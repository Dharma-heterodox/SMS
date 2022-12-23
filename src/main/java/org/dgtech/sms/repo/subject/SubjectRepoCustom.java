package org.dgtech.sms.repo.subject;

import java.util.List;
import java.util.Map;

public interface SubjectRepoCustom {
	
	Map<String,String> getGradeSubjectMap(Long schoolId)throws Exception;
	
	List<String> getSubjectIdMap(Long schoolId)throws Exception;

}
