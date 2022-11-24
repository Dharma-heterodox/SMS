package org.dgtech.sms.util;

public final class Permissions {
	
	public final String[] permissions= new String[] { "MESSAGE","HOMEWORK","CIRCULAR","CONSENT","TIMETABLE"};
	
	public final static String messagePrem = "MESSAGE";
	public final static String homeworkPrem = "HOMEWORK";
	public final static String circularPrem = "CIRCULAR";
	public final static String consentPrem = "CONSENT";
	public final static String timetablePrem = "TIMETABLE";
	
	public final static String read = "R";
	public final static String cre = "CRE";
	public final static String cred = "CRED";
	public final static String separeator = "-";
	
	
	
	public static int getPermissionId(String perm) {
		
		switch (perm) {
		case messagePrem+separeator+read:
			return 1;
		case messagePrem+separeator+cre:
			return 2;
		case messagePrem+separeator+cred:
			return 3;
		case circularPrem+separeator+read:
			return 4;
		case circularPrem+separeator+cre:
			return 5;
		case circularPrem+separeator+cred:
			return 6;
		case homeworkPrem+separeator+read:
			return 7;
		case homeworkPrem+separeator+cre:
			return 8;
		case homeworkPrem+separeator+cred:
			return 10;
		case consentPrem+separeator+read:
			return 11;
		case consentPrem+separeator+cre:
			return 12;
		case consentPrem+separeator+cred:
			return 13;
		case timetablePrem+separeator+read:
			return 14;
		case timetablePrem+separeator+cre:
			return 15;
		case timetablePrem+separeator+cred:
			return 16;


			
		}
		return 5;
	}

}
