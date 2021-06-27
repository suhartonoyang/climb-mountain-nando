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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name = "climber_disease")
public class ClimberDisease implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "disease_name", nullable = false, length = 255)
	private String diseaseName;
	@ManyToOne(optional = false)
	@JoinColumn(name = "climber_id", nullable = false)
	private Climber climber;

	/** Default constructor. */
	public ClimberDisease() {
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
	 * Access method for diseaseName.
	 *
	 * @return the current value of diseaseName
	 */
	public String getDiseaseName() {
		return diseaseName;
	}

	/**
	 * Setter method for diseaseName.
	 *
	 * @param aDiseaseName the new value for diseaseName
	 */
	public void setDiseaseName(String aDiseaseName) {
		diseaseName = aDiseaseName;
	}

	/**
	 * Access method for climber.
	 *
	 * @return the current value of climber
	 */
	public Climber getClimber() {
		return climber;
	}

	/**
	 * Setter method for climber.
	 *
	 * @param aClimber the new value for climber
	 */
	public void setClimber(Climber aClimber) {
		climber = aClimber;
	}

	/**
	 * Compares the key for this instance with another ClimberDisease.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ClimberDisease and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClimberDisease)) {
			return false;
		}
		ClimberDisease that = (ClimberDisease) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ClimberDisease.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClimberDisease))
			return false;
		return this.equalKeys(other) && ((ClimberDisease) other).equalKeys(this);
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
		return "ClimberDisease [id=" + id + ", diseaseName=" + diseaseName + ", climber=" + climber + "]";
	}

}
