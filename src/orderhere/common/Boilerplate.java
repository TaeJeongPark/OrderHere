package orderhere.common;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
* @packageName	: orderhere.common
* @fileName		: Boilerplate.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: 유효성 검사를 위한 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
* 2022.05.21		TaeJeong Park		기능 구현 완료
*/
public class Boilerplate {

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
	
	//인증번호 유효성 검사 : 숫자 6자리
	public static boolean certifiNumValidation(String str) {
		
		return Pattern.matches("^[0-9]{6,6}$", str);
		
	}
	
	//이름 유효성 검사 : 한글 2자리 이상 10자리 이하로 사용 가능
	public static boolean nameValidation(String str) {
		
		return Pattern.matches("^[가-하]{2,10}$", str);
		
	}
	
	//생년 유효성 검사 : 1903 ~ 2003 사용 가능
	public static boolean birthyearValidation(Integer num) {
		
		return num >= 1903 && num <= 2003;
		
	}
	
	//생월 유효성 검사 : 1 ~ 12 사용 가능
	public static boolean birthmonthValidation(Integer num) {
		
		return num >= 1 && num <= 12;
		
	}

	//생일 유효성 검사 : 선택된 월에 따라 01 ~ 31 or 01 ~ 30 or 01 ~ 28까지 사용 가능
	public static boolean birthdayteValidation(Integer month, Integer day) {
		
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if(day >= 1 && day <= 31) return true;
		} else if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day >= 1 && day <= 30) return true;
		} else if(month == 2) {
			if(day >= 1 && day <= 28) return true;
		}
		
		return false;
		
	}
	
	//생년월일 유효성 검사 : 숫자 8자리 사용 가능
	public static boolean birthdayValidation(String str) {
		
		return Pattern.matches("^[0-9]{8,8}$", str);
		
	}
	
	//천단위 구분 기호 생성
	public static String setComma(int num) {
		
		DecimalFormat df = new DecimalFormat("###,###");
		String money = df.format(num);
		
		return money;
		
	}
	
	//이미지 버튼 설정
	public static void setImageButton(JButton btn, ImageIcon icRo, ImageIcon icPr, int num1, int num2) {
		
		btn.setRolloverIcon(icRo);
		btn.setPressedIcon(icPr);
		btn.setBorderPainted(false);						//버튼 테두리 설정
		btn.setContentAreaFilled(false);					//버튼 배경 표시 설정
		btn.setFocusPainted(false);							//포커스 테두리 표시 설정
		btn.setOpaque(false);								//투명하게 설정
		btn.setPreferredSize(new Dimension(num1, num2));	//버튼 크기 설정
		btn.setEnabled(false);								//비활성화 상태로 생성
		
	}
	
	//숫자 6자리 인증번호 생성
	public static int certificationNum() {
		
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
        
    }
	
}
