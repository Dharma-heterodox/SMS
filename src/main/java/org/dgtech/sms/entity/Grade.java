package org.dgtech.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grade extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "grade_id")
//    private Long id;
	private Long boardId;
	private Long mediumId;
	@Id
	@Column(length = 10)
	private String grade;
	@Column(length = 30)
	private String title;
	@Column(length=100)
	private String description;
	private Long schoolId;
	private boolean active;
	
	
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getMediumId() {
		return mediumId;
	}
	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
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
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((mediumId == null) ? 0 : mediumId.hashCode());
		result = prime * result + ((schoolId == null) ? 0 : schoolId.hashCode());
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
		Grade other = (Grade) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Grade , grade=" + grade + ", schoolId="
				+ schoolId + ", active=" + active + "]";
	}
	
}
