package com.wipro_wellness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro_wellness.Entity.MedicalReports;
import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.dto.MedicalReportsDto;
import com.wipro_wellness.dto.PatientDto;
import com.wipro_wellness.exception.DoctorNotFoundException;
import com.wipro_wellness.exception.PatientNotFoundException;
import com.wipro_wellness.service.PatientService;
import com.wipro_wellness.service.PatientServiceImp;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientServiceImp patientServiceImp;
	
	
	@PostMapping("/addPatient")
	public PatientDto addPatientDetails( @RequestBody PatientDto patient)
	{
		return patientServiceImp.addPatient(patient);
	}
	
	@PutMapping("/updatePatient")
	 @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
	public PatientDto updatePatient(@Valid @RequestBody PatientDto patient)
	{ 
		System.out.println(patient.getId());
		return patientServiceImp.updatePatient(patient);
		
	}
	
	@GetMapping("MedicalHistory/{id}")
	public MedicalReportsDto viewMedicalHistory(@PathVariable("id") long id)
	{
		
		return patientServiceImp.viewMedicalReports(id);
	}
	 @DeleteMapping("/delete/{username}")
	    public ResponseEntity<String> deletePatientRecord(@PathVariable String username) {
	        try {
	            String response = patientServiceImp.deletePatientRecord(username);
	            return ResponseEntity.ok(response);
	        } catch (PatientNotFoundException | DoctorNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	        }

}
