package org.dgtech.sms.util;

import java.util.Random;
import java.util.TreeMap;

public final class StringUtil {

	public static String toRoman(int number) {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
	}
	
	public static String generateOTP(int length) {
	      String numbers = "123456789";
	      Random random = new Random();
	      char[] otp = new char[length];

	      for(int i = 0; i< length ; i++) {
	         otp[i] = numbers.charAt(random.nextInt(numbers.length()));
	      }
	      return String.valueOf(otp);
	   }
	
	public static String capitalize(String str) {
	    if(str == null) return str;
	    if(str.trim().length() <= 1) return str;
	    str = str.toLowerCase();
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String fullCapitalize(String str) {
	    if(str == null) return str;
	    String[] strs = str.split(" ");
	    String result = null;
	    for(String s: strs) {
	    	if(result ==null) {
	    		result = capitalize(s);
	    	} else {
	    		result = result + " " + capitalize(s);
	    	}
	    }
	    return result;
	}
	
	public static String getGrade(String classHandling) {
		if(classHandling == null) return classHandling;
		String grade = classHandling.split("-")[0]; 
		return toRoman(Integer.parseInt(grade));
	}
	public static String getSection(String classHandling) {
		if(classHandling == null) return classHandling;
		return classHandling.split("-")[1]; 
	}
}
