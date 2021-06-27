package com.project.nando.climbmountain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
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
	private ClimberDiseaseService climberDiseaseService;

	public List<BookingHdr> getBookingAll() {
		return StreamSupport.stream(bookingRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public BookingHdr getBookingByBookingNumber(String bookingNumber) {
		return bookingRepository.findByBookingNumber(bookingNumber);
	}

	public BookingHdr saveBooking(BookingHdr booking) {
		BookingHdr newBooking = bookingRepository.save(booking);
		newBooking.setBookingNumber(generateBookingNumber(newBooking));
		bookingRepository.save(newBooking);

		booking.getBookingDtls().stream().forEach(p -> {
			Climber climber = climberService.saveClimber(p.getClimber());
			p.setClimber(climber);
			p.getClimber().getClimberDiseases().stream().forEach(q -> {
				q.setClimber(climber);
				q = climberDiseaseService.saveClimberDisease(q);
			});
			p.setBookingHdr(newBooking);
			BookingDtl dtl = bookingDtlRepository.save(p);
			p.setId(dtl.getId());
		});
		return booking;
	}

	private String generateBookingNumber(BookingHdr booking) {
		DateFormatter df = new DateFormatter("yyyymmdd");
		String bookingNumber = "CMT" + "-" + df + "-" + StringUtils.leftPad(String.valueOf(booking.getId()), 5, "0");
		return bookingNumber;
	}
}
