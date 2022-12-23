package org.dgtech.sms.sevice.impl;

import java.util.List;

import org.dgtech.sms.entity.student.StudentParent;
import org.dgtech.sms.sevice.StudentParentService;
import org.springframework.stereotype.Service;

@Service
public class StudentParentServiceImpl implements StudentParentService {

//	@Autowired
//	private StudentParentRepo studentParentRepo;
	
	@Override
	public List<StudentParent> getStudentParent(Long studentId, Long parentId) {
		return null;
	}

	@Override
	public List<StudentParent> getAllByStudentId(Long studentId) {
		return null;
	}

	@Override
	public List<StudentParent> getAllByParentId(Long parentId) {
		return null;
	}

	@Override
	public StudentParent createStudentParent(StudentParent studentParent) {
		return null;
	}

}
