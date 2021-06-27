package com.project.nando.climbmountain.bean;

import java.util.List;

public class ClimberClusterDtoList {

	private int totalCluster;
	private String bookingNumber;
	private List<ClimberClusterDto> climbers;

	public ClimberClusterDtoList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClimberClusterDtoList(int totalCluster, String bookingNumber, List<ClimberClusterDto> climbers) {
		super();
		this.totalCluster = totalCluster;
		this.bookingNumber = bookingNumber;
		this.climbers = climbers;
	}

	public int getTotalCluster() {
		return totalCluster;
	}

	public void setTotalCluster(int totalCluster) {
		this.totalCluster = totalCluster;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public List<ClimberClusterDto> getClimbers() {
		return climbers;
	}

	public void setClimbers(List<ClimberClusterDto> climbers) {
		this.climbers = climbers;
	}

	@Override
	public String toString() {
		return "ClimberDtoList [totalCluster=" + totalCluster + ", bookingNumber=" + bookingNumber + ", climbers="
				+ climbers + "]";
	}

}
