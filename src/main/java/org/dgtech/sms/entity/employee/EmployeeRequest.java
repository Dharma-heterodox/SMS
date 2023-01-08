package org.dgtech.sms.entity.employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dgtech.sms.entity.BaseEntity;
@Entity
@Table(name="employee_request")
public class EmployeeRequest extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
	private Long id;
	@Column(name="employee_id")
	private int employeeId;
	@Column(name="first_name",length=50)
	private String firstName;
	@Column(name="last_name",length=50)
	private String lastName;
	@Column(name="account_no",length=20)
	private String accountNo;
	@Column(name="aadhaar",length=15)
	private String aadhaarNo;
	@Column(name="grosspay")
	private Long grossPay;
	@Column(name="active")
	private boolean active;
	@Column(name="gender",length=10)
	private String gender;

	private String dob;
	@Column(name="epf_name",length=50)
	private String epfName;
	
	private int pfno;
	
	private LocalDate doj;
	@Column(name="esi_name",length=50)
	private String esiName;
	
	private Long esiNo;
	@Column(name="uanno",length=20)
	private String uanNo;
	
	private boolean punStatus;
	@Column(name="pun_amount")
	private int punAmount;
	@Column(name="qualification",length=50)
	private String qualification;
	@Column(name="address")
	private String addressOne;
	@Column(name="pincode",length=10)
	private Integer pincode;
	@Column(name="phone_number",length=20)
	private String phoneNumber;
	@Column(name="mobile",length=13)
	private String mobile;
	@Column(name="mobile2",length=13)
	private String alternateMobile;
	@Column(name="caste_cat",length=20)
	private String casteCat;
	@Column(name="religion",length=50)
	private String religion;
	@Column(name="caste",length=100)
	private String caste;
	@Column(name="emptype",length=40)
	private String empType;
	@Column(name="type_order")
	private int typeOrder;
	@Column(name="request_status")
	private int requestStatus;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="empRequest",orphanRemoval = true)
    private List<EmployeeReqErrors> errors=new ArrayList<EmployeeReqErrors>();
    @Column(length=3)
    private Long schoolId;
    @Column(name="email",length=50)
	private String emailId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public Long getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(Long grossPay) {
		this.grossPay = grossPay;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEpfName() {
		return epfName;
	}
	public void setEpfName(String epfName) {
		this.epfName = epfName;
	}
	public int getPfno() {
		return pfno;
	}
	public void setPfno(int pfno) {
		this.pfno = pfno;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public String getEsiName() {
		return esiName;
	}
	public void setEsiName(String esiName) {
		this.esiName = esiName;
	}
	public Long getEsiNo() {
		return esiNo;
	}
	public void setEsiNo(Long esiNo) {
		this.esiNo = esiNo;
	}
	public String getUanNo() {
		return uanNo;
	}
	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}
	public boolean isPunStatus() {
		return punStatus;
	}
	public void setPunStatus(boolean punStatus) {
		this.punStatus = punStatus;
	}
	public int getPunAmount() {
		return punAmount;
	}
	public void setPunAmount(int punAmount) {
		this.punAmount = punAmount;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAlternateMobile() {
		return alternateMobile;
	}
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}
	public String getCasteCat() {
		return casteCat;
	}
	public void setCasteCat(String casteCat) {
		this.casteCat = casteCat;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public int getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	public List<EmployeeReqErrors> getErrors() {
		return errors;
	}
	public void setErrors(List<EmployeeReqErrors> errors) {
		this.errors = errors;
	}
	
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public void addErrorCode(EmployeeReqErrors errorCode) {
		errors.add(errorCode);
		errorCode.setEmpRequest(this);
	}
	
	public void removeErrorCode(EmployeeReqErrors errorCode) {
		errors.remove(errorCode);
		errorCode.setEmpRequest(null);
	}
	
	@Override
	public int hashCode() {
		if(this.id!=null) {
			return this.id.hashCode();
		}
		return 31;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this.id == null)
			return false;
		EmployeeRequest dto=(EmployeeRequest)obj;
		if(dto.id==this.id || dto.id.equals(this.id))
			return true;
		
		return false;
	}
	
	

}
