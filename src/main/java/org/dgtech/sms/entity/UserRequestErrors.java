package org.dgtech.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userrequest_errors")
public class UserRequestErrors {
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userrequest_errors_id")
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private UserRequest userRequest;
    private String errorCode;
    
    public UserRequestErrors(String errorCode) {
    	this.errorCode=errorCode;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public UserRequest getUserRequest() {
		return userRequest;
	}
	public void setUserRequest(UserRequest userRequest) {
		this.userRequest = userRequest;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequestErrors )) return false;
        return id != null && id.equals(((UserRequestErrors) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }
    
    
}
