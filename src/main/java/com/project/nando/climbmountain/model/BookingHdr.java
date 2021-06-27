// Generated with g9.

package com.project.nando.climbmountain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "booking_hdr")
public class BookingHdr implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "booking_number", length = 255)
	private String bookingNumber;
	@Column(name = "date_return", nullable = false)
	private LocalDate dateReturn;
	@OneToMany(mappedBy = "bookingHdr")
	@JsonIgnoreProperties(value = "bookingHdr")
	private List<BookingDtl> bookingDtls;
	@ManyToOne(optional = false)
	@JoinColumn(name = "climbing_schedule_id", nullable = false)
	@JsonIgnoreProperties(value = "bookingHdrs")
	private ClimbingSchedule climbingSchedule;

	/** Default constructor. */
	public BookingHdr() {
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

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * Access method for dateReturn.
	 *
	 * @return the current value of dateReturn
	 */
	public LocalDate getDateReturn() {
		return dateReturn;
	}

	/**
	 * Setter method for dateReturn.
	 *
	 * @param aDateReturn the new value for dateReturn
	 */
	public void setDateReturn(LocalDate aDateReturn) {
		dateReturn = aDateReturn;
	}

	/**
	 * Access method for bookingDtl.
	 *
	 * @return the current value of bookingDtl
	 */
	public List<BookingDtl> getBookingDtls() {
		return bookingDtls;
	}

	/**
	 * Setter method for bookingDtl.
	 *
	 * @param aBookingDtl the new value for bookingDtl
	 */
	public void setBookingDtls(List<BookingDtl> aBookingDtls) {
		bookingDtls = aBookingDtls;
	}

	/**
	 * Access method for climbingSchedule.
	 *
	 * @return the current value of climbingSchedule
	 */
	public ClimbingSchedule getClimbingSchedule() {
		return climbingSchedule;
	}

	/**
	 * Setter method for climbingSchedule.
	 *
	 * @param aClimbingSchedule the new value for climbingSchedule
	 */
	public void setClimbingSchedule(ClimbingSchedule aClimbingSchedule) {
		climbingSchedule = aClimbingSchedule;
	}

	/**
	 * Compares the key for this instance with another BookingHdr.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class BookingHdr and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookingHdr)) {
			return false;
		}
		BookingHdr that = (BookingHdr) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another BookingHdr.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BookingHdr))
			return false;
		return this.equalKeys(other) && ((BookingHdr) other).equalKeys(this);
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
		return "BookingHdr [id=" + id + ", dateReturn=" + dateReturn + ", bookingDtl=" + bookingDtls
				+ ", climbingSchedule=" + climbingSchedule + "]";
	}

}
