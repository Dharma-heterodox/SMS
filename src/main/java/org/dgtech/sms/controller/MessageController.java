package org.dgtech.sms.controller;

import java.util.List;

import org.dgtech.sms.model.AuthorizeDto;
import org.dgtech.sms.model.MessagesDto;
import org.dgtech.sms.sevice.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/msg")
public class MessageController {
	
	@Autowired
	private MessageService msgService;
	
	@GetMapping(value = "/pc/{grade}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<MessagesDto> getPCUserMsg(@PathVariable("grade")String selGrade)throws Exception{
		return msgService.getLast30Msg(selGrade);
	}
	
	
	@PostMapping(value = "/tc/create")
	@ResponseStatus(code = HttpStatus.OK)
	public String createMsg(@RequestBody MessagesDto dto){
		try {
			msgService.createMsg4App(dto);
		}catch(Exception ex) {
			return "Failed";
		}
		return "Success";
	}
	
	@GetMapping(value = "/tc/msg4app")
	@ResponseStatus(code = HttpStatus.OK)
	public List<MessagesDto> getMsg4Approval(){
		try {
			return msgService.msg4Approval();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value = "/tc/approvemsg")
	@ResponseStatus(code = HttpStatus.OK)
	public String approveMsg(@RequestBody AuthorizeDto dto) {
		try {
			msgService.approveMsgs(dto.getIds());
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Failed";
		}
		return "Success";
	}

}
