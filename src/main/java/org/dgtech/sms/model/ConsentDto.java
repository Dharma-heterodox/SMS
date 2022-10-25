package org.dgtech.sms.model;

import java.time.LocalTime;

import javax.persistence.Column;

public class ConsentDto {
	
	private Long id;
	
	private String title;
	
	private String description;
	private LocalTime startTime;
	private LocalTime endTime;
	private int acceptedCount;
	private int rejectedCount;
	private int notAnsweredCount;
	private String consentFor;

}
