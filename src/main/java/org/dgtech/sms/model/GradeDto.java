package org.dgtech.sms.model;

import java.util.List;



public class GradeDto {

	private Long id;
	private Long boardId;
	private Long mediumId;
	private String grade;
	private String title;
	private String description;
	private List<SectionDto> sections;
	private List<SubjectDto> subjects;
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

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

	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
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
	public String toString() {
		return "GradeDto [id=" + id + ", boardId=" + boardId + ", mediumId=" + mediumId + ", grade=" + grade
				+ ", active=" + active + "]";
	}
}
