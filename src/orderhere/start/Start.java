package orderhere.start;

import orderhere.login.Login;
import orderhere.order.CommonPanel;

/**
* @packageName	: orderhere.start
* @fileName		: Main.java
* @author		: TaeJeong Park
* @date			: 2022.05.20
* @description	: 메인 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.20		TaeJeong Park		최초 생성
* 2022.05.20		TaeJeong Park		기능 구현 완료
* 2022.06.09		TaeJeong Park		메인 프레임 객체 생성
*/
public class Start {
	
	private static StartFrame mf;	//메인 프레임 객체

	public static void main(String[] args) {
		
		mf = new StartFrame("Login");
		
		CommonPanel.redraw(new Login());
		
	}

	public static StartFrame getMf() {
		
		return mf;
		
	}

}
