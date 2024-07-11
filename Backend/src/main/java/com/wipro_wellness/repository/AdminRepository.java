package com.wipro_wellness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro_wellness.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByUsername(String username);

}
