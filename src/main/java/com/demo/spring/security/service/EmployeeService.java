package com.demo.spring.security.service;

import java.util.List;

import com.demo.spring.security.dto.EmployeeDto;
import com.demo.spring.security.dto.EmployeeResponseDto;
import com.demo.spring.security.model.EmployeeEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {
	EmployeeResponseDto saveDetails(EmployeeDto dto);

	EmployeeResponseDto fetchDetails(Long id, HttpServletRequest request);

	List<EmployeeEntity> fetchAll();

	void delete(Long id);
	
	

}
