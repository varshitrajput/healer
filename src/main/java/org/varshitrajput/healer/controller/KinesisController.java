package org.varshitrajput.healer.controller;
import com.varshitrajput.stream_service_java.service.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.varshitrajput.healer.service.Normaliser;

@RestController
@RequestMapping("/api/kinesis")
public class KinesisController {

	@Autowired
	private Normaliser normaliser;

	@PostMapping("/publish")
    public ResponseEntity<String> publishDataToKinesis(@RequestBody String payload) {
        normaliser.normaliseAndSave(payload);
        return ResponseEntity.ok("Data successfully sent to Kinesis!");
    }
}
