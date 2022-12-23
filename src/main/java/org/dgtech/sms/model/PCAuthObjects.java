package org.dgtech.sms.model;

import java.util.List;
import java.util.Set;

public class PCAuthObjects {
	
	private static final long serialVersionUID = 1L;
	private String fathersName;
	private String mothersName;
	private String token;
	private Set<StudentAuthResponse> students;
	private Long userId;
	private String email;
	private List<String> role;
	private List<String> permissions;
	private String mobile;
	private int noOfDeviceLogged;
	private List<String> devices;
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public String getMothersName() {
		return mothersName;
	}
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<StudentAuthResponse> getStudents() {
		return students;
	}
	public void setStudents(Set<StudentAuthResponse> students) {
		this.students = students;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getNoOfDeviceLogged() {
		return noOfDeviceLogged;
	}
	public void setNoOfDeviceLogged(int noOfDeviceLogged) {
		this.noOfDeviceLogged = noOfDeviceLogged;
	}
	public List<String> getDevices() {
		return devices;
	}
	public void setDevices(List<String> devices) {
		this.devices = devices;
	}
	
	

}
