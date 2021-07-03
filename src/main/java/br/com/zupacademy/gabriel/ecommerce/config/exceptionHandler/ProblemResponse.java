package br.com.zupacademy.gabriel.ecommerce.config.exceptionHandler;

import java.time.LocalDateTime;

public class ProblemResponse {
	
	private String field;
	private String message;
	private LocalDateTime timestamp;
	
	
	public ProblemResponse(String field, String message, LocalDateTime timestamp) {
		super();
		this.field = field;
		this.message = message;
		this.timestamp = timestamp;
	}


	public String getField() {
		return field;
	}


	public String getMessage() {
		return message;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
	

}
