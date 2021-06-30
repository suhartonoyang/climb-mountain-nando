package com.project.nando.climbmountain.bean;

public class MapAttributResult {
	private String nameAttribute;
	private String valueAttriute;
	private String result;
	private long count;
	private double prob;

	public MapAttributResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapAttributResult(String nameAttribute, String valueAttriute, String result, long count, double prob) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttriute = valueAttriute;
		this.result = result;
		this.count = count;
		this.prob = prob;
	}

	public String getNameAttribute() {
		return nameAttribute;
	}

	public void setNameAttribute(String nameAttribute) {
		this.nameAttribute = nameAttribute;
	}

	public String getValueAttriute() {
		return valueAttriute;
	}

	public void setValueAttriute(String valueAttriute) {
		this.valueAttriute = valueAttriute;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	@Override
	public String toString() {
		return "MapAttributResult [nameAttribute=" + nameAttribute + ", valueAttriute=" + valueAttriute + ", result="
				+ result + ", count=" + count + ", prob=" + prob + "]";
	}

}