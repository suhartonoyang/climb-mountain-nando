// Generated with g9.

package com.project.nando.climbmountain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "climber")
public class Climber implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(nullable = false)
	private LocalDate dob;
	@Column(nullable = false, length = 255)
	private String gender;
	@Column(nullable = false, length = 255)
	private String address;
	@Column(nullable = false, length = 255)
	private String province;
	@Column(nullable = false, length = 255)
	private String city;
	@Column(name = "identity_type", nullable = false, length = 255)
	private String identityType;
	@Column(name = "identity_number", nullable = false, length = 255)
	private String identityNumber;
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;
	@Column(name = "family_phone_number", length = 15)
	private String familyPhoneNumber;
	@Column(nullable = false, length = 255)
	private String occupation;
	@Column(length = 255)
	private String email;
	@Column(nullable = false, precision = 10)
	private int age;
	@Column(name = "has_ever_climb", nullable = false, length = 1)
	private boolean hasEverClimb;
	@Column(name = "number_of_climbs", nullable = false, precision = 10)
	private int numberOfClimbs;
	@Column(name = "is_leader", nullable = false, length = 1)
	private boolean isLeader;
	@OneToMany(mappedBy = "climber")
	@JsonIgnoreProperties(value = "climber")
	private Set<BookingDtl> bookingDtls;
	@OneToMany(mappedBy = "climber")
	@JsonIgnoreProperties(value = "climber")
	private List<ClimberDisease> climberDiseases;

	/** Default constructor. */
	public Climber() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFamilyPhoneNumber() {
		return familyPhoneNumber;
	}

	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		this.familyPhoneNumber = familyPhoneNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isHasEverClimb() {
		return hasEverClimb;
	}

	public void setHasEverClimb(boolean hasEverClimb) {
		this.hasEverClimb = hasEverClimb;
	}

	public int getNumberOfClimbs() {
		return numberOfClimbs;
	}

	public void setNumberOfClimbs(int numberOfClimbs) {
		this.numberOfClimbs = numberOfClimbs;
	}

	public Set<BookingDtl> getBookingDtls() {
		return bookingDtls;
	}

	public void setBookingDtls(Set<BookingDtl> bookingDtls) {
		this.bookingDtls = bookingDtls;
	}

	public List<ClimberDisease> getClimberDiseases() {
		return climberDiseases;
	}

	public void setClimberDiseases(List<ClimberDisease> climberDiseases) {
		this.climberDiseases = climberDiseases;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	@Override
	public String toString() {
		return "Climber [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", address=" + address
				+ ", province=" + province + ", city=" + city + ", identityType=" + identityType + ", identityNumber="
				+ identityNumber + ", phoneNumber=" + phoneNumber + ", familyPhoneNumber=" + familyPhoneNumber
				+ ", occupation=" + occupation + ", email=" + email + ", age=" + age + ", hasEverClimb=" + hasEverClimb
				+ ", numberOfClimbs=" + numberOfClimbs + ", isLeader=" + isLeader + ", bookingDtls=" + bookingDtls
				+ ", climberDiseases=" + climberDiseases + "]";
	}

}
