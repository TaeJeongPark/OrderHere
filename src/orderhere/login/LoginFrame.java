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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import orderhere.common.Encryption;
import orderhere.common.UsersData;
import orderhere.common.Validation;
import orderhere.main.MainFrame;

/**
* @packageName	: orderhere.login
* @fileName		: LoginFrame.java
* @author		: TaeJeong Park
* @date			: 2022.05.20
* @description	: 로그인 화면(00)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.20		TaeJeong Park		최초 생성
* 2022.05.20		TaeJeong Park		레이아웃 구현 완료
* 2022.05.21		TaeJeong Park		기능 구현 완료
*/
public class LoginFrame extends JFrame implements ActionListener, FocusListener, KeyListener, MouseListener, WindowListener {
	
	private String usersId;				//회원 아이디
	private String usersPw;				//회원 비밀번호
	private String usersPwSalt;			//회원 비밀번호 난수 데이터
	
	private String usersInId;			//사용자에게 입력 받은 아이디
	private String usersInPw;			//사용자에게 입력 받은 비밀번호
	
	private JTextField tfId;			//아이디 입력 텍스트필드
	private JPasswordField tfPw;		//비밀번호 입력 패스워드필드
	private JPanel pnBackground;		//백그라운드 패널
	private JLabel lblError;			//오류 메시지 출력 라벨
	private JButton btnLogin;			//로그인 버튼
	private JLabel btnLblJoin;			//회원가입 화면 이동 라벨버튼
	private JLabel btnLblFind;			//아이디/비밀번호 찾기 화면 이동 라벨버튼
	private boolean loginFlag = false;	//로그인 버튼 활성화 상태 저장
	
	private JoinFrame jf = null;		//회원가입 프레임 객체
	private FindFrame ff = null;		//아이디/비밀번호 찾기 프레임 객체
	private MainFrame mf = null;		//메인 프레임 객체
	
	
	//로그인 화면
	public LoginFrame(String title) {
		
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
//        setLocationRelativeTo(null);
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
		
        makeLogo();		//로고 영역 생성
        makeInput();	//인풋필드 영역 생성
        makeInfo();		//안내정보 영역 생성

    	add(pnBackground, BorderLayout.CENTER);
    	pnBackground.setVisible(true);
        
        setVisible(true);
        
        DB.init();	//DB 연결
        
        addWindowListener(this);
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
		
		setPH(tfId);
		setPwPH(tfPw);
		btnLogin.setEnabled(false);
		lblError.setText("아이디 혹은 비밀번호가 일치하지 않습니다.");
		tfId.requestFocus();
		
		System.out.println("로그인 실패");
		
	}
	
	//JTextField PlaceHolder 초기화 함수
	private void clearPH(JTextField tf) {
		
		tf.setText("");
		tf.setForeground(Color.BLACK);
		
	}
	
	//JTextField PlaceHolder 세팅 함수
	private void setPH(JTextField tf) {

		tf.setEnabled(true);
		tf.setText("  아이디");
		tf.setForeground(Color.GRAY);
		
	}
	
	//JPasswordField PlaceHolder 초기화 함수
	private void clearPwPH(JPasswordField tf) {
		
		tf.setEchoChar('●');	//작성되는 문자 '●'로 변환 설정
		tf.setText("");
		tf.setForeground(Color.BLACK);
		
	}
	
	//JPasswordField PlaceHolder 세팅 함수
	private void setPwPH(JPasswordField tf) {
		
		tf.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
		tf.setText("  비밀번호");
		tf.setForeground(Color.GRAY);
		
	}
	
