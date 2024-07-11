package com.wipro_wellness.controller;

import java.util.List;

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

import com.wipro_wellness.dto.DoctorDto;
import com.wipro_wellness.exception.DoctorNotFoundException;
import com.wipro_wellness.service.DoctorServiceImp;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
   @Autowired
	private DoctorServiceImp doctorServiceImp;
   
   
   @PostMapping("/adddoctor")
   @PreAuthorize("hasRole('ADMIN')")
   public DoctorDto addDoctorDetails( @RequestBody DoctorDto doctor)
   {
	   return doctorServiceImp.addDetails(doctor);
   }
   @PutMapping("/updatedoctor")
   @PreAuthorize("hasRole('ADMIN')")
   public DoctorDto update(@Valid @RequestBody DoctorDto doctor)
   {
	   return doctorServiceImp.update(doctor);
   }
   
   @GetMapping("/getAllDoctors")
   public List<DoctorDto> getAllDoctor()
   {
	  return doctorServiceImp.getAllDoctorDetails();
   }
   @DeleteMapping("/{username}")
   public ResponseEntity<String> deleteDoctorRecord(@PathVariable String username) {
       try {
           String response = doctorServiceImp.deleteDoctorRecord(username);
           return ResponseEntity.ok(response);
       } catch (DoctorNotFoundException e) {
           return ResponseEntity.notFound().build();
       }
   }
  
   
}
