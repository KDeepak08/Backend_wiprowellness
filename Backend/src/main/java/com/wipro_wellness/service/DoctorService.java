package com.wipro_wellness.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.dto.DoctorDto;
import com.wipro_wellness.dto.PatientDto;

public interface DoctorService { 
	
	public DoctorDto addDetails(DoctorDto doctor);
	
	public DoctorDto update(DoctorDto doctor);
	
	public PatientDto updateMedicalRecord(PatientDto patient);
	
	 public PatientDto updateMedicines(PatientDto patient);
	 
	 public String deleteDoctorRecord(String username);
	 
	 

}
