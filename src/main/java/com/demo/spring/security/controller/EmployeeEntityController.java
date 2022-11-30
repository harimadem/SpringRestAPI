package com.demo.spring.security.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.security.dto.EmployeeDto;
import com.demo.spring.security.dto.EmployeeResponseDto;
import com.demo.spring.security.model.EmployeeEntity;
import com.demo.spring.security.serviceImpl.EmployeeServiceImpl;
import com.demo.spring.security.util.EmployeeExcelExporter;
import com.demo.spring.security.util.GeneratePdfReport;

@RestController
public class EmployeeEntityController {
	@Autowired
	private EmployeeServiceImpl empService;
	
	@PostMapping("/save")
	public EmployeeResponseDto<?> saveDetails(@RequestBody EmployeeDto dto){
		return empService.saveDetails(dto);
	}
	
	
	@GetMapping("/pdfReport")
	 public ResponseEntity<String> employeeReport() {

		List<EmployeeEntity> employees = empService.fetchAll();

        ByteArrayInputStream bis = GeneratePdfReport.employeesReport(employees);
        byte[] targetArray = new byte[bis.available()];
        System.out.println(targetArray.length);
        targetArray = bis.readAllBytes();
        String base64String = Base64.getEncoder().encodeToString(targetArray);
        System.out.println(base64String);
        //var headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=test.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
        return ResponseEntity
        		.ok()
        		.body(base64String);
    }
	
	@GetMapping("/excelReport")
	 public ResponseEntity<String> employeeExcelReport() throws IOException {
		
//		response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//        
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=employees" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);

        List<EmployeeEntity> employees = empService.fetchAll();
       
       EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(employees);
       ByteArrayInputStream bips = excelExporter.export();
       byte[] targetArray = new byte[bips.available()];
       targetArray = bips.readAllBytes();
       String base64String = Base64.getEncoder().encodeToString(targetArray);
       return ResponseEntity
       		.ok()
       		.body(base64String);
   }	

}
