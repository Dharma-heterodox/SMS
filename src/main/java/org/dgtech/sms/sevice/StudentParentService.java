package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.entity.student.StudentParent;


public interface StudentParentService {

	List<StudentParent> getStudentParent(Long studentId, Long parentId);
	List<StudentParent> getAllByStudentId(Long studentId);
	List<StudentParent> getAllByParentId(Long parentId);
	StudentParent createStudentParent(StudentParent studentParent);
}
