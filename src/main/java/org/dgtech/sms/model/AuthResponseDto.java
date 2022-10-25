package org.dgtech.sms.model;

import java.io.Serializable;
import java.util.Set;

import org.dgtech.sms.entity.Role;


public class AuthResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String displayName;
	private Set<Role> roles;
	private String token;
	private Set<AuthResponseSchoolInfo> schools;
	private Set<StudentDto> students;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public Set<StudentDto> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentDto> students) {
		this.students = students;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<AuthResponseSchoolInfo> getSchools() {
		return schools;
	}
	public void setSchools(Set<AuthResponseSchoolInfo> schools) {
		this.schools = schools;
	}

}
