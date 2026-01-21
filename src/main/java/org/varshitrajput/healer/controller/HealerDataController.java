package org.varshitrajput.healer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varshitrajput.healer.model.HealerData;
import org.varshitrajput.healer.service.HealerDataService;

@RestController
@RequestMapping("/api/heal")
@RequiredArgsConstructor
public class HealerDataController {

	private final HealerDataService healerDataService;

	@PostMapping
	public ResponseEntity<HealerData> create(@RequestBody HealerData healerData) {
		HealerData saved = healerDataService.save(healerData);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
}
