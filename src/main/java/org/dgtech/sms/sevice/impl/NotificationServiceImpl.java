package org.dgtech.sms.sevice.impl;

import org.dgtech.sms.entity.Notification;
import org.dgtech.sms.repo.NotificationRepo;
import org.dgtech.sms.sevice.NotificationService;
import org.dgtech.sms.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepo notificationRepo;
	
	@Value("${app.sms.gateway.baseurl}")
	public String baseURL ;
	
	@Value("${app.sms.gateway.username}")
	public String username ;
	
	@Value("${app.sms.gateway.password}")
	public String password ;
	
	@Override
	public Notification createNotification(Notification notification) {
		return notificationRepo.save(notification);
	}

	@Override
	public Notification updateNotification(Notification notification) {
		return notificationRepo.save(notification);
	}

	@Override
	public boolean sendSMS(String mobileNo, String message, String formName, Long referenceId) {
		Notification notification = new Notification();
		notification.setNotificationType(Constant.NOTIFICATION_TYPE_SMS);
		notification.setToAddr(mobileNo);
		notification.setBody(message);
		notification.setFormName(formName);
		notification.setReferenceId(referenceId);
		notification.setStatus(Constant.STATUS_PENDING);
		notification = createNotification(notification);
		String result = null;
		try {
			String uri = baseURL 
					+ "?username=" + username 
					+ "&password=" + password 
					+ "&to=" + mobileNo
					+ "&sender=" + Constant.SMS_SENDER
					+ "&message=" + message;
		    RestTemplate restTemplate = new RestTemplate();
		    result = restTemplate.getForObject(uri, String.class);
		} catch (Exception e) {
			notification.setStatus(Constant.STATUS_SENDING_ERROR);
	    	notification.setErrorMessage(e.getMessage());
	    	updateNotification(notification);
	    	return false;
		}
	    if(result != null && result.contains("Message GID")) { 
	    	notification.setStatus(Constant.STATUS_SENT);
	    	notification.setErrorMessage(result);
	    	updateNotification(notification);
	    	return true;
	    } else {
	    	notification.setStatus(Constant.STATUS_SENDING_ERROR);
	    	notification.setErrorMessage(result);
	    	updateNotification(notification);
	    	return false;
	    }
	}

	@Override
	public boolean sendEmail(String to, String cc, String bcc, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}
