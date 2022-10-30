package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.LeaveRequestDto;


public interface LeaveRequestService {

	List<LeaveRequestDto> getAllLeaveRequestsBySchoolId(Long schoolId);

	LeaveRequestDto createLeaveRequest(Long schoolId, LeaveRequestDto leaveRequests);
	
	List<LeaveRequestDto> getAllLeaveRequestsByStudentId(Long studentId);
}
