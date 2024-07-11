package com.wipro_wellness.service;

import java.util.List;

import com.wipro_wellness.Entity.Appointment;
import com.wipro_wellness.dto.AppointmentDto;

public interface AppointmentService {
	
	public AppointmentDto bookAppointment(AppointmentDto appointment);
	
	public AppointmentDto viewAppointmentByPatient(long patientId);
	
	public List<AppointmentDto> viewAllApointMent(long doctorId);
	
	public String cancelAppointMent(long patientId);

}
