package org.dgtech.sms.entity.consent;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.dgtech.sms.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "Consent_Master")
public class ConsentMaster extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Consent_id")
	private Long id;
	@Length(min=5,max = 30 , message = "*Your title characters must be between 5 to 30")
    @NotEmpty(message = "*Please provide a title")
	@Column(length=30)
	private String title;
	@NotEmpty(message = "*Please provide a description")
	@Lob
	private String description;
	@Column(name="start_time")
	private LocalTime startTime;
	@Column(name="end_time")
	private LocalTime endTime;
	@Column(name="accepted_count")
	private int acceptedCount;
	@Column(name="rejected_count")
	private int rejectedCount;
	@Column(name="not_answered_count",length=2)
	private int notAnsweredCount;
	@Column(name="consent_for")
	@Lob
	private String consentFor;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getConsentFor() {
		return consentFor;
	}
	public void setConsentFor(String consentFor) {
		this.consentFor = consentFor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public int getAcceptedCount() {
		return acceptedCount;
	}
	public void setAcceptedCount(int acceptedCount) {
		this.acceptedCount = acceptedCount;
	}
	public int getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(int rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	public int getNotAnsweredCount() {
		return notAnsweredCount;
	}
	public void setNotAnsweredCount(int notAnsweredCount) {
		this.notAnsweredCount = notAnsweredCount;
	}
	
	
	

}
