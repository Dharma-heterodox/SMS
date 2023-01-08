package org.dgtech.sms.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.datetime.DateFormatter;

public interface Constant {

	public String currentAcademicYear = "2021-2022";
	public String lastAcademicYear = "2019-2020";
	public String STATUS_PENDING = "Pending";
	public String STATUS_SENT = "Sent";
	public String STATUS_SENDING_ERROR = "Sending Error";
	public String STATUS_INVALID= "Invalid";
	public String STATUS_VERIFIED= "Verified";
	public String SMS_SENDER= "MSMSTJ";
	
	public String DEFAULT_PASSWORD= "123dgtech";
	
	
	
	public String NOTIFICATION_TYPE_SMS = "SMS";
	public String NOTIFICATION_TYPE_EMAIL = "EMAIL";
	
	String YES="Yes";
	
	public String activeRecord="1";
	
	public String HOMEWORK_MSG="Home Work Submitted Successfully";
	
	int APPROVED=1;
	int REJECTED=0;
	
	String NAME_REGEX="^[A-Za-z. ]{2,30}$";
	String ALPHA_NUMERIC_REGEX="^[A-Za-z0-9]{2,30}$";
	String GENDER_REGEX="^[A-Za-z]{3,6}$";
	String CASTECAT_REGEX="^[A-Za-z]{1,50}$";
	String MOBILE_REGEX="^[0-9]{10}$";
	String LANDLINE_REGEX="^[0-9-]{5,15}$";
	String AADHAR_REGEX="^[0-9]{12,}$";
	String NUMBER_REGEX="\\d{1,20}";
	String EMAIL_REGEX ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	
	int REQUEST_FAILED=0;
	int REQUEST_SUCCESS=1;
	int REQUEST_APPROVED=2;
	
	int REQUEST_STUDENT=1;
	int REQUEST_TEACHER=2;
	
	int SUCCESS_REPS=0;
	int FAILED_RESP=1;
	
	DateTimeFormatter dformatter=DateTimeFormatter.ofPattern("d-MM-yyyy");
	
	LocalDateTime now= LocalDateTime.now();
	LocalDateTime last30Days=LocalDateTime.now().minusMonths(1);
	
}
