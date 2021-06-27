package com.project.nando.climbmountain.bean;

import java.util.List;

public class Cluster {
	private int clusterNumber;
	private Centroid centroid;
	private List<Record> records;

	public Cluster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cluster(int clusterNumber, Centroid centroid, List<Record> records) {
		super();
		this.clusterNumber = clusterNumber;
		this.centroid = centroid;
		this.records = records;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	public Centroid getCentroid() {
		return centroid;
	}

	public void setCentroid(Centroid centroid) {
		this.centroid = centroid;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Cluster [clusterNumber=" + clusterNumber + ", centroid=" + centroid + ", records=" + records + "]";
	}

}
