package org.dgtech.sms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_request")
public class UserRequest extends BaseEntity{
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userrequest_id")
    private Long id;
    private Integer rollNo;
    @Column(length=6)
    private String grade;
    @Column(length=3)
    private String section;
    @Column(length=3)
    private Long mediumId;
    @Column(length=3)
    private Long boardId;
    @Column(length=5)
    private String bloodGroup;
    @Column(length = 15)
    private String alternateMobile;
    @Column(length = 15)
    private String mobile;
    @Column(length = 30)
    private String fatherName;
    @Column(length = 30)
    private String motherName;
    @Column(length = 30)
    private String studentName;
    @Column(length=3)
    private Long schoolId;
    @Lob
    private String address;
    @Column(length = 20)
    private String admissionNo;
    @Column(length=3)
    private int studId;
    @Column(length = 20)
    private String examNo;
    @Column(length = 5)
    private String casteCat;
    @Column(length = 50)
    private String caste;
    @Column(length = 50)
    private String religion;
    @Column(length = 20)
    private String landLine;
    private String RTE;
    private String emisno;
    @Column(length = 20)
    private String gender;
    @Column(length=2,name="requested_type")
    private int requestedType;
    @Column(length=2,name="requested_status")
    private int requestStatus;
    private LocalDate dob;
    @Column(length = 20)
    private String aadhaarNo;
    @Column(length = 50)
    private String enrollmentNo;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="userRequest",orphanRemoval = true)
    private List<UserRequestErrors> errors=new ArrayList<UserRequestErrors>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
//	public Long getGradeId() {
//		return gradeId;
//	}
//	public void setGradeId(Long gradeId) {
//		this.gradeId = gradeId;
//	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
//	public Long getSectionId() {
//		return sectionId;
//	}
//	public void setSectionId(Long sectionId) {
//		this.sectionId = sectionId;
//	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Long getMediumId() {
		return mediumId;
	}
	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
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
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	public List<UserRequestErrors> getErrors() {
		return errors;
	}
	public void setErrors(List<UserRequestErrors> errors) {
		this.errors = errors;
	}
	public int getRequestedType() {
		return requestedType;
	}
	public void setRequestedType(int requestedType) {
		this.requestedType = requestedType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getAlternateMobile() {
		return alternateMobile;
	}
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public String getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}
	
	public void addUserRequestError(UserRequestErrors requestError) {
		errors.add(requestError);
		requestError.setUserRequest(this);
	}
	
	public void removeRequestError(UserRequestErrors requestError) {
		errors.remove(requestError);
		requestError.setUserRequest(null);
	}
	
    
	
    

}
