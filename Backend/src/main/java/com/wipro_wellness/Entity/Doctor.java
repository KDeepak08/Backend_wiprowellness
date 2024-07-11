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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="doctors")
public class Doctor implements UserDetails {
	
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   
   

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}
	@Pattern(regexp = "[a-zA-Z]{4,20}",message = "name should be more than four char")
	private String name;
	
	@NotNull(message = "the speciality should not be null")
	private String speciality;
	
	@NotNull(message = "the experience should not be null")
	private String experience;
	
	@NotNull(message = "the qualification should not be null")
	private String qualification;
	
	@NotNull(message = "the designation should not be null")
	private String designation;
	
	@JsonIgnore
	@OneToMany(mappedBy="doctor")
	private List<Patient> patient;
	
	public List<Patient> getPatient() {
		return patient;
	}
	
	

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
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

	private String role="ROLE_DOCTOR";



	public String getRole() {
		return role;
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

	   
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority(role));
	    }
	
	
	
	



}
