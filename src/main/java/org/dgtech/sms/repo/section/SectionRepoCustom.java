package org.dgtech.sms.repo.section;

import java.util.List;
import java.util.Map;

public interface SectionRepoCustom {
	
	Map<String,String> getGradeSectionMap(Long schoolId)throws Exception;
	
	List<String> getGradeSectionList(Long schoolId)throws Exception;

}
