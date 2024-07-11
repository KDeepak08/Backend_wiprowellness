package com.wipro_wellness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro_wellness.Entity.MedicalReports;

public interface MedicalRepository extends JpaRepository<MedicalReports, Long>{
	
	
	

}
