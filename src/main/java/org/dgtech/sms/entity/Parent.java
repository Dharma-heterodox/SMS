package org.dgtech.sms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.dgtech.sms.entity.student.Student;

@Entity
@Table(name = "parent",
uniqueConstraints = @UniqueConstraint(columnNames= {"UserId","mobile"}))
public class Parent extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;
	private String relationship;
	@Transient
	private Set<Student> childs;
	private boolean active;
	private Long UserId;
	private String casteCat;
    private String caste;
    private String religion;
    private String landLine;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="parent",orphanRemoval = true)
    private List<Student> childList=new ArrayList<Student>(); 
    
	
	
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
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
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
	
	public Set<Student> getChilds() {
		return childs;
	}
	public void setChilds(Set<Student> childs) {
		this.childs = childs;
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
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
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
		Parent other = (Parent) obj;
		if (active != other.active)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (relationship == null) {
			if (other.relationship != null)
				return false;
		} else if (!relationship.equals(other.relationship))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Parent [id=" + id + ", relationship=" + relationship + ", active=" + active + "]";
	}
	
	public void addChild(Student student) {
		childList.add(student);
		student.setParent(this);
	}
	
	public void removeChild(Student student) {
		childList.remove(student);
		student.setParent(null);
	}
	public List<Student> getChildList() {
		return childList;
	}
	public void setChildList(List<Student> childList) {
		this.childList = childList;
	}
	
	
}
