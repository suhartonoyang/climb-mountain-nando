package com.project.nando.climbmountain.bean;

import java.util.List;

public class Attribute {

	private String name;
	private List<String> values;

	public Attribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attribute(String name, List<String> values) {
		super();
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", values=" + values + "]";
	}

}
