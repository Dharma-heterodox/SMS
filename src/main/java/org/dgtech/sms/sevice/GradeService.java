package org.dgtech.sms.sevice;

import java.util.List;
import java.util.Map;

import org.dgtech.sms.model.GradeDto;


public interface GradeService {

	List<GradeDto> getAllGradesBySchoolId(Long schoolId)throws Exception ;
	GradeDto getByGrade(Long schoolId, String grade);
	GradeDto saveGradeBySchoolId(Long schoolId, GradeDto gradeDto);
	Map<String,String> getAllGradesBySchool(Long schoolId)throws Exception;

}
