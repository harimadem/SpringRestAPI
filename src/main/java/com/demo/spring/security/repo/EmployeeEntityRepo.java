package com.demo.spring.security.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.security.model.EmployeeEntity;

@Repository
public interface EmployeeEntityRepo extends JpaRepository<EmployeeEntity,Long>{

}
