package org.dgtech.sms.sevice;

import java.util.List;

public interface UserRequestService {
	
	int createStudentParentAcc(List<Long> ids,Long schoolId)throws Exception;
	
	int createEmployeeAcc(List<Long> ids,Long schoolId) throws Exception;

}
