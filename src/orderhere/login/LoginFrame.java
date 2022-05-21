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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import orderhere.common.DB;

/**
* @packageName	: orderhere.login
* @fileName		: LoginFrame.java
* @author		: TaeJeong Park
* @date			: 2022.05.20
* @description	: 로그인 화면(00)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
*/
public class LoginFrame extends JFrame implements ActionListener, FocusListener, KeyListener, MouseListener {
	
	private String usersId = null;		//회원 아이디
	private String userPw = null;		//회원 비밀번호
	
	private String userInputId = null;	//사용자에게 입력 받은 아이디
	private String userInputPw = null;	//사용자에게 입력 받은 비밀번호
	
	private JTextField tfId;			//아이디 입력 텍스트필드
	private JPasswordField tfPw;		//비밀번호 입력 패스워드필드
	private JPanel pnBackground;		//백그라운드 패널
	private JLabel lblError;			//오류 메시지 출력 라벨
	private JButton btnLogin;			//로그인 버튼
	private JLabel btnLblJoin;			//회원가입 화면 이동 라벨버튼
	private JLabel btnLblFind;			//아이디/비밀번호 찾기 화면 이동 라벨버튼
	private boolean LoginFlag = false;	//로그인 버튼 활성화 상태 저장
	private JoinFrame jf = null;		//회원가입 프레임 객체
	private FindFrame ff = null;		//아이디/비밀번호 찾기 프레임 객체

	//로그인 프레임
	public LoginFrame(String title) {
		
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
        
        makeLogo();		//로고 영역 생성
        makeInput();	//인풋필드 영역 생성
        makeInfo();		//안내정보 영역 생성
        DB.init();		//DB 연결

        setVisible(true);
        
    }

	//로고 영영 생성
	private void makeLogo() {
		
		JPanel pnLogo = new JPanel();
		pnLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnLogo.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JLabel lblLogo = new JLabel(new ImageIcon("images/login/logo_login.png"));
		
		pnLogo.add(lblLogo);
		pnBackground.add(pnLogo, BorderLayout.NORTH);
		
	}

	//인풋필드 영역 생성
	private void makeInput() {
		
		JPanel pnInputBackground = new JPanel();
		pnInputBackground.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnInputBackground.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(5, 1, 5, 5));
		pnInput.setBorder(new EmptyBorder(30, 0, 0, 0));	//패널 패딩 설정
		pnInput.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		
		//아이디, 비밀번호 텍스트필드
		tfId = new JTextField("  아이디");
		tfId.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfId.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfId.addFocusListener(this);
		tfId.addKeyListener(this);
		tfId.addActionListener(this);
		
		tfPw = new JPasswordField("  비밀번호");
		tfPw.setEchoChar((char)0);	//작성되는 문자 드대로 보여지게 설정
		tfPw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfPw.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPw.addFocusListener(this);
		tfPw.addKeyListener(this);
		tfPw.addActionListener(this);
		
		
		//오류 메시지 라벨
		lblError = new JLabel();
		lblError.setPreferredSize(new Dimension(414, 48));	//라벨 크기 설정
		lblError.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblError.setForeground(Color.RED);	//텍스트필드 폰트 생상 설정
		lblError.setVerticalAlignment(JLabel.TOP);	//텍스트필드 상단 정렬
		
		
		//로그인 버튼
		btnLogin = new JButton(new ImageIcon("images/login/Btn_Login_EnabledTrue.png"));
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
		pnJoinFind.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		btnLblJoin = new JLabel("회원가입");
		btnLblJoin.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		btnLblJoin.addMouseListener(this);
		
		btnLblFind = new JLabel("아이디/비밀번호 찾기");
		btnLblFind.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		btnLblFind.addMouseListener(this);
		
