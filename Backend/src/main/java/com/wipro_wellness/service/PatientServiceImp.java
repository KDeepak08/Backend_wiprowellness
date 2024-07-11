package com.wipro_wellness.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.MedicalReports;
import com.wipro_wellness.Entity.Patient;

import com.wipro_wellness.Entity.User;
import com.wipro_wellness.dto.MedicalReportsDto;
import com.wipro_wellness.dto.PatientDto;
import com.wipro_wellness.exception.DoctorNotFoundException;
import com.wipro_wellness.exception.PatientNotFoundException;
import com.wipro_wellness.repository.MedicalRepository;
import com.wipro_wellness.repository.PatientRepository;


@Service
public class PatientServiceImp  implements PatientService{
	@Autowired
	private PatientRepository patientRepository;
    @Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private MedicalRepository medicalRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    public PatientDto mapToDto(Patient patient) {
	    return modelMapper.map(patient, PatientDto.class);
	}

	public Patient mapToEntity(PatientDto patient) {
	    return modelMapper.map(patient, Patient.class);
	}
	
	public MedicalReportsDto mapToDto1(MedicalReports medicalReports) {
	    return modelMapper.map(medicalReports, MedicalReportsDto.class);
	}

	public MedicalReports mapToEntity1(MedicalReportsDto medicalReports) {
	    return modelMapper.map(medicalReports, MedicalReports.class);
	}
   
	@Override
	public PatientDto addPatient(PatientDto patientDto) {
		
		if (patientDto.getMedicalReports() != null) {
	        MedicalReports medicalReports = mapToEntity1(patientDto.getMedicalReports());
	        medicalRepository.save(medicalReports);
	        
	        patientDto.setMedicalReports(mapToDto1(medicalReports));
	    }
        patientDto.setPassword(passwordEncoder.encode(patientDto.getPassword()));
	    Patient patient = mapToEntity(patientDto);
	    Patient savedPatient = patientRepository.save(patient);
	    
	    return mapToDto(savedPatient);
	}

	@Override
	public PatientDto updatePatient(PatientDto patient) 
	{
		
		if(getPatientDetails(patient.getId())==null)
		{
			throw new PatientNotFoundException("Patient details not found");
		}
		
		
		if(patient.getMedicalReports()!=null) {
		       medicalRepository.save(mapToEntity1(patient.getMedicalReports()));
		}
		return mapToDto(patientRepository.save(mapToEntity(patient))); 
	}



	



	
	@Override
	public MedicalReportsDto viewMedicalReports(long id) {
		if(getPatientDetails(id)==null)
		{
			throw new PatientNotFoundException("Patient details not found");
		}
		
		return mapToDto1(getPatientDetails(id).getMedicalReports());
	}
  
	public Patient getPatientDetails(long id)
	{
	   Patient orElse= patientRepository.findById(id).orElse(null);
	   if(orElse==null)
	   {
		   throw new PatientNotFoundException("no Patient found");
	   }
	   return orElse;
	}

	@Override
	public String deletePatientRecord(String username) {
		Patient patient=patientRepository.findByUsername(username).orElse(null);
		if(patient ==null)
		{
			throw new DoctorNotFoundException("invalid username");
		}
		patientRepository.deleteById(patient.getId());
		return "record deleted successfully" ;
	}
}
