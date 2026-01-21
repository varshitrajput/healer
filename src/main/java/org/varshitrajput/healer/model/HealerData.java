
package org.varshitrajput.healer.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HealerData")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HealerData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "analog_input_1")
	private Boolean analogInput1;

	@Column(name = "device_id", nullable = false)
	private String deviceId;

	private Double latitude;
	private Double longitude;
	private Double speed;

	@Column(name = "asset_group_id")
	private Integer assetGroupId;

	@Column(name = "asset_id")
	private Integer assetId;

	@Column(name = "engine_ignition_status")
	private Boolean engineIgnitionStatus;

	private Long timestamp;

	@Column(name = "digital_input_1")
	private Boolean digitalInput1;

	@Column(name = "digital_input_2")
	private Boolean digitalInput2;

	@Column(name = "is_enriched")
	private Boolean isEnriched;
}
