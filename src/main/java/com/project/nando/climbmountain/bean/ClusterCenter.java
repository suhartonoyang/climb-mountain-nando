package com.project.nando.climbmountain.bean;

import java.util.List;

public class ClusterCenter {

	private int clusterNumber;
	private int row;
	private List<ClusterCenter2D> columns;

	public ClusterCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClusterCenter(int clusterNumber, int row, List<ClusterCenter2D> columns) {
		super();
		this.clusterNumber = clusterNumber;
		this.row = row;
		this.columns = columns;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public List<ClusterCenter2D> getColumns() {
		return columns;
	}

	public void setColumns(List<ClusterCenter2D> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "ClusterCenter [clusterNumber=" + clusterNumber + ", row=" + row + ", columns=" + columns + "]";
	}

}
