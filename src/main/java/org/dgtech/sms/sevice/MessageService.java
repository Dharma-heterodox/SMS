package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.NotificationDto;

public interface MessageService {
	
	int createMsg4App(NotificationDto dto);
	
	List<NotificationDto> getLast30Msg(String selGrade)throws Exception;
	

}
