package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findAllBySchoolId(Long schoolId);
	
	List<LeaveRequest> findAllByStudentId(Long studentId);

}
