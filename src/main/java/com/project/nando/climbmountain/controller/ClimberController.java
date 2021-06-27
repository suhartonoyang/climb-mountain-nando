package com.project.nando.climbmountain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.nando.climbmountain.bean.Response;
import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.model.ClimbingSchedule;
import com.project.nando.climbmountain.service.ClimberService;
import com.project.nando.climbmountain.service.ClimbingScheduleService;

@RestController
@RequestMapping("/api/climber")
@CrossOrigin
public class ClimberController {

	@Autowired
	private ClimberService climberService;

	@GetMapping("/")
	public ResponseEntity<Response> getClimberAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<Climber> climbers = climberService.getClimberAll();
		resp.setData(climbers);
		return ResponseEntity.ok(resp);
	}

}
