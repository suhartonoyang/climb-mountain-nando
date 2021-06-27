package com.project.nando.climbmountain.bean;

public class ClimberClusterDto {

	private int climberId;
	private String climberName;
	private boolean isLeader;
	private int clusterNumber;

	public ClimberClusterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClimberClusterDto(int climberId, String climberName, boolean isLeader, int clusterNumber) {
		super();
		this.climberId = climberId;
		this.climberName = climberName;
		this.isLeader = isLeader;
		this.clusterNumber = clusterNumber;
	}

	public int getClimberId() {
		return climberId;
	}

	public void setClimberId(int climberId) {
		this.climberId = climberId;
	}

	public String getClimberName() {
		return climberName;
	}

	public void setClimberName(String climberName) {
		this.climberName = climberName;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	@Override
	public String toString() {
		return "ClimberDto [climberId=" + climberId + ", climberName=" + climberName + ", isLeader=" + isLeader
				+ ", clusterNumber=" + clusterNumber + "]";
	}

}
