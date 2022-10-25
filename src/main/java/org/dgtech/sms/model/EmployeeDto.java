package org.dgtech.sms.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class EmployeeDto extends Person{

	private Long id;
	private String category;
	private String subjectTaking;
	private String qualification;
	private String designation;
	private String major;
	private LocalDate dateOfJoin;
	private String marritalStatus;
	private boolean active;
    private String classHandling;
    private int subCategory;
    private String subCategoryName;
    private Set<TeacherMappingDto> teacherMapping;
    private Long userId;
    
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public Set<TeacherMappingDto> getTeacherMapping() {
		return teacherMapping;
	}
	public void setTeacherMapping(Set<TeacherMappingDto> teacherMapping) {
		this.teacherMapping = teacherMapping;
	}
	public int getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubjectTaking() {
		return subjectTaking;
	}
	public void setSubjectTaking(String subjectTaking) {
		this.subjectTaking = subjectTaking;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassHandling() {
		return classHandling;
	}
	public void setClassHandling(String classHandling) {
		this.classHandling = classHandling;
	}
	public LocalDate getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(LocalDate dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public String getMarritalStatus() {
		return marritalStatus;
	}
	public void setMarritalStatus(String marritalStatus) {
		this.marritalStatus = marritalStatus;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
