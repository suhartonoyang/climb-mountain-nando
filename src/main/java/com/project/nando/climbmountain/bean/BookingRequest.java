package com.project.nando.climbmountain.bean;

import java.time.LocalDate;
import java.util.List;

import com.project.nando.climbmountain.model.Climber;

public class BookingRequest {

	private String bookingNumber;
	private LocalDate dateDeparture;
	private LocalDate dateReturn;
	private List<Climber> climbers;

	public BookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public LocalDate getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(LocalDate dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public LocalDate getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(LocalDate dateReturn) {
		this.dateReturn = dateReturn;
	}

	public List<Climber> getClimbers() {
		return climbers;
	}

	public void setClimbers(List<Climber> climbers) {
		this.climbers = climbers;
	}

	@Override
	public String toString() {
		return "BookingRequest [bookingNumber=" + bookingNumber + ", dateDeparture=" + dateDeparture + ", dateReturn="
				+ dateReturn + ", climbers=" + climbers + "]";
	}

}
