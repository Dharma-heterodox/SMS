package org.dgtech.sms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dgtech.sms.model.AuthorizeDto;
import org.dgtech.sms.model.EmployeeDto;
import org.dgtech.sms.sevice.EmployeeService;
import org.dgtech.sms.sevice.UserRequestService;
import org.dgtech.sms.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/{schoolId}/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserRequestService userReqService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDto createEmployee(@PathVariable("schoolId") Long schoolId,
			@RequestBody EmployeeDto employeeDto) {
		return employeeService.createEmployee(schoolId, employeeDto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<EmployeeDto> getAllEmployeeBySchoolId(@PathVariable("schoolId") Long schoolId) {
		return employeeService.getAllEmployeeBySchoolId(schoolId);
	}
	
	@PostMapping(value="/upload")
	@ResponseStatus(HttpStatus.OK)
	public boolean upload(@PathVariable("schoolId") Long schoolId, @RequestParam("file") MultipartFile file) throws Exception {
		return employeeService.upload(schoolId, file);
	}
//	Created By : Dharma
//	Date:10-10-2020
//	Purpose: To retrieve employee details and teacher mapped class & subject .
	@GetMapping(value="/get/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDto getEmployee(@PathVariable ("userId")Long userId)throws Exception {
		EmployeeDto employee=null;
		try {
			employee=employeeService.getEmployeeByUserId(userId);
			if(employee!=null && employee.getCategory().equals(Roles.TEACHER.toString())) 
			{
				
			}
			
		}catch(Exception ex) {
			
		}
		
		return employee;
	}
	
	@PostMapping(value="/approve")
	@ResponseStatus(HttpStatus.OK)
	public int approve(@PathVariable("schoolId") Long schoolId, @RequestBody AuthorizeDto dto,HttpServletRequest req)throws Exception{
		return userReqService.createEmployeeAcc(dto.getIds(), schoolId,(String)req.getAttribute("userId"));
	}
}
