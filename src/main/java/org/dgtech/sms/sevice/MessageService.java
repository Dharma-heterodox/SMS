package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.MessagesDto;

public interface MessageService {
	
	int createMsg4App(MessagesDto dto);
	
	List<MessagesDto> getLast30Msg(String selGrade)throws Exception;
	
	int approveMsgs(List<Long> ids)throws Exception;
	
	List<MessagesDto> msg4Approval()throws Exception;
	

}
