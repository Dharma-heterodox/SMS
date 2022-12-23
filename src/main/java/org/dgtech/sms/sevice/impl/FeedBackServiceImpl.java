package org.dgtech.sms.sevice.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dgtech.sms.entity.Feedback;
import org.dgtech.sms.model.FeedBackDto;
import org.dgtech.sms.repo.FeedBackRepo;
import org.dgtech.sms.sevice.FeedBackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	private FeedBackRepo feedbackRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<FeedBackDto> getAllFeedBacksBySchoolId(Long schoolId) {
		List<Feedback> feedbacks = feedbackRepo.findAllBySchoolId(schoolId);
		return feedbacks.stream().map(f -> mapper.map(f, FeedBackDto.class)).collect(Collectors.toList());
	}

	@Override
	public FeedBackDto createFeedBack(final Long schoolId, FeedBackDto f) {
		Feedback feedback = mapper.map(f, Feedback.class);
		feedback.setActive(true);
		feedback.setSchoolId(schoolId);
		feedbackRepo.save(feedback);
		return mapper.map(feedback, FeedBackDto.class);
	}

	@Override
	public List<FeedBackDto> getAllFeedbacksByStudentId(Long schoolId, Long studentId) {
		List<Feedback> feedbacks = feedbackRepo.findAllByStudentId(studentId);
		return feedbacks.stream().map(f -> mapper.map(f, FeedBackDto.class)).collect(Collectors.toList()); 
	}

}
