package org.varshitrajput.healer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.varshitrajput.healer.model.HealerData;
import org.varshitrajput.healer.repository.HealerDataRepository;

@Service
@RequiredArgsConstructor
public class HealerDataService {

	private final HealerDataRepository healerDataRepository;

	public HealerData save(HealerData healerData) {
		return healerDataRepository.save(healerData);
	}
}
