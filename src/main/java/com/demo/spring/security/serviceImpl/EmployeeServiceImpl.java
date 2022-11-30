package com.demo.spring.security.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.security.dto.EmployeeDto;
import com.demo.spring.security.dto.EmployeeResponseDto;
import com.demo.spring.security.model.EmployeeEntity;
import com.demo.spring.security.repo.EmployeeEntityRepo;
import com.demo.spring.security.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeEntityRepo repo;

	@Override
	public EmployeeResponseDto saveDetails(EmployeeDto dto) {
		EmployeeEntity eEntity = new EmployeeEntity();
		EmployeeResponseDto empRes = new EmployeeResponseDto();
		try {
			eEntity.setContactNumber(dto.getContactNumber());
			eEntity.setEmailId(dto.getEmailId());
			eEntity.setFirstName(dto.getFirstName());
			eEntity.setLastName(dto.getLastName());
			eEntity.setSalary(dto.getSalary());
			repo.save(eEntity);
			empRes.setMessage("data stored");
		}catch (Exception e){
			e.printStackTrace();
		}
		return empRes;
	}

	@Override
	public EmployeeResponseDto fetchDetails(Long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeEntity> fetchAll() {
		
		
		
		return repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
