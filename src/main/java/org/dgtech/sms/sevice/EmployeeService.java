package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;


public interface EmployeeService {

	EmployeeDto createEmployee(Long schoolId, EmployeeDto employee);
	List<EmployeeDto> getAllEmployeeBySchoolId(Long schoolId);
	EmployeeDto getEmployee(Long employeeId);
	boolean upload(Long schoolId, MultipartFile file);
	EmployeeDto getEmployeeByUserId(Long userId)throws Exception;
}
