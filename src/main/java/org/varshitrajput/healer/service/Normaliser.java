package org.varshitrajput.healer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.varshitrajput.healer.model.HealerData;
import org.varshitrajput.healer.repository.HealerDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Normaliser {

	private final HealerDataRepository healerDataRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public void normaliseAndSave(String rawJson) {

		if (rawJson == null || rawJson.isBlank()) {
			return;
		}

		try {
			List<Map<String, Object>> payload =
					objectMapper.readValue(
							rawJson,
							new TypeReference<List<Map<String, Object>>>() {}
					);

			List<HealerData> batch = new ArrayList<>(payload.size());

			for (Map<String, Object> data : payload) {
				HealerData healerData = buildHealerData(data);
				if (healerData != null) batch.add(healerData);
			}

			healerDataRepository.saveAll(batch);

		} catch (Exception e) {
			// never let bad JSON crash ingestion
			System.out.println("Failed to normalise payload: " + e.getMessage());
		}
	}

	/* ---------------- mapping ---------------- */

	private HealerData buildHealerData(Map<String, Object> data) {

		HealerData healerData = new HealerData();

		healerData.setDeviceId(String.valueOf(data.get("device.id")));

		healerData.setLatitude(getDouble(data, "position.latitude"));
		if (healerData.getLatitude() == null) {
			return null;
		}
		healerData.setLongitude(getDouble(data, "position.longitude"));
		healerData.setSpeed(getDouble(data, "position.speed"));

		healerData.setEngineIgnitionStatus(
				getBoolean(data, "engine.ignition.status")
		);

		healerData.setAnalogInput1(
				getDouble(data, "ain") != null && getDouble(data, "ain") > 0
		);

		healerData.setDigitalInput1(
				getBoolean(data, "din.1")
		);

		healerData.setDigitalInput2(
				getBoolean(data, "dout.1")
		);

		healerData.setTimestamp(
				getLong(data, "timestamp")
		);

		healerData.setIsEnriched(false);

		return healerData;
	}

	/* ---------------- helpers ---------------- */

	private Double getDouble(Map<String, Object> map, String key) {
		Object val = map.get(key);
		return val instanceof Number ? ((Number) val).doubleValue() : null;
	}

	private Boolean getBoolean(Map<String, Object> map, String key) {
		Object val = map.get(key);
		return val instanceof Boolean ? (Boolean) val : null;
	}

	private Long getLong(Map<String, Object> map, String key) {
		Object val = map.get(key);
		return val instanceof Number ? ((Number) val).longValue() : null;
	}
}
