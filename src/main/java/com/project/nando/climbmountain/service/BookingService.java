package com.project.nando.climbmountain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.BookingDtl;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.model.ClimberDisease;
import com.project.nando.climbmountain.repo.BookingDtlRepository;
import com.project.nando.climbmountain.repo.BookingRepository;
import com.project.nando.climbmountain.repo.ClimberRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingDtlRepository bookingDtlRepository;

	@Autowired
	private ClimberService climberService;

	@Autowired
	private ClimbingScheduleService climbingScheduleService;

	public List<BookingHdr> getBookingAll() {
		return StreamSupport.stream(bookingRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public BookingHdr getBookingByBookingNumber(String bookingNumber) {
		return bookingRepository.findByBookingNumber(bookingNumber);
	}

	public BookingHdr saveBooking(BookingHdr booking) {
		booking.setClimbingSchedule(
				climbingScheduleService.getClimbingScheduleById(booking.getClimbingSchedule().getId()));
		bookingRepository.save(booking);
		booking.setBookingNumber(generateBookingNumber(booking));
		booking.getBookingDtls().stream().forEach(p->{
			climberService.saveClimber(p.getClimber());
			p.setBookingHdr(booking);
			bookingDtlRepository.save(p);
		});;
		return booking;
	}

	private String generateBookingNumber(BookingHdr booking) {
		String formattedDate = booking.getClimbingSchedule().getDate().toString().replaceAll("-", "");
		String bookingNumber = "CMT" + "-" + formattedDate + "-"
				+ StringUtils.leftPad(String.valueOf(booking.getId()), 5, "0");
		System.out.println("bookingNumber: " + bookingNumber);
		return bookingNumber;
	}
}
