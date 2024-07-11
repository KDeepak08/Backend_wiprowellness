package com.wipro_wellness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro_wellness.Entity.Admin;
import com.wipro_wellness.Entity.Doctor;

import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.Entity.User;
import com.wipro_wellness.dto.AuthResponse;
import com.wipro_wellness.exception.PatientNotFoundException;
import com.wipro_wellness.repository.AdminRepository;
import com.wipro_wellness.repository.DoctorRepository;
import com.wipro_wellness.repository.PatientRepository;
import com.wipro_wellness.service.JwtService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
	  
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @PostMapping("/login")
	    public ResponseEntity<AuthResponse>login(@RequestBody User user) {
		
		if(user.getRole().equals("ADMIN"))
		{
			Admin admin = adminRepository.findByUsername(user.getUsername()).orElse(null);
			if(admin==null)
			{
				throw new PatientNotFoundException("invalid Username");
			}
			boolean flag=passwordEncoder.matches(user.getPassword(), adminRepository.findByUsername(user.getUsername()).get().getPassword());
			
			if(!flag)
			{
			  throw new PatientNotFoundException("invalid password");
			}
			AuthResponse auth=new AuthResponse();
			auth.setToken(jwtService.generateTokenp(user));
			
			return ResponseEntity.ok(auth);
		}
		else if(user.getRole().equals("PATIENT"))
		{
			Patient patient= patientRepository.findByUsername(user.getUsername()).orElse(null);
			if(patient==null)
			{
				throw new PatientNotFoundException("invalid Username");
			}
			
			boolean flag=passwordEncoder.matches(user.getPassword(), patientRepository.findByUsername(user.getUsername()).get().getPassword());
			
			if(!flag)
			{
			  throw new PatientNotFoundException("invalid password");
			}
			AuthResponse auth=new AuthResponse();
			auth.setToken(jwtService.generateTokenp(user));
			
			return ResponseEntity.ok(auth);
		}
		else if(user.getRole().equals("DOCTOR"))
		{
			Doctor doctor=doctorRepository.findByUsername(user.getUsername()).orElse(null);
			if(doctor==null)
			{
				throw new PatientNotFoundException("invalid Username");
			}
			boolean flag=passwordEncoder.matches(user.getPassword(), doctorRepository.findByUsername(user.getUsername()).get().getPassword());
			
			if(!flag)
			{
			  throw new PatientNotFoundException("invalid password");
			}
			AuthResponse auth=new AuthResponse();
			auth.setToken(jwtService.generateTokenp(user));
			
			return ResponseEntity.ok(auth);
		}
			
		 throw new PatientNotFoundException("invalid data");
		 
		

	}

}