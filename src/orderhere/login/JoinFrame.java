package orderhere.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* @packageName	: orderhere.login
* @fileName		: JoinFrame.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: 회원가입 화면(0001)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
*/
public class JoinFrame extends JFrame {
	
	private LoginFrame lf;
	private JPanel pnBackground;
	
	//로그인 프레임
	public JoinFrame(String title, LoginFrame lf) {
		
		setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
        setSize(1050, 789);
        setResizable(false);
        setLayout(new BorderLayout());
        
        
        //타이틀바 아이콘 설정
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("images/common/logo_titleBar.png");
        setIconImage(img);
        
        
        pnBackground = new JPanel();
		pnBackground.setLayout(new BorderLayout());
		pnBackground.setBorder(BorderFactory.createEmptyBorder(100, 0, 50, 0));
		pnBackground.setBackground(new Color(1, 168, 98));
		
		add(pnBackground, BorderLayout.CENTER);
		
		lf = this.lf;
        
        makeLogo();		//로고 영역 생성
        makeTitle();	//타이틀 영역 생성
        makeInput();	//인풋필드 영역 생성

        setVisible(true);
		
	}

	private void makeLogo() {
		
		
		
	}

	private void makeTitle() {
		
		
		
	}

	private void makeInput() {
		
		
		
	}

}