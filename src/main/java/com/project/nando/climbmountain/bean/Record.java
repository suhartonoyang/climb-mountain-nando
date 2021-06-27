package com.project.nando.climbmountain.bean;

import java.util.Map;
import java.util.Objects;

/**
 * Encapsulates all feature values for a few attributes. Optionally each record
 * can be described with the {@link #description} field.
 */
public class Record {

	/**
	 * The record description. For example, this can be the artist name for the
	 * famous musician example.
	 */
	private final int climberId;
	private final String climberName;
	private final boolean isLeader;

	/**
	 * Encapsulates all attributes and their corresponding values, i.e. features.
	 */
	private final Map<String, Double> features;

	public Record(int climberId, String climberName, boolean isLeader, Map<String, Double> features) {
		super();
		this.climberId = climberId;
		this.climberName = climberName;
		this.isLeader = isLeader;
		this.features = features;
	}

	public int getClimberId() {
		return climberId;
	}

	public String getClimberName() {
		return climberName;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public Map<String, Double> getFeatures() {
		return features;
	}

	@Override
	public String toString() {
		return "Record [climberId=" + climberId + ", climberName=" + climberName + ", isLeader=" + isLeader
				+ ", features=" + features + "]";
	}

}