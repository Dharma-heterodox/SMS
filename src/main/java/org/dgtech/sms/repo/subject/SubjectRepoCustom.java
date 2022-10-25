package org.dgtech.sms.repo.subject;

import java.util.Map;

public interface SubjectRepoCustom {
	
	Map<String,String> getGradeSubjectMap(Long schoolId)throws Exception;
	
	Map<Long,String> getSubjectIdMap(Long schoolId)throws Exception;

}
