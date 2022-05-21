package orderhere.common;

import java.util.regex.Pattern;

public class Validation {

	//ID 유효성 검사 : 영문, 숫자 조합의 5자리 이상 12자리 이하로 사용 가능하며, 첫 자리에 숫자 사용 불가능
	public static boolean idValidation(String str) {
		return Pattern.matches("^[a-zA-Z0-9]{5,12}$", str) && !Pattern.matches("^[0-9]$", str.substring(0, 1));
	}
	
	//PW 유효성 검사 : 영문, 숫자, 특수문자 조합의 8자리 이상 15자리 이하로 사용 가능
	public static boolean pwValidation(String str) {
		return Pattern.matches("^[a-zA-Z0-9!@#$]{8,15}$", str);
	}
	
	//휴대폰번호 유효성 검사 : 숫자 10자리 이상 11자리 이하로 사용 가능
	public static boolean phonNumValidation(String str) {
		return Pattern.matches("^[0-9]{10,11}$", str);
	}
	
}
