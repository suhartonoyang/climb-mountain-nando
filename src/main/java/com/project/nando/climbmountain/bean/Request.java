package com.project.nando.climbmountain.bean;

public class Request {
	private String nameAttribute;
	private String valueAttribute;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(String nameAttribute, String valueAttribute) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttribute = valueAttribute;
	}

	public String getNameAttribute() {
		return nameAttribute;
	}

	public void setNameAttribute(String nameAttribute) {
		this.nameAttribute = nameAttribute;
	}

	public String getValueAttribute() {
		return valueAttribute;
	}

	public void setValueAttribute(String valueAttribute) {
		this.valueAttribute = valueAttribute;
	}

	@Override
	public String toString() {
		return "Request [nameAttribute=" + nameAttribute + ", valueAttribute=" + valueAttribute + "]";
	}

}
