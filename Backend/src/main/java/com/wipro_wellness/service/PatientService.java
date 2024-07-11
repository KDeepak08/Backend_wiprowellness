package com.wipro_wellness.service;

import com.wipro_wellness.Entity.MedicalReports;
import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.dto.MedicalReportsDto;
import com.wipro_wellness.dto.PatientDto;

public interface PatientService {
	
	public PatientDto addPatient(PatientDto patient);
	
	public PatientDto updatePatient(PatientDto patient);
	

	
	public MedicalReportsDto viewMedicalReports(long id);
	
	public String deletePatientRecord(String username);
	

}
