package org.dgtech.sms.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
@ResponseBody
public class ResponseObj {
	
	private HttpStatus statuscode;
	
	private Object message;
	
	public ResponseObj(Object message,HttpStatus statuscode){
		this.statuscode=statuscode;
		this.message=message;
	}

	public HttpStatus getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(HttpStatus statuscode) {
		this.statuscode = statuscode;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	

}
