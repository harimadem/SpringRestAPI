package com.demo.spring.security.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.spring.security.model.EmployeeEntity;

public class EmployeeExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<EmployeeEntity> employees;
     
    public EmployeeExcelExporter(List<EmployeeEntity> employees) {
        this.employees = employees;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "First Name", style);       
        createCell(row, 2, "Last Name", style);    
        createCell(row, 3, "Email Id", style);
        createCell(row, 4, "Contact Num", style);
        createCell(row, 5, "Salary", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (EmployeeEntity employee : employees) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, employee.getId(), style);
            createCell(row, columnCount++, employee.getFirstName(), style);
            createCell(row, columnCount++, employee.getLastName(), style);
            createCell(row, columnCount++, employee.getEmailId(), style);
            createCell(row, columnCount++, employee.getContactNumber(), style);
            createCell(row, columnCount++, employee.getSalary(), style);
             
        }
    }
     

    	public ByteArrayInputStream export() throws IOException {
        writeHeaderLine();
        writeDataLines();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();
         
        return new ByteArrayInputStream(bos.toByteArray());
        
         
    }

}
