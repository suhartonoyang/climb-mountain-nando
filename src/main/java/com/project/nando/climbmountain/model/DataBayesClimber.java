// Generated with g9.

package com.project.nando.climbmountain.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = "data_bayes_climber")
public class DataBayesClimber implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String age;
	@Column(nullable = false, length = 255)
	private String gender;
	@Column(name = "has_ever_climb", length = 1)
	private boolean hasEverClimb;
	@Column(name = "has_disease", length = 1)
	private boolean hasDisease;
	@Column(nullable = false, length = 255)
	private String result;

	/** Default constructor. */
	public DataBayesClimber() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(int aId) {
		id = aId;
	}

	/**
	 * Access method for age.
	 *
	 * @return the current value of age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Setter method for age.
	 *
	 * @param aAge the new value for age
	 */
	public void setAge(String aAge) {
		age = aAge;
	}

	/**
	 * Access method for gender.
	 *
	 * @return the current value of gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter method for gender.
	 *
	 * @param aGender the new value for gender
	 */
	public void setGender(String aGender) {
		gender = aGender;
	}

	/**
	 * Access method for hasEverClimb.
	 *
	 * @return the current value of hasEverClimb
	 */
	public boolean getHasEverClimb() {
		return hasEverClimb;
	}

	/**
	 * Setter method for hasEverClimb.
	 *
	 * @param aHasEverClimb the new value for hasEverClimb
	 */
	public void setHasEverClimb(boolean aHasEverClimb) {
		hasEverClimb = aHasEverClimb;
	}

	/**
	 * Access method for hasDisease.
	 *
	 * @return the current value of hasDisease
	 */
	public boolean getHasDisease() {
		return hasDisease;
	}

	/**
	 * Setter method for hasDisease.
	 *
	 * @param aHasDisease the new value for hasDisease
	 */
	public void setHasDisease(boolean aHasDisease) {
		hasDisease = aHasDisease;
	}

	/**
	 * Access method for result.
	 *
	 * @return the current value of result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Setter method for result.
	 *
	 * @param aResult the new value for result
	 */
	public void setResult(String aResult) {
		result = aResult;
	}

	/**
	 * Compares the key for this instance with another DataBayesClimber.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class DataBayesClimber and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DataBayesClimber)) {
			return false;
		}
		DataBayesClimber that = (DataBayesClimber) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another DataBayesClimber.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DataBayesClimber))
			return false;
		return this.equalKeys(other) && ((DataBayesClimber) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = getId();
		result = 37 * result + i;
		return result;
	}

	@Override
	public String toString() {
		return "DataBayesClimber [id=" + id + ", age=" + age + ", gender=" + gender + ", hasEverClimb=" + hasEverClimb
				+ ", hasDisease=" + hasDisease + ", result=" + result + "]";
	}

}
