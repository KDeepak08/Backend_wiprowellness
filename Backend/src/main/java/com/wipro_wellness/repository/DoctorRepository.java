package com.wipro_wellness.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.Patient;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	public List<Patient> findByPatient_AppointmentDate(String date);
	
    Optional<Doctor>findByUsername(String username);
	
	
}
