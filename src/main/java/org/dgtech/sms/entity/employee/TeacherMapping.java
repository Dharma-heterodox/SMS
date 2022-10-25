package org.dgtech.sms.entity.employee;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.dgtech.sms.entity.BaseEntity;

@Entity
public class TeacherMapping extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_mapping_id")
    private Long id;
	private Long teacherId;
	private String gradeId;
	private String sectionId;
	private String subjectId;
	private String academicYear;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private boolean classTeacher;
	private Long schoolId;
	private boolean active;
	private String grade;
	private String subject;
	private String section;
	
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String grade) {
		gradeId = grade;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String section) {
		this.sectionId = section;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subject) {
		this.subjectId = subject;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
		result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + (classTeacher ? 1231 : 1237);
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((schoolId == null) ? 0 : schoolId.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeacherMapping other = (TeacherMapping) obj;
		if (gradeId == null) {
			if (other.gradeId != null)
				return false;
		} else if (!gradeId.equals(other.gradeId))
			return false;
		if (academicYear == null) {
			if (other.academicYear != null)
				return false;
		} else if (!academicYear.equals(other.academicYear))
			return false;
		if (active != other.active)
			return false;
		if (classTeacher != other.classTeacher)
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (schoolId == null) {
			if (other.schoolId != null)
				return false;
		} else if (!schoolId.equals(other.schoolId))
			return false;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (teacherId == null) {
			if (other.teacherId != null)
				return false;
		} else if (!teacherId.equals(other.teacherId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TeacherMapping [id=" + id + ", teacher=" + teacherId + ", Grade=" + gradeId + ", section=" + sectionId
				+ ", subject=" + subjectId + ", academicYear=" + academicYear + ", dateFrom=" + dateFrom + ", dateTo="
				+ dateTo + ", classTeacher=" + classTeacher + ", schoolId=" + schoolId + ", active=" + active + "]";
	}
	
	
}
