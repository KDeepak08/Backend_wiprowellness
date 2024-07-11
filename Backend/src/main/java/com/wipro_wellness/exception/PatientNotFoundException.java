package com.wipro_wellness.exception;

public class PatientNotFoundException extends RuntimeException{
	public PatientNotFoundException(String message) {
        super(message);
    }

}
