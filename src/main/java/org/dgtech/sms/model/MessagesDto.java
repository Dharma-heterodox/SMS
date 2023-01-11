package org.dgtech.sms.model;

public class MessagesDto {
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String msgDate;
	private String createdBy;
	private String stdSelected;
	private String msgBody;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getStdSelected() {
		return stdSelected;
	}
	public void setStdSelected(String stdSelected) {
		this.stdSelected = stdSelected;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	
	
	

}
