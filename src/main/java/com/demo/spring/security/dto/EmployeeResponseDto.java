package com.demo.spring.security.dto;

public class EmployeeResponseDto<T> {
	private T responceData;
	private String status;
	private String code;
	private String error;
	private String message;
	public T getResponceData() {
		return responceData;
	}
	public void setResponceData(T responceData) {
		this.responceData = responceData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
