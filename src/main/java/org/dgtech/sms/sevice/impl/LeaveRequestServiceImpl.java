package org.dgtech.sms.sevice.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dgtech.sms.entity.LeaveRequest;
import org.dgtech.sms.model.LeaveRequestDto;
import org.dgtech.sms.repo.LeaveRequestRepo;
import org.dgtech.sms.sevice.LeaveRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	private LeaveRequestRepo leaveRequestRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<LeaveRequestDto> getAllLeaveRequestsBySchoolId(Long schoolId) {
		List<LeaveRequest> leaveRequests = leaveRequestRepo.findAllBySchoolId(schoolId);
		return leaveRequests.stream().map(f -> mapper.map(f, LeaveRequestDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public List<LeaveRequestDto> getAllLeaveRequestsByStudentId(Long studentId) {
		List<LeaveRequest> leaveRequests = leaveRequestRepo.findAllByStudentId(studentId);
		return leaveRequests.stream().map(f -> mapper.map(f, LeaveRequestDto.class)).collect(Collectors.toList());
	}

	@Override
	public LeaveRequestDto createLeaveRequest(final Long schoolId, LeaveRequestDto f) {
		LeaveRequest leaveRequest = mapper.map(f, LeaveRequest.class);
		leaveRequest.setSchoolId(schoolId);
		leaveRequestRepo.save(leaveRequest);
		return mapper.map(leaveRequest, LeaveRequestDto.class);
	}

}
