package com.wipro_wellness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wipro_wellness.filter.JwtAuthenticationFilter;
import com.wipro_wellness.service.UserDetailsServiceImp;



@Configuration
@EnableWebSecurity
public class SecurityConfig {


	
	private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    //private final CustomLogoutHandler logoutHandler;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
       // this.logoutHandler = logoutHandler;
    }
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//    	return http.csrf(a -> a.disable()).authorizeHttpRequests(a -> a
//    			.requestMatchers("/login","patient/MedicalHistory/{id}","admin/addAdmin",
//    					"appointment/viewAppointment/{patientId}","patient/cancel/{patientId}","doctor/getAllDoctors"
//                	,"/patient/**","/doctor/adddoctor").permitAll()
//               
//                .anyRequest().authenticated()
//                )
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
// 
    	return http.csrf(a -> a.disable()).authorizeHttpRequests(a -> a
    			.requestMatchers("/login","/patient/**","admin/addAdmin",
    					"appointment/**","doctor/getAllDoctors"
                	,"/doctor/adddoctor").permitAll()
               
                .anyRequest().authenticated()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
 
    }

    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}