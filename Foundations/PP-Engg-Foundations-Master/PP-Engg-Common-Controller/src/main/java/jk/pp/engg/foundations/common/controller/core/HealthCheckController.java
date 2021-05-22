package jk.pp.engg.foundations.common.controller.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class HealthCheckController {

	public static final String HEALTH_MSG = "{\"status\" : \"running\"}";

	@RequestMapping(path = "health")
	public ResponseEntity<String> healthCheck() throws Exception {
		return ResponseEntity.ok(HEALTH_MSG);
	}

}
