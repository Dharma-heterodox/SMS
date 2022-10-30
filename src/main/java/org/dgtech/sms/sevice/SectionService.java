package org.dgtech.sms.sevice;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.dgtech.sms.model.SectionDto;


public interface SectionService {

	SectionDto createSection(Long schoolId, @Valid SectionDto sectionDto);

	List<SectionDto> getAllSectionBySchoolId(Long schoolId);
	List<SectionDto> findAllBySchoolIdAndGradeId(Long schoolId, Long gradeId);

	SectionDto getBySection(Long schoolId, Long gradeId, String section);
	
	Map<String, List<SectionDto>> getAllBySchool(Long schoolId)throws Exception;
}
