package org.dgtech.sms.Exception;

public final class SMSException extends Throwable{
	
	public SMSException() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public SMSException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		return "SMS Exception,contact admin";
	}

}
