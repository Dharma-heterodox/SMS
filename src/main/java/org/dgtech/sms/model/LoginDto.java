package org.dgtech.sms.model;

import java.io.Serializable;

public class LoginDto implements Serializable {

	/**
	 * 
	 */
	private String userId;
	private String password;
	private static final long serialVersionUID = 1L;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
//	private String loginId;


//	public String getLoginId() {
//		return loginId;
//	}
//
//	public void setLoginId(String loginId) {
//		this.loginId = loginId;
//	}
	
	
	
	

	
}
