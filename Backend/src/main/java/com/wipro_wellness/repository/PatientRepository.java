package com.wipro_wellness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Optional<Patient> findByUsername(String username);
	
	
}
