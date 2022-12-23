package org.dgtech.sms.entity.student;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Person;

@Entity
@Table(name="student",
uniqueConstraints =@UniqueConstraint(columnNames= {"admissionNo"}))
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
	@Column(length=12)
    private String idNumber;
    private Integer rollNo;
    @Column(length=6)
    private String grade;
    @Column(length=4)
    private String section;
    private Long mediumId;
    private Long boardId;
    private String bloodGroup;
    @Column(length = 30)
    private String emergencyContactNo;
    @Column(length = 30)
    private String fatherName;
    @Column(length = 30)
    private String motherName;
    @Transient
    private Set<Parent> parents;
    private Long schoolId;
    @Lob
    private String address;
    @Column(length = 20)
    private String admissionNo;
    private boolean active;
    
    private int studId;
    @Column(length = 20)
    private String examNo;
    @Column(length = 50)
    private String casteCat;
    @Column(length = 50)
    private String caste;
    @Column(length = 50)
    private String religion;
    @Column(length = 20)
    private String landLine;
    private String RTE;
    private String emisno;
    @ManyToOne(fetch = FetchType.EAGER)
    private Parent parent;
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    private StudentSectionRecord sectionRecord;
    @Column(name="child_order",columnDefinition = "integer default 1")
    private int childOrder;
    @OneToOne(cascade = CascadeType.ALL)
    
    
    
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

	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idCard) {
		this.idNumber = idCard;
	}
	public Long getMediumId() {
		return mediumId;
	}
	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
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
	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}
	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Set<Parent> getParents() {
		return parents;
	}
	public void setParents(Set<Parent> parents) {
		this.parents = parents;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentId=" + idNumber + ", grade=" + grade + ", section=" + section
				+ ", bloodGroup=" + bloodGroup + ", emergencyContactNo="
				+ emergencyContactNo + ", active=" + active + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student )) return false;
        return id != null && id.equals(((Student) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public StudentSectionRecord getSectionRecord() {
		return sectionRecord;
	}
	public void setSectionRecord(StudentSectionRecord sectionRecord) {
		this.sectionRecord = sectionRecord;
	}
	public int getChildOrder() {
		return childOrder;
	}
	public void setChildOrder(int childOrder) {
		this.childOrder = childOrder;
	}
	
	
	
    
    
}
