package org.dgtech.sms.model;

import java.util.List;

public class ParentDto extends Person{

	private Long id;
	private String relationship;
	private List<StudentDto> childs;
	private boolean active;
    private String casteCat;
    private String caste;
    private String religion;
    private String landLine;
    
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public List<StudentDto> getChilds() {
		return childs;
	}
	public void setChilds(List<StudentDto> childs) {
		this.childs = childs;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
