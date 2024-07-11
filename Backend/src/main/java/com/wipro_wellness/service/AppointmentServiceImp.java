package com.wipro_wellness.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro_wellness.Entity.Appointment;
import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.dto.AppointmentDto;
import com.wipro_wellness.exception.DoctorNotFoundException;
import com.wipro_wellness.exception.PatientNotFoundException;
import com.wipro_wellness.repository.AppointMentRepository;
import com.wipro_wellness.repository.DoctorRepository;

@Service
public class AppointmentServiceImp implements AppointmentService{
	
	@Autowired
	private AppointMentRepository appointMentRepository;
	@Autowired
	private PatientServiceImp patientServiceImp;
	@Autowired
	private DoctorServiceImp doctorServiceImp;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public AppointmentDto mapToDto(Appointment appointment) {
	    return modelMapper.map(appointment, AppointmentDto.class);
	}

	public Appointment mapToEntity(AppointmentDto appointment) {
	    return modelMapper.map(appointment, Appointment.class);
	}

	@Override
	public AppointmentDto bookAppointment(AppointmentDto appointment) {
		
		if(patientServiceImp.getPatientDetails(appointment.getPatientId())==null)
		{
			throw new PatientNotFoundException("Patient details not found");
		}
		if(doctorServiceImp.getDoctorDetails(appointment.getDoctorId())==null)
		{
			throw new PatientNotFoundException("Doctor details not found");
		}
		appointment.setDoctorName(doctorServiceImp.getDoctorDetails(appointment.getDoctorId()).getName());
	
		return mapToDto(appointMentRepository.save(mapToEntity(appointment)));
	}

	@Override
	public AppointmentDto viewAppointmentByPatient(long patientId) {
		
		return mapToDto(appointMentRepository.findByPatientId(patientId).orElseThrow(()->
		new PatientNotFoundException("No Patient is Found")));
	}

	@Override
	public List<AppointmentDto> viewAllApointMent(long doctorId) {
		
		if(appointMentRepository.findByDoctorId(doctorId).size()==0)
		{
			throw new DoctorNotFoundException("No Appointments");
		}
	
		return appointMentRepository.findByDoctorId(doctorId).stream().map(x->mapToDto(x)).toList();
	}

	@Override
	public String cancelAppointMent(long patientId) {
		
		if(patientServiceImp.getPatientDetails(patientId)==null)
		{
			throw new PatientNotFoundException("Patient doesn't exists");
		}
		Appointment patient = appointMentRepository.findByPatientId(patientId).get();
		
		patient.setStatus("Cancel");
		 appointMentRepository.save(patient);
		 return "your Appointment is cancelled";
	}
	
	

}
