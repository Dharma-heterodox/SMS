package org.dgtech.sms.model;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

public class StudentDto extends Person{

	private Long id;
    private String idNumber;
    private Integer rollNo;
    private Long gradeId;
    private String grade;
    private String section;
    private Long sectionId;
    private String bloodGroup;
    private String fatherName;
    private String motherName;
    private Set<ParentDto> parents;
    private boolean active;
    @NotEmpty
    private Long schoolId;
    private String admissionNo;
    private int studId;
    private String examNo;
    private String casteCat;
    private String caste;
    private String religion;
    private String landLine;
    private String RTE;
    private String emisno;
    private String enrollmentNo;
    private List<String> errors;
    private int requestType;
    private int childOrder;
    
    
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public String getCasteCat() {
		return casteCat;
	}
	public void setCasteCat(String casteCat) {
		this.casteCat = casteCat;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getRTE() {
		return RTE;
	}
	public void setRTE(String rTE) {
		RTE = rTE;
	}
	public String getEmisno() {
		return emisno;
	}
	public void setEmisno(String emisno) {
		this.emisno = emisno;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idCard) {
		this.idNumber = idCard;
	}
	public Integer getRollNo() {
		return rollNo;
	}
	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public Set<ParentDto> getParents() {
		return parents;
	}
	public void setParents(Set<ParentDto> parents) {
		this.parents = parents;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public int getChildOrder() {
		return childOrder;
	}
	public void setChildOrder(int childOrder) {
		this.childOrder = childOrder;
	}
	@Override
	public int hashCode() {
		if(this.id!=null) {
			return this.id.hashCode();
		}
		return this.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this.id == null)
			return false;
		StudentDto dto=(StudentDto)obj;
		if(dto.id==this.id || dto.id.equals(this.id))
			return true;
		
		return false;
	}
    
}
