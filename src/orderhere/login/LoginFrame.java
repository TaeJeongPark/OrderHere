package orderhere.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame implements ActionListener, FocusListener {

	/**
	* @packageName	: orderhere.login
	* @fileName		: Login.java
	* @author		: TaeJeong Park
	* @date			: 2022.05.20
	* @description	:
	* ===========================================================
	* DATE				AUTHOR				NOTE
	* -----------------------------------------------------------
	* 2022.05.20		TaeJeong Park		최초 생성
	*/
	
	private JTextField tfId;		//아이디 입력 텍스트필드
	private JTextField tfPw;		//비밀번호 입력 텍스트필드
	private JPanel pnBackground;	//백그라운드 패널
	private JLabel lblError;		//오류 메시지 출력 라벨
	private JButton btnLogin;		//로그인 버튼
	private JLabel lblJoin;			//회원가입 화면 이동 라벨버튼
	private JLabel lblFind;			//아이디/비밀번호 찾기 화면 이동 라벨버튼

	//로그인 프레임
	public LoginFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 20);
        setSize(1050, 789);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        
        
        //타이틀바 아이콘 설정
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("images/common/logo_titleBar.png");
        setIconImage(img);
        
        
        pnBackground = new JPanel();
		pnBackground.setLayout(new BorderLayout());
		pnBackground.setBorder(BorderFactory.createEmptyBorder(100, 0, 50, 0));
		pnBackground.setBackground(new Color(1, 168, 98));
		
		getContentPane().add(pnBackground, BorderLayout.CENTER);
        
        makeLogo();		//로고 영역 생성
        makeInput();	//인풋필드 영역 생성
        makeInfo();		//안내정보 영역 생성

        setVisible(true);
    }

	//로고 영영 생성
	private void makeLogo() {
		
		JPanel pnLogo = new JPanel();
		pnLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnLogo.setBackground(new Color(255, 0, 0, 0));		//패널 투명 설정
		
		JLabel lblLogo = new JLabel(new ImageIcon("images/login/logo_login.png"));
		
		pnLogo.add(lblLogo);
		pnBackground.add(pnLogo, BorderLayout.NORTH);
		
	}

	//인풋필드 영역 생성
	private void makeInput() {
		
		JPanel pnInputBackground = new JPanel();
		pnInputBackground.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnInputBackground.setBackground(new Color(255, 0, 0, 0));	//패널 투명 설정
		
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(5, 1, 5, 5));
		pnInput.setBorder(new EmptyBorder(30, 0, 0, 0));	//패널 패딩 설정
		pnInput.setBackground(new Color(255, 0, 0, 0));		//패널 투명 설정
		
		
		//아이디, 비밀번호 텍스트필드
		tfId = new JTextField("  아이디");
		tfId.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfId.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfId.addFocusListener(this);
		
		tfPw = new JTextField("  비밀번호");
		tfPw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfPw.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPw.addFocusListener(this);
		
		
		//오류 메시지 라벨
		lblError = new JLabel("아이디 혹은 비밀번호가 일치하지 않습니다.");
		lblError.setPreferredSize(new Dimension(414, 48));	//라벨 크기 설정
		lblError.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblError.setForeground(Color.RED);	//텍스트필드 폰트 생상 설정
		lblError.setVerticalAlignment(JLabel.TOP);	//텍스트필드 상단 정렬
		
		
		//로그인 버튼
		btnLogin = new JButton(new ImageIcon("images/login/Btn_Login_EnabledFalse2.png"));
		btnLogin.setRolloverIcon(new ImageIcon("images/login/Btn_Login_Rollover.png"));
		btnLogin.setPressedIcon(new ImageIcon("images/login/Btn_Login_Pressed.png"));
		btnLogin.setBorderPainted(false);					//버튼 테두리 설정
		btnLogin.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnLogin.setFocusPainted(false);					//포커스 표시 설정
		btnLogin.setOpaque(false);							//투명하게 설정
		btnLogin.setPreferredSize(new Dimension(414, 48));	//버튼 크기 설정
		btnLogin.setEnabled(false);							//비활성화 상태로 생성
		btnLogin.addActionListener(this);
		
		
		//회원가입, 아이디/비밀번호 찾기 화면 이동 라벨버튼
		JPanel pnJoinFind = new JPanel();
		pnJoinFind.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 0));
		pnJoinFind.setBorder(new EmptyBorder(20, 0, 0, 0));	//패널 패딩 설정
		pnJoinFind.setBackground(new Color(255, 0, 0, 0));	//패널 투명 설정
		
		lblJoin = new JLabel("회원가입");
		lblJoin.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		
		lblFind = new JLabel("아이디/비밀번호 찾기");
		lblFind.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		
		pnJoinFind.add(lblJoin);
		pnJoinFind.add(lblFind);
		
		
		pnInput.add(tfId);
		pnInput.add(tfPw);
		pnInput.add(lblError);
		pnInput.add(btnLogin);
		pnInput.add(pnJoinFind);
		
		pnInputBackground.add(pnInput);
		pnBackground.add(pnInputBackground, BorderLayout.CENTER);
		
	}

	//안내정보 영역 생성
	private void makeInfo() {
		
		JPanel pnInfoBackground = new JPanel();
		pnInfoBackground.setLayout(new GridLayout(1, 2));
		pnInfoBackground.setBorder(new EmptyBorder(20, 50, 0, 50));	//패널 패딩 설정
		pnInfoBackground.setBackground(new Color(255, 0, 0, 0));	//패널 투명 설정
		
		//이용약관 안내
		JPanel pnInfoTOS = new JPanel();
		pnInfoTOS.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnInfoTOS.setBackground(new Color(255, 0, 0, 0));	//패널 투명 설정
		
		JLabel lblTOS = new JLabel("<html>본 서비스를 이용할 경우<br/>이용약관 및 개인정보 처리 방침에 동의한 것으로 간주합니다.</html>");
		lblTOS.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblTOS.setForeground(new Color(94, 94, 94));	//라벨 폰트 생상 설정
		pnInfoTOS.add(lblTOS);
		
		
		//회사명 및 프로그램 버전 정보 안내
		JPanel pnInfoVerion = new JPanel();
		pnInfoVerion.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnInfoVerion.setBackground(new Color(255, 0, 0, 0));	//패널 투명 설정
		
		JLabel lblVersion = new JLabel("<html><div style='text-align:right'>All Night Company<br/>ver 1.0</div></html>");
		lblVersion.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblVersion.setForeground(new Color(94, 94, 94));	//라벨 폰트 생상 설정
		pnInfoVerion.add(lblVersion);
		
		
		pnInfoBackground.add(pnInfoTOS);
		pnInfoBackground.add(pnInfoVerion);
		pnBackground.add(pnInfoBackground, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == tfId) {
			//아이디 텍스트필드 PlaceHolder
			if(tfId.getText().equals("  아이디")) {
				tfId.setText("");
				tfId.setForeground(Color.BLACK);
			}
		} else if(obj == tfPw) {
			//비밀번호 텍스트필드 PlaceHolder
			if(tfPw.getText().equals("  비밀번호")) {
				tfPw.setText("");
				tfPw.setForeground(Color.BLACK);
			}
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == tfId) {
			//아이디 텍스트필드 PlaceHolder
			if(tfId.getText().isEmpty()) {
				tfId.setText("  아이디");
				tfId.setForeground(Color.GRAY);
			}
		} else if(obj == tfPw) {
			//비밀번호 텍스트필드 PlaceHolder
			if(tfPw.getText().isEmpty()) {
				tfPw.setText("  비밀번호");
				tfPw.setForeground(Color.GRAY);
			}
		}
		
	}

}
