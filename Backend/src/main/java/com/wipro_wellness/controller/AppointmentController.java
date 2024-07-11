package com.wipro_wellness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.wipro_wellness.Entity.Appointment;
import com.wipro_wellness.dto.AppointmentDto;
import com.wipro_wellness.service.AppointmentServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	
	@Autowired
	private AppointmentServiceImp appointmentServiceImp;
	
	@PostMapping("/book")
	@PreAuthorize("hasRole('PATIENT')")
	public AppointmentDto scheduleAppointMent(@RequestBody AppointmentDto appointment)
	{
		return appointmentServiceImp.bookAppointment(appointment);
	}
	
	@GetMapping("/viewAppointment/{patientId}")
	public AppointmentDto viewAppointmentByPatient(@PathVariable("patientId") long patientId)
	{
		return appointmentServiceImp.viewAppointmentByPatient(patientId);
	}
	
	@GetMapping("/allAppointment/{doctorId}/{date}")
	 @PreAuthorize("hasRole('DOCTOR')")
	public List<AppointmentDto>viewAllAppointment(@PathVariable("doctorId") long doctorId,@PathVariable("date") String date)
	{
		return appointmentServiceImp.viewAllApointMent(doctorId);
	}
	
	@DeleteMapping("/cancel/{patientId}")
	public String cancelAppointment(@PathVariable("patientId") long patientId)
	{
		return appointmentServiceImp.cancelAppointMent(patientId);
	}
	
	

}
