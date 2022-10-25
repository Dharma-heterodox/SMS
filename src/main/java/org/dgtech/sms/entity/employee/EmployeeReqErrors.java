package org.dgtech.sms.entity.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="employeereq_errors")
public class EmployeeReqErrors {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emprequest_errors_id")
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private EmployeeRequest empRequest;
    private String errorCode;
    
    public EmployeeReqErrors(String errorCode) {
		// TODO Auto-generated constructor stub
    	this.errorCode=errorCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmployeeRequest getEmpRequest() {
		return empRequest;
	}
	public void setEmpRequest(EmployeeRequest empRequest) {
		this.empRequest = empRequest;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    
    

}
