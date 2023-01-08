package org.dgtech.sms.repo.notifygrade;

import java.util.List;

import org.dgtech.sms.entity.NotifyGrade;

public interface NotifyGradesCustomRepo {
	
	List<Long> msgIdValue(String[] grades)throws Exception;

}
