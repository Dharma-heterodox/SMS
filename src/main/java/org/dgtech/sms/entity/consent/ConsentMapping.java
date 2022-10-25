package org.dgtech.sms.entity.consent;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Consent_Mapping")
public class ConsentMapping {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	@Column(name = "consent_id")
	private Long consentId;
	@Column(name = "parent_id")
	private Long parentId;
	@Column(name = "student_id")
	private Long studentId;
	@Column(name = "section_id")
	private Long sectionId;
	@Column(name = "consent_reply", columnDefinition = "integer default 0",length=2)
	private int consentReply;
	@Column(name="start_time")
	private LocalTime startTime;
	@Column(name="end_time")
	private LocalTime endTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getConsentId() {
		return consentId;
	}
	public void setConsentId(Long consentId) {
		this.consentId = consentId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public int getConsentReply() {
		return consentReply;
	}
	public void setConsentReply(int consentReply) {
		this.consentReply = consentReply;
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
	
	
	
	

}
