package com.wipro_wellness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wipro_wellness.Entity.Admin;
import com.wipro_wellness.Entity.Doctor;
import com.wipro_wellness.Entity.Patient;
import com.wipro_wellness.repository.AdminRepository;
import com.wipro_wellness.repository.DoctorRepository;
import com.wipro_wellness.repository.PatientRepository;




@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AdminRepository adminRepository;

   
 

		

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByUsername(username).orElse(null);
        if (doctor != null) {
            return doctor;
        }
        Patient patient = patientRepository.findByUsername(username).orElse(null);
        if (patient != null) {
            return patient;
        }
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null) {
            return admin;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

