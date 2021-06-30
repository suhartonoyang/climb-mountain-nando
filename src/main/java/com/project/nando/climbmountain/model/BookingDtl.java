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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "booking_dtl")
public class BookingDtl implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "is_need_tour_guide", length = 1)
	private boolean isNeedTourGuide;
	@ManyToOne(optional = false)
	@JoinColumn(name = "climber_id", nullable = false)
	@JsonIgnoreProperties(value = "bookingDtls")
	private Climber climber;
	@ManyToOne(optional = false)
	@JoinColumn(name = "booking_hdr_id", nullable = false)
	@JsonIgnoreProperties(value = "bookingDtls")
	private BookingHdr bookingHdr;

	/** Default constructor. */
	public BookingDtl() {
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

	public boolean isNeedTourGuide() {
		return isNeedTourGuide;
	}

	public void setNeedTourGuide(boolean isNeedTourGuide) {
		this.isNeedTourGuide = isNeedTourGuide;
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
	 * Access method for bookingHdr.
	 *
	 * @return the current value of bookingHdr
	 */
	public BookingHdr getBookingHdr() {
		return bookingHdr;
	}

	/**
	 * Setter method for bookingHdr.
	 *
	 * @param aBookingHdr the new value for bookingHdr
	 */
	public void setBookingHdr(BookingHdr aBookingHdr) {
		bookingHdr = aBookingHdr;
	}

	/**
	 * Compares the key for this instance with another BookingDtl.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class BookingDtl and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookingDtl)) {
			return false;
		}
		BookingDtl that = (BookingDtl) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another BookingDtl.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BookingDtl))
			return false;
		return this.equalKeys(other) && ((BookingDtl) other).equalKeys(this);
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
		return "BookingDtl [id=" + id + ", isNeedTourGuide=" + isNeedTourGuide + ", climber=" + climber
				+ ", bookingHdr=" + bookingHdr + "]";
	}

}
