package com.project.nando.climbmountain.controller;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.nando.climbmountain.bean.Response;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.model.ClimbingSchedule;
import com.project.nando.climbmountain.service.BookingService;
import com.project.nando.climbmountain.service.ClimberService;
import com.project.nando.climbmountain.service.ClimbingScheduleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/")
	public ResponseEntity<Response> getBookingAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<BookingHdr> bookings = bookingService.getBookingAll();
		resp.setData(bookings);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/{bookingNumber}")
	public ResponseEntity<Response> getBookingByBookingNumber(@PathVariable String bookingNumber) {
		Response resp = new Response();

		BookingHdr booking = bookingService.getBookingByBookingNumber(bookingNumber);
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

	@Transactional
	@PostMapping("/")
	@ApiOperation(value = "create one record of booking")
	public ResponseEntity<Response> saveBooking(
			@ApiParam(value = "In bookingHdr, please clear attributes of id and bookingNumber. "
					+ "In climbingSchedule, please just fill id attribute. "
					+ "In bookingDtls, please clear id attribute. "
					+ "In climber, please clear id attribute and bookingDtls. "
					+ "In climberDiseases, please clear id attribute" ) @RequestBody BookingHdr booking) {
		BookingHdr newBooking = bookingService.saveBooking(booking);
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.CREATED.value()));
		resp.setMessage("Sucessfully Booking!");
		resp.setData(Arrays.asList(newBooking));
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

}
