package org.dgtech.sms.sevice.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dgtech.sms.entity.Messages;
import org.dgtech.sms.entity.NotifyGrade;
import org.dgtech.sms.model.MessagesDto;
import org.dgtech.sms.model.NotificationDto;
import org.dgtech.sms.repo.message.MessageRepo;
import org.dgtech.sms.repo.notifygrade.NotifyGradesRepo;
import org.dgtech.sms.sevice.MessageService;
import org.dgtech.sms.sevice.NotificationService;
import org.dgtech.sms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private NotificationService notificationService;
	
	
	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired
	private NotifyGradesRepo notifyRepo;

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
	
	@Override
	
	public int createMsg4App(MessagesDto dto) {
		int success = Constant.FAILED_RESP;
		try {
			Messages msgEntity=getMessagesEntity(dto);
			messageRepo.saveAndFlush(msgEntity);
		    success = Constant.SUCCESS_REPS;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return success;
		
	}
	
	@Override
	public List<MessagesDto> getLast30Msg(String selGrade)throws Exception{
		List<MessagesDto> dtoList=new ArrayList<MessagesDto>(32);
		String[] selectedGrade = null;
		List<Long> ntgIds=null;
		try {
			selectedGrade= new String [] {"ALL",selGrade};
			ntgIds=notifyRepo.msgIdValue(selectedGrade);
			makeDtoList(dtoList, messageRepo.getPCMsg(ntgIds));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return dtoList;
	}
	
//  Will get all msg for approval . Admin
	@Override
	public List<MessagesDto> msg4Approval()throws Exception{
		List<MessagesDto> dtoList = new ArrayList<MessagesDto>();
		return makeDtoList(dtoList, messageRepo.msg4Approval());
		
	}
	
//	Update approved msg by Admin
	@Override
	@Transactional
	public int approveMsgs(List<Long> ids)throws Exception{
		int result = 0;
		result = messageRepo.approveMsg(ids);
		return result;
	}
	
	
	private Messages getMessagesEntity(MessagesDto dto)throws Exception{
		Messages notify = new Messages();
		String grades=dto.getStdSelected();
		String[] listedGrades=null;
		notify.setMsgBody(dto.getMsgBody());
		notify.setCreatedBy(dto.getCreatedBy());
		notify.setCreatedTime(LocalDateTime.now());
		notify.setMsgDate(LocalDateTime.now());
		notify.setListedGrades(grades);
		notify.setDisplay(false);
		if(grades!=null && grades.contains(",")) {
			listedGrades=grades.split(",");
			for(int i=0;i<listedGrades.length;i++) {
				notify.addNotifyGrade(makeNotifyGrade(listedGrades[i]));
			}
		}else {
			notify.addNotifyGrade(makeNotifyGrade(grades));
		}
		return notify;
		
	}
	
	private NotifyGrade makeNotifyGrade(String grade)throws Exception{
		NotifyGrade gradeN=new NotifyGrade();
			gradeN.setCreatedDate(LocalDateTime.now());
			gradeN.setDisplay(false);
			gradeN.setGrade(grade);
		return gradeN;
		
	}
	
	
	private List<MessagesDto> makeDtoList(List<MessagesDto> dtoList,
			List<Messages> entity)	throws Exception{
		entity.forEach(h ->{
			MessagesDto dto=new MessagesDto();
			dto.setMsgBody(h.getMsgBody());
			dto.setMsgDate(Constant.dformatter.format(h.getMsgDate()));
			dto.setId(h.getId());
			dto.setCreatedBy(h.getCreatedBy());
			dto.setStdSelected(h.getListedGrades());
			dtoList.add(dto);
		});
		return dtoList;
	}
}
