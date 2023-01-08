package org.dgtech.sms.sevice.impl;

import java.util.List;

import org.dgtech.sms.repo.grade.GradeRepo;
import org.dgtech.sms.sevice.GradeService;
import org.dgtech.sms.sevice.SectionService;
import org.dgtech.sms.sevice.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepo gradeRepo;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SectionService sectionService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<String> getAllGrades(Long schoolId) throws Exception {
		// TODO Auto-generated method stub
		return gradeRepo.getGradeList(schoolId);
	}

//	@Override
//	public List<GradeDto> getAllGradesBySchoolId(Long schoolId)throws Exception {
//		List<Grade> grades = gradeRepo.findAllBySchoolId(schoolId);
//		List<GradeDto> gradeDtos = grades.stream().map(g -> mapper.map(g, GradeDto.class)).collect(Collectors.toList());
//		List<GradeDto> gradeList = new ArrayList<GradeDto>();
//		Map<String, List<SectionDto>> sections=sectionService.getAllBySchool(schoolId);
//		Map<String,List<SubjectDto>> subjects=subjectService.getAllSubjects4Grade(schoolId);
//		if(gradeDtos != null) {
//			for(GradeDto gradeDto: gradeDtos) {
//				gradeDto.setSections(sections.get(gradeDto.getId()) );
//				gradeDto.setSubjects(subjects.get(gradeDto.getId()));
//				gradeList.add(gradeDto);
//			}
//		}
//		gradeList.sort((GradeDto g1,GradeDto g2)->g1.getGrade().compareTo(g2.getGrade()));
//		return gradeList;
//	}

//	@Override
//	public GradeDto saveGradeBySchoolId(Long schoolId, GradeDto gradeDto) {
//		Grade grade = mapper.map(gradeDto, Grade.class);
//		grade.setSchoolId(schoolId);
//		grade.setActive(true);
//		gradeRepo.save(grade);
//		return mapper.map(grade, GradeDto.class);
//	}

//	@Override
//	public GradeDto getByGrade(Long schoolId, String grade) {
//		Grade gradeObj = gradeRepo.findAllBySchoolIdAndGrade(schoolId, grade);
//		if (gradeObj == null)
//			return null;
//		return mapper.map(gradeObj, GradeDto.class);
//	}
	
	
	

}
