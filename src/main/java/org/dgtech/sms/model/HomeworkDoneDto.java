package org.dgtech.sms.model;

import java.time.LocalDate;
import java.util.List;

//Created By : Dharma
//Date : 13-09-2020
//Purpose : To Interact with Entity and front-end for finished students homework

public class HomeworkDoneDto {
	
	private Long id; 
	private String title;
	private String description;
	private Long gradeId;
	private Long sectionId;
	private Long subjectId;
	private Long teacherId;
	private Long schoolId;
	private Long studentId;
	private LocalDate homeworkDate;
//	private byte[] file;
	private boolean verified;
	private Long teacherHomeworkId ;
	private String rating;
	private String s3URL;
	private String studentName;
	private Integer rollNo;
	private List<String> s3Download;
	
	
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getRollNo() {
		return rollNo;
	}
	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	public String getS3URL() {
		return s3URL;
	}
	public void setS3URL(String s3url) {
		s3URL = s3url;
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
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public LocalDate getHomeworkDate() {
		return homeworkDate;
	}
	public void setHomeworkDate(LocalDate homeworkDate) {
		this.homeworkDate = homeworkDate;
	}
//	public byte[] getFile() {
//		return file;
//	}
//	public void setFile(byte[] file) {
//		this.file = file;
//	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Long getTeacherHomeworkId() {
		return teacherHomeworkId;
	}
	public void setTeacherHomeworkId(Long teacherHomeworkId) {
		this.teacherHomeworkId = teacherHomeworkId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public List<String> getS3Download() {
		return s3Download;
	}
	public void setS3Download(List<String> s3Download) {
		this.s3Download = s3Download;
	}
	
	

}
