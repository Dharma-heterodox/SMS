package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.ParentDto;
import org.dgtech.sms.model.StudentDto;


public interface ParentService {

	ParentDto createParent(ParentDto parentDto);

	List<ParentDto> getAllParentByStudentId(Long studentId);
	
	ParentDto addStudent(Long parnetId, StudentDto studentDto);
	ParentDto getParentByMobile(String mobile)throws Exception;
}
