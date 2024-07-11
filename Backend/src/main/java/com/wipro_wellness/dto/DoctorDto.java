package com.wipro_wellness.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

import com.wipro_wellness.Entity.Patient;

public class DoctorDto {

    private long id;

   // @Pattern(regexp = "[a-zA-Z]{4,20}", message = "Name should be more than four characters")
    private String name;

   // @NotNull(message = "Speciality should not be null")
    private String speciality;

   // @NotNull(message = "Experience should not be null")
    private String experience;

    //@NotNull(message = "Qualification should not be null")
    private String qualification;

    //@NotNull(message = "Designation should not be null")
    private String designation;

    private String role;

    private String username;
    
    private String password;
    
    private List<PatientDto> patient;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  
}
