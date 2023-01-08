package org.dgtech.sms.controller;

import java.util.List;

import org.dgtech.sms.model.NotificationDto;
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
	public List<NotificationDto> getPCUserMsg(@PathVariable("grade")String selGrade)throws Exception{
		return msgService.getLast30Msg(selGrade);
	}
	
	
	@PostMapping(value = "/tc/create")
	@ResponseStatus(code = HttpStatus.OK)
	public String createMsg(@RequestBody NotificationDto dto)throws Exception{
		msgService.createMsg4App(dto);
		return "Success";
	}

}
