package com.project.nando.climbmountain.bean;

public class DataSet {

	private long id;
	private String weather;
	private String car;
	private String result;

	public DataSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataSet(long id, String weather, String car, String result) {
		super();
		this.id = id;
		this.weather = weather;
		this.car = car;
		this.result = result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
