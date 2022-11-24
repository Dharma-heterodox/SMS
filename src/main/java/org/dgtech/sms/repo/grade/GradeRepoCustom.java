package org.dgtech.sms.repo.grade;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



public interface GradeRepoCustom {
	
	Map<String, String> getGradeMap(Long schoolId)throws Exception;
	
	List<String> getGradeList(Long schoolId)throws Exception;

}
