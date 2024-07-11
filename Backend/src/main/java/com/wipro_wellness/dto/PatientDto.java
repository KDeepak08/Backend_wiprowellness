package com.wipro_wellness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.MedicalReports;

import jakarta.validation.constraints.Pattern;

public class PatientDto {

    private long id;

    //@Pattern(regexp = "[a-zA-Z]{6,20}", message = "Name should be more than six characters")
    private String fullName;

    private String dateOfBirth;

    //@Pattern(regexp = "^[6-9]*[0-9]{10,12}$", message = "Contact number should start with a digit between 6 and 9 and should be 10 digits long.")
    private String contactNumber;

   // @Pattern(regexp = "^[a-zA-Z0-9 .,;:'\"!?()\\-]{10,500}$", message = "Symptoms should contain only letters, digits, spaces, and common punctuation marks, with a length between 10 and 500 characters.")
    private String symptoms;

   // @Pattern(regexp = "^[a-zA-Z0-9\\s,.'-]{5,100}$", message = "Patient information should contain only letters, digits, spaces, commas, periods, apostrophes, and hyphens, with a length between 5 and 100 characters.")
    private String patientInformation;
   
    private String gender;
    //@JsonIgnore
    private String status;
    //@JsonIgnore
    private String role;
   // @JsonIgnore
    private String appointmentDate;

    private String username;
    
    private String password;
    
    private MedicalReportsDto medicalReports;
    
    @JsonIgnore
    private DoctorDto doctor;

    // Getters and Setters

    public DoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}

	public MedicalReportsDto getMedicalReports() {
		return medicalReports;
	}

	public void setMedicalReports(MedicalReportsDto medicalReports) {
		this.medicalReports = medicalReports;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPatientInformation() {
        return patientInformation;
    }

    public void setPatientInformation(String patientInformation) {
        this.patientInformation = patientInformation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
