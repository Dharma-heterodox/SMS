package org.dgtech.sms.model;

import java.util.Set;


public class PCAuthResponse {
	
	private static final long serialVersionUID = 1L;
	private String fathersName;
	private String mothersName;
	private String token;
	private Set<StudentAuthResponse> students;
	private Long userId;
	private String email;
	private int responseCode;
	private String errorMsg;
	
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
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
	
	


}
