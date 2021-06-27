package com.project.nando.climbmountain.bean;

public class ClusterCenter2D {

	private long lessonId;
	private String lessonName;
	private int column;
	private double value;

	public ClusterCenter2D() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClusterCenter2D(long lessonId, String lessonName, int column, double value) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.column = column;
		this.value = value;
	}

	public long getLessonId() {
		return lessonId;
	}

	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ClusterCenter2D [lessonId=" + lessonId + ", lessonName=" + lessonName + ", column=" + column
				+ ", value=" + value + "]";
	}

}