	//비밀번호 문자열로 변환
	private String getPassword(JPasswordField pw) {
		String pwStr = "";
		char[] secret_pw = pw.getPassword();
		
		for(char cha : secret_pw){
		     Character.toString(cha);
		     pwStr += cha;
		}
		return pwStr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		//로그인 유효성 검사
		if(loginFlag && (obj == btnLogin || obj == tfId || obj == tfPw)) {	//로그인 절차 진행여부 감지
			if(Validation.idValidation(tfId.getText())) {	//ID 유효성 검사 : 영문, 숫자 조합만 사용 가능하며, 첫 자리에 숫자 사용 불가능
				
				String pw = getPassword(tfPw);
				
				if(Validation.pwValidation(pw)) {	//PW 유효성 검사 : 영문, 숫자, 특수문자 조합만 사용 가능
					lblError.setText("");
					System.out.println("로그인 진행");
					
					usersInId = tfId.getText();	//사용자가 아이디 텍스트필드에 입력한 데이터 저장 
					usersInPw = pw;	//사용자가 비밀번호 텍스트필드에 입력한 데이터 저장
					
					ResultSet rs = DB.getResult("select * from USERS WHERE USERSID = '" + usersInId + "'");	//USERS 테이블에서 일치하는 사용자 존재 유무 조회
					
					try {
						if(rs.next()) {
							usersId = rs.getString("USERSID");	//조회 결과 데이터(회원 아이디) 저장
							usersPw = rs.getString("USERSPW");	//조회 결과 데이터(회원 비밀번호) 저장
							usersPwSalt = rs.getString("USERSPWSALT");	//조회 결과 데이터(회원 비밀번호) 저장
							
							System.out.println("회원 조회 성공");
							
							usersInPw = Encryption.SHA512(usersInPw, usersPwSalt);	//사용자가 입력한 아이디를 조회된 회원 비밀번호 난수 데이터로 SHA512 암호화
							
							if(usersId.equals(usersInId) && usersPw.equals(usersInPw)) {
								System.out.println("로그인 성공");
								
								setPH(tfId);				//PlaceHolder 세팅
								setPwPH(tfPw);				//PlaceHolder 세팅
								btnLogin.setEnabled(false);	//로그인 버튼 비활성화
								
								UsersData.setUsersId(usersId);	//로그인한 회원 아이디 저장
								
								//메인 화면으로 이동
								mf = new MainFrame();	//메인 화면 생성
								dispose();	//현재 화면 종료
							} else {
								errorHandling();
							}
						} else {
							errorHandling();
						}
					} catch (SQLException e1) {
						System.out.println("예외발생 : DB 조회에 실패했습니다.");
						errorHandling();
						e1.printStackTrace();
					}
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
				clearPH(tfId);	//PlaceHolder 초기화
			}
		} else if(obj == tfPw) {
			
			String pw = getPassword(tfPw);
			
			//비밀번호 텍스트필드 PlaceHolder
			if(pw.equals("  비밀번호")) {
				clearPwPH(tfPw);	//PlaceHolder 초기화
			}
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == tfId) {
			//아이디 텍스트필드 PlaceHolder
			if(tfId.getText().isEmpty()) {
				setPH(tfId);	//PlaceHolder 세팅
			}
		} else if(obj == tfPw) {
			
			String pw = getPassword(tfPw);
			
			//비밀번호 텍스트필드 PlaceHolder
			if(pw.isEmpty()) {
				setPwPH(tfPw);	//PlaceHolder 세팅
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
		
		String pw = getPassword(tfPw);
		
		if(!tfId.getText().equals("  아이디") && !pw.equals("  비밀번호")) {
			if(tfId.getText().length() >= 5 && pw.length() >= 8) {	//로그인 버튼 활성화 조건 검사
				btnLogin.setEnabled(true);	//로그인 버튼 활성화
				loginFlag = true;
			} else {
				btnLogin.setEnabled(false);	//로그인 버튼 비활성화
				loginFlag = false;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == btnLblJoin) {	//회원가입 라벨버튼 클릭시
			btnLblJoin.setForeground(Color.BLACK);
			
			jf = new JoinFrame(this);
	        add(jf.getPnJoinBackground(), BorderLayout.CENTER);
	        jf.getPnJoinBackground().setVisible(true);	//회원가입 화면 활성화
			pnBackground.setVisible(false);	//로그인 화면 비활성화
			setTitle("Join");
			
		} else if(obj == btnLblFind) {	//아이디/비밀번호 찾기 라벨버튼 클릭시
			btnLblFind.setForeground(Color.BLACK);
			
			ff = new FindFrame(this);
			add(ff.getPnFindBackground(), BorderLayout.CENTER);
			ff.getPnFindBackground().setVisible(true);	//아이디/비밀번호 찾기 화면 활성화
			pnBackground.setVisible(false);	//로그인 화면 비활성화
			setTitle("ID/PW Find");
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
	
	public JPanel getPnBackground() {
		return pnBackground;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		DB.closeDB(DB.conn, DB.stmt);	//DB 연결 종료
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
