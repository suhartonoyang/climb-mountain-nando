package com.project.nando.climbmountain.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.nando.climbmountain.bean.Response;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.service.BayesService;

@RestController
@RequestMapping("/api/bayes")
@CrossOrigin
public class BayesController {

	@Autowired
	private BayesService bayesService;

	@Transactional
	@PostMapping("/run")
	public ResponseEntity<?> run(@RequestParam String bookingNumber) {
		Response resp = new Response();
		BookingHdr booking = bayesService.runBayesByBookingNumber(bookingNumber);
		if (booking == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Data not found!");
			resp.setData(null);
			return ResponseEntity.ok(resp);
		} else {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage(HttpStatus.OK.name());
			resp.setData(Arrays.asList(booking));
			return ResponseEntity.ok(resp);
		}
	}

//	@PostMapping("/run")
//	public ResponseEntity<?> run(@RequestBody List<Request> requests) {
//		Result result = bayesService.run(requests);
//		return ResponseEntity.ok().body(result);
//	}
}
