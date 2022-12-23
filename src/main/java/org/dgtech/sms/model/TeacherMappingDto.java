package org.dgtech.sms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TeacherMappingDto {

	private Long id;
	private Long teacherId;
	private String teacherName;
	private String gradeId;
	private String sectionId;
	private String subjectId;
	private String subjectName;
	private String academicYear;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private boolean classTeacher;
	private boolean active;
	private String grade;
	private String section;
	private List<String> subjectList=new ArrayList<String>();
	
	
	
	public List<String> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<String> subjectList) {
		this.subjectList = subjectList;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacher) {
		this.teacherId = teacher;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public LocalDate getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}
	public boolean isClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(boolean classTeacher) {
		this.classTeacher = classTeacher;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeacherMappingDto other = (TeacherMappingDto) obj;
		if(this.sectionId.equals(other.getSectionId())||this.sectionId==other.getSectionId()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Organization [id=" + id + ", section=" + sectionId + ", subjectName="
				 + ", active=" + active + "]";
	}
}
