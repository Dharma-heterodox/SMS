package org.dgtech.sms.repo.section;

import java.util.Map;

public interface SectionRepoCustom {
	
	Map<String,String> getGradeSectionMap(Long schoolId)throws Exception;

}
