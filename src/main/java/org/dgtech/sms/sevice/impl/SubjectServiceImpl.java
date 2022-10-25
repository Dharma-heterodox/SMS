package org.dgtech.sms.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dgtech.sms.entity.Subject;
import org.dgtech.sms.model.SubjectDto;
import org.dgtech.sms.repo.SubjectRepo;
import org.dgtech.sms.sevice.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<SubjectDto> getAllSubjectsBySchoolId(Long schoolId) {
		List<Subject> subjects = subjectRepo.findAllBySchoolId(schoolId);
		if(CollectionUtils.isEmpty(subjects)) {
			return null;
		}
		return subjects.stream().map(g -> mapper.map(g, SubjectDto.class)).collect(Collectors.toList());
	}

	@Override
	public SubjectDto createSubject(Long schoolId, SubjectDto subjectDto) {
		Subject subject = mapper.map(subjectDto, Subject.class);
		subject.setSchoolId(schoolId);
		subject.setActive(true);
		subjectRepo.save(subject);
		return mapper.map(subject, SubjectDto.class);
	}

	@Override
	public List<SubjectDto> findAllBySchoolIdAndGradeId(Long schoolId, Long gradeId) {
		List<Subject> subjects = new ArrayList<Subject>();
		if(gradeId == null) {
			subjects = subjectRepo.findAllBySchoolId(schoolId);
		} else {
			subjects = subjectRepo.findAllBySchoolIdAndGradeId(schoolId, gradeId);
		}
		if(CollectionUtils.isEmpty(subjects)) {
			return null;
		}
		return subjects.stream().map(g -> mapper.map(g, SubjectDto.class)).collect(Collectors.toList());
	}

	@Override
	public SubjectDto getSubject(Long schoolId, Long gradeId, String subjectName) {
		Subject subject = null;
		if(gradeId == null) {
			subject = subjectRepo.findAllBySchoolIdAndSubject(schoolId, subjectName);
		} else {
			subject = subjectRepo.findSubject(schoolId, gradeId, subjectName);
		}
		if(subject == null) {
			return null;
		}
		return mapper.map(subject, SubjectDto.class);
		
	}
	
	@Override
	public Map<String,List<SubjectDto>> getAllSubjects4Grade(Long schoolId) {
		Map<String,List<SubjectDto>> dtoMap=new HashMap<String,List<SubjectDto>>();
		List<Subject> subjects = subjectRepo.findAllBySchoolId(schoolId);
		subjects.forEach(h -> {
			SubjectDto dto=new SubjectDto();
			dto.setActive(true);
//			dto.setId(h.getId());
			dto.setDescription(h.getDescription());
//			dto.setGradeId(h.getGradeId());
			dto.setSubjectName(h.getSubjectName());
			dto.setTitle(h.getTitle());
			if(dtoMap.containsKey(h.getGradeId())){
				List<SubjectDto> dtoList=dtoMap.get(h.getGradeId());
				dtoList.add(dto);
			}else {
				List<SubjectDto> dtoList=new ArrayList<SubjectDto>();
				dtoList.add(dto);
				dtoMap.put(h.getGradeId(), dtoList);
			}
		});
		return dtoMap;
		
	}
	

}
