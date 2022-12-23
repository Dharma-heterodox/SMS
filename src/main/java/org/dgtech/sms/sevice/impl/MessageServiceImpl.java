package org.dgtech.sms.sevice.impl;

import org.dgtech.sms.sevice.MessageService;
import org.dgtech.sms.sevice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private NotificationService notificationService;

//	public boolean sendOTP(String mobileNo) {
//		List<OneTimePassword> otps = otpRepo.getUserOTPs(mobileNo, Constant.STATUS_SENT);
//		if(!CollectionUtils.isEmpty(otps)) {
//			for(OneTimePassword otp:otps) {
//				otp.setStatus(Constant.STATUS_INVALID);
//				otpRepo.save(otp);
//			}
//		}
//		OneTimePassword newOtp = saveCode(mobileNo);
////		boolean msgSent = sendCode(mobileNo, newOtp.getCode(), "OTP" , newOtp.getId());
//		boolean msgSent = true;
//		if(msgSent) {
//			newOtp = otpRepo.getOTP(mobileNo, Constant.STATUS_PENDING);
//			newOtp.setStatus(Constant.STATUS_SENT);
//			otpRepo.save(newOtp);
//		}
//		return true;
//	}
//	
//	@Override
//	public boolean sendOTPV2(String mobileNo)throws Exception{
//		boolean msgSent;
//		OneTimePasswordDto dtoObj=generateOTP(mobileNo);
//		OTPUtill.saveOTP(mobileNo, dtoObj);
//		msgSent = sendCode(mobileNo, dtoObj.getCode(), "OTP" ,Long.valueOf(mobileNo.hashCode()));
//		return msgSent;
//	}
//	@Override
//	public boolean verifyOTPV2(String mobileNo,String code)throws Exception{
//		return OTPUtill.checkOTP(mobileNo, code);
//	}
	
//	public boolean verifyOTP(String mobileNo, String code) {
//		OneTimePassword otp = otpRepo.getOTP(mobileNo, Constant.STATUS_SENT);
//		System.out.println("--->"+otp);
//		if(otp != null && LocalDateTime.now().isBefore(otp.getExpiryTime()) 
//				&& (otp.getCode().equalsIgnoreCase(code) || code.equalsIgnoreCase("555555"))) {
//			otp.setStatus(Constant.STATUS_VERIFIED);
//			otpRepo.save(otp);
//			return true;
//		}
//		return false;
//	}
//	
//	private OneTimePassword saveCode(String mobileNo) {
//		OneTimePassword otp = new OneTimePassword();
//		otp.setCode(StringUtil.generateOTP(6));
//		LocalDateTime now = LocalDateTime.now();
//		now = now.plusMinutes(5);
//		otp.setExpiryTime(now);
//		otp.setMobile(mobileNo);
//		otp.setStatus(Constant.STATUS_PENDING);
//		return otpRepo.save(otp);
//	}
//	
//	private OneTimePasswordDto generateOTP(String mobileNo) {
//		OneTimePasswordDto dto=new OneTimePasswordDto();
//		LocalDateTime now = LocalDateTime.now();
//		dto.setCode(StringUtil.generateOTP(6));
//		dto.setExpiryTime(now.plusMinutes(5));
//		dto.setMobile(mobileNo);
//		dto.setStatus(Constant.STATUS_PENDING);
//		return dto;
//	}
//	
	/**
	 * send code via third party API
	 * 
	 * @param mobileNo
	 * @param code
	 * @return
	 */
	private boolean sendCode(String mobileNo, String code, String formName, Long referenceId) {
		String msg = "Dear Parent, thanks for using our msms parent connect app. Kindly login using OTP "+ code + " Stay connected!";
		return notificationService.sendSMS(mobileNo, msg, formName, referenceId);
	}
}
