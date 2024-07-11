package com.wipro_wellness.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro_wellness.Entity.Appointment;

public interface AppointMentRepository extends JpaRepository<Appointment, Long> {
	
	
	
	Optional<Appointment> findByPatientId(long patientId);
	
	List<Appointment> findByDoctorId(long id);

}
