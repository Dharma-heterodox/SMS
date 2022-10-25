package org.dgtech.sms.model;

import java.time.LocalDate;
import java.util.List;

public class HomeworkDto {

	private Long id;
	private String title;
	private String description;
	private Long gradeId;
	private Long sectionId;
	private Long subjectId;
	private Long teacherId;
	private Long schoolId;
	private LocalDate homeworkDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean approved;
	private String s3URL;
	private List<String> s3Download;
	
	
	

	public String getS3URL() {
		return s3URL;
	}

	public void setS3URL(String s3url) {
		s3URL = s3url;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public LocalDate getHomeworkDate() {
		return homeworkDate;
	}

	public void setHomeworkDate(LocalDate homeworkDate) {
		this.homeworkDate = homeworkDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<String> getS3Download() {
		return s3Download;
	}

	public void setS3Download(List<String> s3Download) {
		this.s3Download = s3Download;
	}
	


}