		pnJoinFind.add(btnLblJoin);
		pnJoinFind.add(btnLblFind);
		
		
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
		pnInfoBackground.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		//이용약관 안내
		JPanel pnInfoTOS = new JPanel();
		pnInfoTOS.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnInfoTOS.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JLabel lblTOS = new JLabel("<html>본 서비스를 이용할 경우<br/>이용약관 및 개인정보 처리 방침에 동의한 것으로 간주합니다.</html>");
		lblTOS.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblTOS.setForeground(new Color(94, 94, 94));	//라벨 폰트 생상 설정
		pnInfoTOS.add(lblTOS);
		
		
		//회사명 및 프로그램 버전 정보 안내
		JPanel pnInfoVerion = new JPanel();
		pnInfoVerion.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnInfoVerion.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JLabel lblVersion = new JLabel("<html><div style='text-align:right'>All Night Company<br/>ver 1.0</div></html>");
		lblVersion.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblVersion.setForeground(new Color(94, 94, 94));	//라벨 폰트 생상 설정
		pnInfoVerion.add(lblVersion);
		
		
		pnInfoBackground.add(pnInfoTOS);
		pnInfoBackground.add(pnInfoVerion);
		pnBackground.add(pnInfoBackground, BorderLayout.SOUTH);
		
	}
	
	//로그인 에러 핸들링
	private void errorHandling() {
		
		tfId.setText("  아이디");
		tfId.setForeground(Color.GRAY);
		tfPw.setText("  비밀번호");
		tfPw.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
		tfPw.setForeground(Color.GRAY);
		btnLogin.setEnabled(false);
		lblError.setText("아이디 혹은 비밀번호가 일치하지 않습니다.");
		
	}
	
	//Salt 생성
	public static String Salt() { 
		
		String salt = "";
		
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("난수 생성 오류");
			e.printStackTrace();	
		}
		
		return salt;
		
	}
	
	//SHA512 암호화
	public static String SHA512(String password, String hash) {
		
		String salt = hash + password;
		String hex = null;
		
		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(salt.getBytes());
			
			hex = String.format("%128x", new BigInteger(1, msg.digest()));
		} catch (Exception e) {
			System.out.println("암호화 오류");
			e.printStackTrace();
		}
		
		return hex;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		//로그인 유효성 검사
		if(LoginFlag && (obj == btnLogin || obj == tfId || obj == tfPw)) {	//로그인 절차 진행여부 감지
			if(Pattern.matches("^[a-zA-Z0-9]{5,12}$", tfId.getText()) && !Pattern.matches("^[0-9]$", tfId.getText().substring(0, 1))) {	//아이디 작성 규칙 성립여부 검사(영문, 숫자 조합만 사용 가능)
				if(Pattern.matches("^[a-zA-Z0-9!@#$]{8,15}$", tfPw.getText())) {	//비밀번호 작성 규칙 성립여부 검사(영문, 숫자 조합만 사용 가능)
					lblError.setText("");
					System.out.println("로그인 진행");
					
					userInputId = tfId.getText();	//사용자가 아이디 텍스트필드에 입력한 데이터 저장 
					userInputPw = tfPw.getText();	//사용자가 비밀번호 텍스트필드에 입력한 데이터 저장
					
					
				} else {
					errorHandling();
				}
			} else {
				errorHandling();
			}
		}
		
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
				tfPw.setEchoChar('●');	//작성되는 문자 '●'로 변환 설정
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
				tfPw.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
				tfPw.setForeground(Color.GRAY);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(!tfId.getText().equals("  아이디") && !tfPw.getText().equals("  비밀번호")) {
			if(tfId.getText().length() >= 5 && tfPw.getText().length() >= 8) {	//로그인 버튼 활성화 조건 검사
				btnLogin.setEnabled(true);	//로그인 버튼 활성화
				LoginFlag = true;
			}
			
			if(tfId.getText().length() < 5 || tfPw.getText().length() < 8) {	//로그인 버튼 비활성화 조건 검사
				btnLogin.setEnabled(false);	//로그인 버튼 비활성화
				LoginFlag = false;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == btnLblJoin) {	//회원가입 라벨버튼 클릭시
			btnLblJoin.setForeground(Color.BLACK);
			
			if(jf == null) {	//회원가입 객체 생성여부 확인
				jf = new JoinFrame("Join", this);		//회원가입 화면 생성	
			} else {
				jf.setVisible(true);	//회원가입 화면 노출
			}
			
			setVisible(false);
		} else if(obj == btnLblFind) {	//아이디/비밀번호 찾기 라벨버튼 클릭시
			btnLblFind.setForeground(Color.BLACK);
			
			if(ff == null) {	//아이디/비밀번호 찾기 객체 생성여부 확인
				ff = new FindFrame("Find ID/PW", this);	//아이디/비밀번호 찾기 화면 생성
			} else {
				ff.setVisible(true);	//아이디/비밀번호 찾기 화면 노출
			}
			
			setVisible(false);	//로그인 화면 제거
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == btnLblJoin) {
			btnLblJoin.setForeground(Color.BLUE);
		} else if(obj == btnLblFind) {
			btnLblFind.setForeground(Color.BLUE);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

Object obj = e.getSource();
		
		if(obj == btnLblJoin) {
			btnLblJoin.setForeground(Color.BLACK);
		} else if(obj == btnLblFind) {
			btnLblFind.setForeground(Color.BLACK);
		}
		
	}

}
