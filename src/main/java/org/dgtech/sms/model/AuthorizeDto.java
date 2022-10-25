package org.dgtech.sms.model;

import java.util.List;

public class AuthorizeDto {
	
	private List<Long> ids;
	
	private int auth;//0-False,1-True

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	

}
