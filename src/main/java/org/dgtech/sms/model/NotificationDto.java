package org.dgtech.sms.model;

import java.time.LocalDate;

public class NotificationDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String notificationType;
	private String fromAddr;
	private String toAddr;
	private String cc;
	private String bcc;
	private String body;
	private String status;
	private String errorMessage;
	private String response;
	private String errorCode;
	private String formName;
	private Long referenceId;
	private LocalDate msgDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(LocalDate msgDate) {
		this.msgDate = msgDate;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String from) {
		this.fromAddr = from;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String to) {
		this.toAddr = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
}
