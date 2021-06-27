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
import com.project.nando.climbmountain.model.ClimbingSchedule;
import com.project.nando.climbmountain.service.ClimbingScheduleService;

@RestController
@RequestMapping("/api/climbing-schedule")
@CrossOrigin
public class ClimbingScheduleController {

	@Autowired
	private ClimbingScheduleService climbingScheduleService;

	@GetMapping("/")
	public ResponseEntity<Response> getClimbingScheduleAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<ClimbingSchedule> climbingSchedules = climbingScheduleService.getClimbingScheduleAll();
		resp.setData(climbingSchedules);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/{month}/{year}")
	public ResponseEntity<Response> getClimbingScheduleByMonthAndYear(@PathVariable int month, @PathVariable int year) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<ClimbingSchedule> climbingSchedules = climbingScheduleService.getClimbingScheduleByMonthAndYear(month,
				year);
		resp.setData(climbingSchedules);
		return ResponseEntity.ok(resp);
	}
}
