// Generated with g9.

package com.project.nando.climbmountain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "climbing_schedule")
public class ClimbingSchedule implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false)
	private LocalDate date;
	@Column(nullable = false, precision = 10)
	private int quota;
	@OneToMany(mappedBy = "climbingSchedule")
	@JsonIgnoreProperties(value = { "climbingSchedule" })
	private Set<BookingHdr> bookingHdrs;

	/** Default constructor. */
	public ClimbingSchedule() {
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
	 * Access method for date.
	 *
	 * @return the current value of date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Setter method for date.
	 *
	 * @param aDate the new value for date
	 */
	public void setDate(LocalDate aDate) {
		date = aDate;
	}

	/**
	 * Access method for quota.
	 *
	 * @return the current value of quota
	 */
	public int getQuota() {
		return quota;
	}

	/**
	 * Setter method for quota.
	 *
	 * @param aQuota the new value for quota
	 */
	public void setQuota(int aQuota) {
		quota = aQuota;
	}

	/**
	 * Access method for bookingHdr.
	 *
	 * @return the current value of bookingHdr
	 */
	public Set<BookingHdr> getBookingHdrs() {
		return bookingHdrs;
	}

	/**
	 * Setter method for bookingHdr.
	 *
	 * @param aBookingHdr the new value for bookingHdr
	 */
	public void setBookingHdrs(Set<BookingHdr> aBookingHdrs) {
		bookingHdrs = aBookingHdrs;
	}

	/**
	 * Compares the key for this instance with another ClimbingSchedule.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ClimbingSchedule and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClimbingSchedule)) {
			return false;
		}
		ClimbingSchedule that = (ClimbingSchedule) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ClimbingSchedule.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClimbingSchedule))
			return false;
		return this.equalKeys(other) && ((ClimbingSchedule) other).equalKeys(this);
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
		return "ClimbingSchedule [id=" + id + ", date=" + date + ", quota=" + quota + ", bookingHdr=" + bookingHdrs
				+ "]";
	}

}
