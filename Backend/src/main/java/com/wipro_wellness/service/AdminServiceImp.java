package com.wipro_wellness.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro_wellness.Entity.Admin;
import com.wipro_wellness.dto.AdminDto;
import com.wipro_wellness.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService{
	
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AdminDto mapToDto(Admin admin)
	{
		return modelMapper.map(admin, AdminDto.class);
	}
	
	public Admin mapToEntity(AdminDto admin)
	{
		return modelMapper.map(admin, Admin.class);
	}

	@Override
	public AdminDto addAdmin(AdminDto admin) {
		admin.setPassword(passwordEncoder.encode(mapToEntity(admin).getPassword()));
		return mapToDto(adminRepository.save(mapToEntity(admin)));
	}
	
	

}
