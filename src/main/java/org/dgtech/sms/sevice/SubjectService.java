package org.dgtech.sms.sevice;

import java.util.List;
import java.util.Map;

import org.dgtech.sms.model.SubjectDto;


public interface SubjectService {

	SubjectDto createSubject(Long schoolId, SubjectDto subjectDto);

	List<SubjectDto> getAllSubjectsBySchoolId(Long schoolId);
	List<SubjectDto> findAllBySchoolIdAndGradeId(Long schoolId, Long gradeId);
	SubjectDto getSubject(Long schoolId, Long gradeId, String subject);
	Map<String,List<SubjectDto>> getAllSubjects4Grade(Long schoolId);
}
