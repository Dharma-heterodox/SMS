package org.dgtech.sms.sevice;

import java.util.List;

public interface GradeService {

	List<String> getAllGrades(Long schoolId)throws Exception;
}
