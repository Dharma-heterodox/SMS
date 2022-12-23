package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.FeedBackDto;


public interface FeedBackService {

	List<FeedBackDto> getAllFeedBacksBySchoolId(Long schoolId);
	List<FeedBackDto> getAllFeedbacksByStudentId(Long schoolId, Long studentId);

	FeedBackDto createFeedBack(Long schoolId, FeedBackDto feedBacks);

}
