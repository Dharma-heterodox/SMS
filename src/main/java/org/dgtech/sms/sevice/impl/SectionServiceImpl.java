package org.dgtech.sms.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dgtech.sms.entity.Section;
import org.dgtech.sms.model.SectionDto;
import org.dgtech.sms.repo.section.SectionRepo;
import org.dgtech.sms.sevice.SectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepo sectionRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SectionDto createSection(Long schoolId, SectionDto sectionDto) {
		Section section = modelMapper.map(sectionDto, Section.class);
		section.setSchoolId(String.valueOf(schoolId));
		section.setActive(true);
		sectionRepo.save(section);
		return modelMapper.map(section, SectionDto.class);
	}

	@Override
	public List<SectionDto> getAllSectionBySchoolId(Long schoolId) {
		List<Section> sections = sectionRepo.findAllBySchoolId(schoolId);
		return sections.stream().map(s -> modelMapper.map(s, SectionDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SectionDto> findAllBySchoolIdAndGrade(Long schoolId, String gradeId) {
		List<Section> sections = sectionRepo.findAllBySchoolIdAndGradeId(schoolId, gradeId);
		return sections.stream().map(s -> modelMapper.map(s, SectionDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public SectionDto getBySection(Long schoolId, String grade, String section) {
		Section sectionObj = sectionRepo.findAllBySchoolIdAndSection(schoolId, grade, section);
		if (sectionObj == null)
			return null;
		return modelMapper.map(sectionObj, SectionDto.class);
	}
	
	@Override
	public Map<String, List<SectionDto>> getAllBySchool(Long schoolId)throws Exception{
		List<Section> sections = sectionRepo.findAllBySchoolId(schoolId);
		Map<String, List<SectionDto>> dtoMap=new HashMap<String, List<SectionDto>>();
		sections.forEach(h -> {
			SectionDto dto=new SectionDto();
			dto.setActive(true);
//			dto.setGradeId(h.getGradeId());
//			dto.setId(h.getId());
			dto.setSection(h.getSection());
			if(dtoMap.containsKey(h.getGrade())) {
				List<SectionDto> dtoList=dtoMap.get(h.getGrade());
				dtoList.add(dto);
				dtoMap.put(h.getGrade(), dtoList);
			}else {
				List<SectionDto> dtoList=new ArrayList<SectionDto>();
				dtoList.add(dto);
				dtoMap.put(h.getGrade(), dtoList);
			}
			
		});
		return dtoMap;
	}

}
