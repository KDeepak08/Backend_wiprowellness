package com.wipro_wellness.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro_wellness.Entity.Admin;
import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.dto.AdminDto;
import com.wipro_wellness.dto.DoctorDto;
import com.wipro_wellness.dto.PatientDto;
import com.wipro_wellness.exception.DoctorNotFoundException;
import com.wipro_wellness.exception.PatientNotFoundException;
import com.wipro_wellness.repository.DoctorRepository;
import com.wipro_wellness.repository.PatientRepository;

@Service
public class DoctorServiceImp implements DoctorService{
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DoctorDto mapToDto(Doctor doctor)
	{
		return modelMapper.map(doctor, DoctorDto.class);
	}
	
	public Doctor mapToEntity(DoctorDto doctor)
	{
		return modelMapper.map(doctor, Doctor.class);
	}
    
	public PatientDto mapToDto(Patient patient) {
	    return modelMapper.map(patient, PatientDto.class);
	}

	public Patient mapToEntity(PatientDto patient) {
	    return modelMapper.map(patient, Patient.class);
	}
	@Override
	public DoctorDto addDetails(DoctorDto doctor) {
		doctor.setPassword(passwordEncoder.encode(mapToEntity(doctor).getPassword()));
		return mapToDto(doctorRepository.save(mapToEntity(doctor)));
	}

	@Override
	public DoctorDto update(DoctorDto doctor) {
		if(getDoctorDetails(mapToEntity(doctor).getId())==null)
		{
			throw new DoctorNotFoundException("Doctor details not found");
		}
		return mapToDto(doctorRepository.save(mapToEntity(doctor)));
	}


	@Override
	public PatientDto updateMedicalRecord(PatientDto patient) {
		
		if(getPatientDetails(mapToEntity(patient).getId())==null)
		{
			throw new DoctorNotFoundException("Doctor details not found");
		}
		
		return mapToDto(patientRepository.save(mapToEntity(patient))) ;
	}

	@Override
	public PatientDto updateMedicines(PatientDto patient) {
		if(getPatientDetails(mapToEntity(patient).getId())==null)
		{
			throw new DoctorNotFoundException("Doctor details not found");
		}
		
		return mapToDto(patientRepository.save(mapToEntity(patient))) ;
	}
	
	
	public Doctor getDoctorDetails(long id)
	{ 
		Doctor orElse = doctorRepository.findById(id).orElse(null);
		if(orElse==null)
		{
			throw new DoctorNotFoundException("Doctor details not found");
		}
		return doctorRepository.findById(id).get();
	}
	
	public Patient getPatientDetails(long id)
	{
		Patient patient = patientRepository.findById(id).orElse(null);
		
		if(patient==null)
		{
			throw new PatientNotFoundException("patient details not found");
		}
	  return patient;
	}

	@Override
	public String deleteDoctorRecord(String username) {
		Doctor doctor=doctorRepository.findByUsername(username).orElse(null);
		if(doctor ==null)
		{
			throw new DoctorNotFoundException("invalid username");
		}
		doctorRepository.deleteById(doctor.getId());
		return "record deleted successfully" ;
	}

	public List<DoctorDto> getAllDoctorDetails() {
		
		if(doctorRepository.findAll().size()==0)
		{
			throw new DoctorNotFoundException("No Doctor Aviliable");
		}
		
		return doctorRepository.findAll().stream()
				.map(x->mapToDto(x)).toList();
	}
	

}
