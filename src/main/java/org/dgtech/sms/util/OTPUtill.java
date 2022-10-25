package org.dgtech.sms.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dgtech.sms.model.OTPDto;


public final class OTPUtill {
	
	private static Map<String,OTPDto> otp=new ConcurrentHashMap<String,OTPDto>();
	
	public static OTPDto saveOTP(String mobileNo,OTPDto otpObj)throws Exception{
		otp.put(mobileNo,otpObj);
		return otpObj;
	}
	
	public static boolean checkOTP(String mobileNo,String otpCode)throws Exception{
		if(otp.containsKey(mobileNo)) {
			OTPDto otpObj=(OTPDto)otp.get(mobileNo);
			if(otpCode!=null && LocalDateTime.now().isBefore(otpObj.getExpiryTime()) && 
					otpCode.equals(otpObj.getCode())){
				otpObj.setStatus(Constant.STATUS_VERIFIED);
				otp.put(mobileNo, otpObj);
				otp.remove(mobileNo);
				return true;
			}
		}
		return false;
	}

}
