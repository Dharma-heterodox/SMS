package org.dgtech.sms.sevice;

import java.util.List;

public interface SectionService {

	List<String> getAllSection(Long schoolId)throws Exception;
}
