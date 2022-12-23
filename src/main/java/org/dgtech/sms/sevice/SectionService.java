package org.dgtech.sms.sevice;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.dgtech.sms.model.SectionDto;


public interface SectionService {

	SectionDto createSection(Long schoolId, @Valid SectionDto sectionDto);

	List<SectionDto> getAllSectionBySchoolId(Long schoolId);
	List<SectionDto> findAllBySchoolIdAndGrade(Long schoolId, String grade);

	SectionDto getBySection(Long schoolId, String grade, String section);
	
	Map<String, List<SectionDto>> getAllBySchool(Long schoolId)throws Exception;
}
