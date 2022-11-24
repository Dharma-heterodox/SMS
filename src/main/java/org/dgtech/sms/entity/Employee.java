package org.dgtech.sms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee",
uniqueConstraints = @UniqueConstraint(columnNames= {"employeeCode","email"}))
public class Employee extends Person {

    private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
	private String category;
	private String subjectTaking;
	private String qualification;
	private String designation;
	private String major;
	@Lob
    private String address;
	private LocalDate dateOfJoin;
	@Column(length=50)
	private String marritalStatus;
	private Long schoolId;
	private boolean active;
	private Long userId;
	private int subCategory;
	private int employeeCode;
	
	
	
	public int getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public void setCategory(String employeeType) {
		this.category = employeeType;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(int employeeCode) {
		this.employeeCode = employeeCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
//		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((subjectTaking == null) ? 0 : subjectTaking.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
//		if (category == null) {
//			if (other.category != null)
//				return false;
//		} else if (!category.equals(other.category))
//			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (subjectTaking == null) {
//			if (other.subjectTaking != null)
//				return false;
//		} else if (!subjectTaking.equals(other.subjectTaking))
//			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeType=" + category + ", subjectTaking=" + subjectTaking + "]";
	}
	
	
}
