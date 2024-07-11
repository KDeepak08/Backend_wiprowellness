package com.wipro_wellness.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "patients")
public class Patient implements UserDetails  {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Pattern(regexp = "[a-zA-Z]{6,20}",message = "name should be more than six char")
	private String fullName;
	
	private String dateOfBirth;
	
	@Pattern(regexp = "^[6-9]*[0-9]{10,12}$", message = "It should starts with a digit between 6 and 9 and should be 10 digits long.")
	private String contactNumber;
	
	@Pattern(regexp = "^[a-zA-Z0-9 .,;:'\"!?()\\-]{10,500}$", message = "It should contains only letters (both uppercase and lowercase), digits, spaces, and some common punctuation marks. It should Has a length between 10 and 500 characters.")
	private String symptoms;
	
	@Pattern(regexp = "^[a-zA-Z0-9\\s,.'-]{5,100}$", message = "It should contains only letters (both uppercase and lowercase), digits, spaces, commas, periods, apostrophes, and hyphens.It has a length between 5 and 100 characters.")
	private String patientInformation;

	public String getPatientInformation() {
		return patientInformation;
	}

	public void setPatientInformation(String patientInformation) {
		this.patientInformation = patientInformation;
	}
	
	


	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor")
	private Doctor doctor;

	private String status;
	@OneToOne
	private MedicalReports medicalReports;
	
	private String role="ROLE_PATIENT";
   
   //@JsonIgnore
   private String appointmentDate;

	public String getRole() {
		return role;
	}


	public MedicalReports getMedicalReports() {
		return medicalReports;
	}

	public void setMedicalReports(MedicalReports medicalReports) {
		this.medicalReports = medicalReports;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	  @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	   
	    public void setRole(String role) {
			this.role = role;
		}

		@Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority(role));
	    }

		@Override
		public String toString() {
			return "Patient [id=" + id + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", contactNumber="
					+ contactNumber + ", symptoms=" + symptoms + ", patientInformation=" + patientInformation
					+ ", gender=" + gender + ", doctor=" + doctor + ", status=" + status + ", medicalReports="
					+ medicalReports + ", role=" + role + ", appointmentDate=" + appointmentDate + ", username="
					+ username + ", password=" + password + "]";
		}
  

}
