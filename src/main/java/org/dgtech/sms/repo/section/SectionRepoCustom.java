package org.dgtech.sms.repo.section;

import java.util.List;
import java.util.Map;

public interface SectionRepoCustom {
	
	
	List<String> getGradeSectionList(Long schoolId)throws Exception;

}
