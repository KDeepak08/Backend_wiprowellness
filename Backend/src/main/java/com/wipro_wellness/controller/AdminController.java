package com.wipro_wellness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro_wellness.Entity.Admin;
import com.wipro_wellness.dto.AdminDto;
import com.wipro_wellness.service.AdminServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminServiceImp adminServiceImp;
	
	@PostMapping("/addAdmin")
	public AdminDto addAdmin(@RequestBody AdminDto admin)
	{
		return  adminServiceImp.addAdmin(admin);
	}

}
