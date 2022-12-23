package org.dgtech.sms.sevice;

import org.dgtech.sms.Exception.SMSException;
import org.dgtech.sms.model.LoginDto;
import org.dgtech.sms.model.PCAuthObjects;

public interface UserAuthServices {
	
	Object[] loginPCUser(LoginDto dto)throws SMSException,Exception;
	
	 void putUserInMap(Long userId,PCAuthObjects userObj)throws Exception;
	 
	 void deletePCAuthUser(Long userId)throws Exception;

}
