package org.dgtech.sms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Notification extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
	private Long id;
	@Column(length = 30)
	private String notificationType;
	@Column(length = 100)
	private String fromAddr;
	@Column(length = 100)
	private String toAddr;
	@Column(length = 100)
	private String cc;
	@Column(length = 100)
	private String bcc;
	@Lob
	private String body;
	@Column(length = 50)
	private String status;
	@Column(length = 100)
	private String errorMessage;
	@Column(length = 100)
	private String formName;
	private Long referenceId;
	private LocalDate msgDate;
	
	
	private String listedGrades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getListedGrades() {
		return listedGrades;
	}
	public void setListedGrades(String listedGrades) {
		this.listedGrades = listedGrades;
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
