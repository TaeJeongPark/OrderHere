package orderhere.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import orderhere.common.Boilerplate;
import orderhere.common.DB;
import orderhere.common.Encryption;

/**
* @packageName	: orderhere.login
* @fileName		: Join.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: 회원가입 화면(0001)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
* 2022.05.21		TaeJeong Park		레이아웃 구현 완료
* 2022.05.22		TaeJeong Park		기능 구현 완료
* 2022.06.02		TaeJeong Park		화면 전환 방식 변경
*/
public class Join extends JPanel implements ActionListener, FocusListener, KeyListener, ItemListener {
	
	private String usersInId;				//사용자에게 입력 받은 아이디
	private String usersInPw;				//사용자에게 입력 받은 비밀번호
	private String usersInName; 			//사용자에게 입력 받은 이름
	private String usersInBirthday; 		//사용자에게 입력 받은 생년월일
	private String usersInPhoneNum; 		//사용자에게 입력 받은 휴대폰번호
	
	private Login login = null;				//로그인 패널 객체
	private JTextField tfId;				//아이디 입력 텍스트필드
	private JButton btnOverlapChk;			//중복확인 버튼
	private JPasswordField tfPw;			//비밀번호 입력 텍스트필드
	private JPasswordField tfPw2;			//비밀번호 확인 입력 텍스트필드
	private JTextField tfName;				//이름 입력 텍스트필드
	private JComboBox<Integer> cbYear;		//생년 콤보박스
	private Integer[] yearList = {2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903};
	private JComboBox<Integer> cbMonth;		//생월 콤보박스
	private Integer[] monthList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private JComboBox<Integer> cbDay;		//생일 콤보박스
	private Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private JTextField tfPhonNum;			//휴대폰번호 입력 텍스트필드
	private JButton btnSend;				//전송 버튼
	private JTextField tfCertifiNum;		//인증번호 입력 텍스트필드
	private JButton btnCancel;				//취소 버튼
	private JButton btnOk;					//확인 버튼
	private JLabel lblCount;				//인증시간 카운트 라벨
	private boolean overlapFlag = false;	//중복확인 완료여부 저장
	private static int sendCertifiFlag = 1;	//전송하기 버튼이면 1, 인증하기 버튼이면 2
	private static int certifiNum;			//인증번호
	private boolean certifiFlag = false;	//인증 완료여부 저장
	private CountThread countThread = null;	//인증시간 카운트 Thread
	private int year = 2003;				//생년 저장
	private int month = 1;					//생월 저장
	private int day = 1;					//생일 저장
	
	private ImageIcon icCancelET;			//취소 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icCancelRo;			//취소 버튼(Rollover) 이미지 아이콘
	private ImageIcon icCancelPr;			//취소 버튼(Pressed) 이미지 아이콘
	private ImageIcon icCertifiedET;		//인증하기 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icCertifiedRo;		//인증하기 버튼(Rollover) 이미지 아이콘
	private ImageIcon icCertifiedPr;		//인증하기 버튼(Pressed) 이미지 아이콘
	private ImageIcon icOkET;				//확인 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icOkRo;				//확인 버튼(Rollover) 이미지 아이콘
	private ImageIcon icOkPr;				//확인 버튼(Pressed) 이미지 아이콘
	private ImageIcon icOverlapChkET;		//중복확인 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icOverlapChkRo;		//중복확인 버튼(Rollover) 이미지 아이콘
	private ImageIcon icOverlapChkPr;		//중복확인 버튼(Pressed) 이미지 아이콘
	private ImageIcon icSendET;				//전송하기 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icSendRo;				//전송하기 버튼(Rollover) 이미지 아이콘
	private ImageIcon icSendPr;				//전송하기 버튼(Pressed) 이미지 아이콘
	
	
	//회원가입 화면
	public Join(Login login) {
		
        setLayout(new BorderLayout());
        setBackground(new Color(1, 168, 98));
        
		this.login = login;
        
		makeImageIcon();	//이미지 아이콘 생성
		
        makeTitle();		//타이틀 영역 생성
        makeInput();		//인풋필드 영역 생성
		
	}
	
	//이미지 아이콘 생성
	private void makeImageIcon() {
		
		icCancelET = new ImageIcon("images/join/Btn_Cancel_EnabledTrue.png");			//취소 버튼(EnabledTrue) 이미지 아이콘
		icCancelRo = new ImageIcon("images/join/Btn_Cancel_Rollover.png");				//취소 버튼(Rollover) 이미지 아이콘
		icCancelPr = new ImageIcon("images/join/Btn_Cancel_Pressed.png");				//취소 버튼(Pressed) 이미지 아이콘
		
		icCertifiedET = new ImageIcon("images/join/Btn_Certified_EnabledTrue.png");		//인증하기 버튼(EnabledTrue) 이미지 아이콘
		icCertifiedRo = new ImageIcon("images/join/Btn_Certified_Rollover.png");		//인증하기 버튼(Rollover) 이미지 아이콘
		icCertifiedPr = new ImageIcon("images/join/Btn_Certified_Pressed.png");			//인증하기 버튼(Pressed) 이미지 아이콘
		
		icOkET = new ImageIcon("images/join/Btn_Ok_EnabledTrue.png");					//확인 버튼(EnabledTrue) 이미지 아이콘
		icOkRo = new ImageIcon("images/join/Btn_Ok_Rollover.png");						//확인 버튼(Rollover) 이미지 아이콘
		icOkPr = new ImageIcon("images/join/Btn_Ok_Pressed.png");						//확인 버튼(Pressed) 이미지 아이콘
		
		icOverlapChkET = new ImageIcon("images/join/Btn_OverlapChk_EnabledTrue.png");	//중복확인 버튼(EnabledTrue) 이미지 아이콘
		icOverlapChkRo = new ImageIcon("images/join/Btn_OverlapChk_Rollover.png");		//중복확인 버튼(Rollover) 이미지 아이콘
		icOverlapChkPr = new ImageIcon("images/join/Btn_OverlapChk_Pressed.png");		//중복확인 버튼(Pressed) 이미지 아이콘
		
		icSendET = new ImageIcon("images/join/Btn_Send_EnabledTrue.png");				//전송하기 버튼(EnabledTrue) 이미지 아이콘
		icSendRo = new ImageIcon("images/join/Btn_Send_Rollover.png");					//전송하기 버튼(Rollover) 이미지 아이콘
		icSendPr = new ImageIcon("images/join/Btn_Send_Pressed.png");					//전송하기 버튼(Pressed) 이미지 아이콘
		
	}

	//타이틀 영역 생성
	private void makeTitle() {
		JPanel pnTitleBackground = new JPanel();
		pnTitleBackground.setLayout(new BorderLayout());
		pnTitleBackground.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		//타이틀 생성
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTitle.setBorder(new EmptyBorder(50, 0, 0, 0));	//패널 패딩 설정
		pnTitle.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JLabel lblTitle = new JLabel("Join");
		lblTitle.setFont(new Font("Santana-Black", Font.PLAIN, 50));	//라벨 폰트 설정
		
		pnTitle.add(lblTitle);
		pnTitleBackground.add(pnTitle, BorderLayout.CENTER);
		
		//로고 생성
		JPanel pnLogo = new JPanel();
		pnLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnLogo.setBorder(new EmptyBorder(20, 0, 0, 20));	//패널 패딩 설정
		pnLogo.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JLabel lblLogo = new JLabel(new ImageIcon("images/common/logo_titleBar.png"));
		
		pnLogo.add(lblLogo);
		pnTitleBackground.add(pnLogo, BorderLayout.EAST);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(120, 120));	//텍스트필드 크기 설정
		pnLeft.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		pnTitleBackground.add(pnLeft, BorderLayout.WEST);
		
		add(pnTitleBackground, BorderLayout.NORTH);
		
	}

	//인풋필드 영역 생성
	private void makeInput() {
		JPanel pnInputBackground = new JPanel();
		pnInputBackground.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnInputBackground.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(8, 1, 5, 5));
		pnInput.setBorder(new EmptyBorder(50, 0, 0, 20));	//패널 패딩 설정
		pnInput.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		
		//아이디 필드
		JPanel pnIdInput = new JPanel();
		pnIdInput.setLayout(new BorderLayout());
		pnIdInput.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		//아이디 텍스트필드 생성
		tfId = new JTextField("  아이디");
		tfId.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfId.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfId.addFocusListener(this);
		tfId.addKeyListener(this);
		
		//중복확인 버튼 생성
		btnOverlapChk = new JButton(icOverlapChkET);
		Boilerplate.setImageButton(btnOverlapChk, icOverlapChkRo, icOverlapChkPr, 90, 48);	//이미지 버튼 세팅
		btnOverlapChk.addActionListener(this);
		
		
		//비밀번호 텍스트필드 생성
		tfPw = new JPasswordField("  비밀번호");
		tfPw.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
		tfPw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfPw.setFont(new Font("맑은고딕", Font.PLAIN, tfPw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPw.addFocusListener(this);
		tfPw.addKeyListener(this);
		
		
		//비밀번호 확인 텍스트필드 생성
		tfPw2 = new JPasswordField("  비밀번호 확인");
		tfPw2.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
		tfPw2.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfPw2.setFont(new Font("맑은고딕", Font.PLAIN, tfPw2.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPw2.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPw2.addFocusListener(this);
		tfPw2.addKeyListener(this);
		
		
		//이름 텍스트필드 생성
		tfName = new JTextField("  이름");
		tfName.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfName.setFont(new Font("맑은고딕", Font.PLAIN, tfName.getFont().getSize()));	//텍스트필드 폰트 설정
		tfName.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfName.addFocusListener(this);
		tfName.addKeyListener(this);
		
		
		//생년월일 콤보박스
		JPanel pnBirthday = new JPanel();
		pnBirthday.setLayout(new BorderLayout(50, 0));
		pnBirthday.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//생년 콤보박스 생성
		cbYear = new JComboBox<Integer>(yearList);
		cbYear.setPreferredSize(new Dimension(108, 48));	//콤보박스 크기 설정
		cbYear.setFont(new Font("맑은고딕", Font.PLAIN, cbYear.getFont().getSize()));	//콤보박스 폰트 설정
		cbYear.addItemListener(this);
		
		//생월 콤보박스 생성
		cbMonth = new JComboBox<Integer>(monthList);
		cbMonth.setPreferredSize(new Dimension(108, 48));	//콤보박스 크기 설정
		cbMonth.setFont(new Font("맑은고딕", Font.PLAIN, cbMonth.getFont().getSize()));	//콤보박스 폰트 설정
		cbMonth.addItemListener(this);
		
		//생일 콤보박스 생성
		cbDay = new JComboBox<Integer>(dayList);
		cbDay.setPreferredSize(new Dimension(108, 48));	//콤보박스 크기 설정
		cbDay.setFont(new Font("맑은고딕", Font.PLAIN, cbDay.getFont().getSize()));	//콤보박스 폰트 설정
		cbDay.addItemListener(this);
		
		
		//휴대폰번호 필드
		JPanel pnPhonNumInput = new JPanel();
		pnPhonNumInput.setLayout(new BorderLayout());
		pnPhonNumInput.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//휴대폰번호 텍스트필드 생성
		tfPhonNum = new JTextField("  휴대폰번호('-'제외)");
		tfPhonNum.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfPhonNum.setFont(new Font("맑은고딕", Font.PLAIN, tfPhonNum.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPhonNum.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPhonNum.addFocusListener(this);
		tfPhonNum.addKeyListener(this);
		
		//전송하기 버튼 생성
		btnSend = new JButton(icSendET);
		Boilerplate.setImageButton(btnSend, icSendRo, icSendPr, 90, 48);	//이미지 버튼 세팅
		btnSend.addActionListener(this);
		
		
		//인증번호 필드
		JPanel pnCertif = new JPanel();
		pnCertif.setLayout(new BorderLayout());
		pnCertif.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//인증번호 텍스트필드 생성
		tfCertifiNum = new JTextField("  인증번호");
		tfCertifiNum.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfCertifiNum.setFont(new Font("맑은고딕", Font.PLAIN, tfCertifiNum.getFont().getSize()));	//텍스트필드 폰트 설정
		tfCertifiNum.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfCertifiNum.addFocusListener(this);
		tfCertifiNum.addKeyListener(this);
		
		//인증시간 카운트 라벨 생성
		lblCount = new JLabel();
		lblCount.setPreferredSize(new Dimension(90, 48));	//라벨 크기 설정
		lblCount.setFont(new Font("맑은고딕", Font.PLAIN, lblCount.getFont().getSize()));	//라벨 폰트 설정
		lblCount.setForeground(Color.RED);	//라벨 폰트 생상 설정
		lblCount.setHorizontalAlignment(JLabel.CENTER);	//라벨 가운데 정렬
		lblCount.setOpaque(true);	//라벨 투명도 설정
		lblCount.setBackground(Color.WHITE);	//라벨 색상 하얀색으로 설정
		
		
		//취소, 확인 버튼
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new BorderLayout());
		pnBtn.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//취소 버튼 생성
		btnCancel = new JButton(icCancelET);
		Boilerplate.setImageButton(btnCancel, icCancelRo, icCancelPr, 183, 50);	//이미지 버튼 세팅
		btnCancel.setEnabled(true);	//활성화 상태로 생성
		btnCancel.addActionListener(this);
		
		//확인 버튼 생성
		btnOk = new JButton(icOkET);
		Boilerplate.setImageButton(btnOk, icOkRo, icOkPr, 183, 50);	//이미지 버튼 세팅
		btnOk.addActionListener(this);
		
		
		pnIdInput.add(tfId, BorderLayout.CENTER);
		pnIdInput.add(btnOverlapChk, BorderLayout.EAST);
		
		pnBirthday.add(cbYear, BorderLayout.WEST);
		pnBirthday.add(cbMonth, BorderLayout.CENTER);
		pnBirthday.add(cbDay, BorderLayout.EAST);
		
		pnPhonNumInput.add(tfPhonNum, BorderLayout.CENTER);
		pnPhonNumInput.add(btnSend, BorderLayout.EAST);
		
		pnCertif.add(tfCertifiNum, BorderLayout.CENTER);
		pnCertif.add(lblCount, BorderLayout.EAST);
		
		pnBtn.add(btnCancel, BorderLayout.WEST);
		pnBtn.add(btnOk, BorderLayout.EAST);
		
		pnInput.add(pnIdInput);
		pnInput.add(tfPw);
		pnInput.add(tfPw2);
		pnInput.add(tfName);
		pnInput.add(pnBirthday);
		pnInput.add(pnPhonNumInput);
		pnInput.add(pnCertif);
		pnInput.add(pnBtn);
		
		pnInputBackground.add(pnInput);
		
		add(pnInputBackground, BorderLayout.CENTER);
		
	}
	
	//JTextField PlaceHolder 초기화 함수
	private void clearPH(JTextField tf) {
		
		tf.setText("");
		tf.setForeground(Color.BLACK);
		
	}
	
	//JTextField PlaceHolder 세팅 함수
	private void setPH(JTextField tf) {

		String str = "";
		
		if(tf == tfId) str = "  아이디";
		else if(tf == tfPw) str = "  비밀번호";
		else if(tf == tfPw2) str = "  비밀번호 확인";
		else if(tf == tfName) str = "  이름";
		else if(tf == tfPhonNum) str = "  휴대폰번호('-'제외)";
		else if(tf == tfCertifiNum) str = "  인증번호";
		
		tf.setEnabled(true);
		tf.setText(str);
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
		
		String str = "";
		
		if(tf == tfPw) str = "  비밀번호";
		else if(tf == tfPw2) str = "  비밀번호 확인";
		
		tf.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
		tf.setText(str);
		tf.setForeground(Color.GRAY);
		
	}
	
	//확인 버튼 활성화 조건 검사
	private void okBtnChk() {
		
		String pw1 = getPassword(tfPw);
		
		String pw2 = getPassword(tfPw2);
		
		if(!tfId.getText().equals("  아이디") && !pw1.equals("  비밀번호") && !pw2.equals("  비밀번호 확인") &&  !tfName.getText().equals("  이름")
				&& !tfPhonNum.getText().equals("  휴대폰번호('-'제외)")&& !tfCertifiNum.getText().equals("  인증번호")) {
			if(overlapFlag && Boilerplate.pwValidation(pw1) && Boilerplate.pwValidation(pw2) && Boilerplate.nameValidation(tfName.getText())
					&& Boilerplate.birthyearValidation(year) && Boilerplate.birthmonthValidation(month) && Boilerplate.birthdayteValidation(month, day) && Boilerplate.phonNumValidation(tfPhonNum.getText()) && certifiFlag) {
				btnOk.setEnabled(true);	//확인 버튼 활성화
			} else {
				btnOk.setEnabled(false);	//확인 버튼 비활성화
			}
		}
		
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
		
		if(obj == btnOverlapChk) {
			//아이디 유효성 검사
			if(Boilerplate.idValidation(tfId.getText())) {
				System.out.println("(Join) 아이디 중복여부 검사");
				
				usersInId = tfId.getText();	//사용자가 아이디 텍스트필드에 입력한 데이터 저장
				
				ResultSet rs = DB.getResult("select * from USERS WHERE USERSID like '" + usersInId + "'");	//USERS 테이블에서 일치하는 사용자 존재 유무 조회
				
				try {
					if(rs.next()) {
						System.out.println("(Join) 아이디 중복");
						JOptionPane.showMessageDialog(login, "이미 사용중인 아이디입니다.\n다시 시도해주세요.", "아이디 중복", JOptionPane.ERROR_MESSAGE);
						
						btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
						setPH(tfId);	//PlaceHolder 세팅
					} else {
						System.out.println("(Join) 아이디 사용가능");
						
						int result = JOptionPane.showConfirmDialog(login, "사용 가능한 아이디입니다.\n" + usersInId + "로 사용하시겠습니까?", "아이디 사용 가능", JOptionPane.YES_NO_OPTION);
						
						if(result == JOptionPane.YES_OPTION) {
							tfId.setEnabled(false);	//아이디 텍스트필드 비활성화
							btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
							overlapFlag = true;
							okBtnChk();
						} else {
							btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
							setPH(tfId);	//PlaceHolder 세팅
						}
					}
				} catch (SQLException e1) {
					System.out.println("(Join) 예외발생 : DB 조회에 실패했습니다.");
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(login, "사용할 수 없는 아이디입니다.\n아이디는 영문, 숫자 조합의 5자리 이상 12자리 이하로 사용 가능하며,\n첫 자리에 숫자를 사용할 수 없습니다.\n다시 시도해주세요.", "규칙 위배", JOptionPane.ERROR_MESSAGE);
				
				btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
				setPH(tfId);	//PlaceHolder 세팅
			}
		} else if(obj == btnSend && sendCertifiFlag == 1 && Boilerplate.phonNumValidation(tfPhonNum.getText())) {
			tfPhonNum.setEnabled(false);	//휴대폰번호 텍스트필드 비활성화
			
			System.out.println("(Join) 인증번호 발송");
			
			certifiNum = Boilerplate.certificationNum();	//6자리 숫자 난수 생성
			JOptionPane.showMessageDialog(login, "인증번호는 [" + certifiNum + "]입니다.", tfPhonNum.getText() + " 문자", JOptionPane.PLAIN_MESSAGE);
			
			//인증하기 버튼으로 변경
			sendCertifiFlag = 2;
			btnSend.setIcon(icCertifiedET);
			btnSend.setRolloverIcon(icCertifiedRo);
			btnSend.setPressedIcon(icCertifiedPr);
			btnSend.setEnabled(false);	//인증하기 버튼 비활성화
			
			//인증시간 카운트
			countThread = new CountThread(1, lblCount, btnSend, tfPhonNum, tfCertifiNum);	//카운트 스레드 생성
			countThread.start();	//카운트 스레드 시작
			System.out.println("(Join) Count Thread 실행");
		} else if(obj == btnSend && sendCertifiFlag == 2 && Boilerplate.certifiNumValidation(tfCertifiNum.getText())) {
			System.out.println("(Join) 인증번호 일치여부 검사");
			
			if(certifiNum == Integer.parseInt(tfCertifiNum.getText())) {
				System.out.println("(Join) 인증완료");
				
				JOptionPane.showMessageDialog(login, "인증이 완료되었습니다.", "인증 완료", JOptionPane.PLAIN_MESSAGE);
				
				if(countThread.isAlive()) countThread.interrupt();		//카운트 쓰레드 종료
				
				tfCertifiNum.setEnabled(false);	//인증번호 텍스트필드 비활성화
				btnSend.setEnabled(false);		//인증하기 버튼 비활성화
				
				certifiFlag  = true;
				
				okBtnChk();
			} else {
				System.out.println("(Join) 인증실패");
				
				JOptionPane.showMessageDialog(login, "인증번호가 일치하지 않습니다.\n다시 시도해주세요.", "인증 실패", JOptionPane.ERROR_MESSAGE);
				
				if(countThread.isAlive()) countThread.interrupt();		//카운트 쓰레드 종료
				
				setPH(tfPhonNum);		//PlaceHolder 세팅
				setPH(tfCertifiNum);	//PlaceHolder 세팅
				
				certifiNum = 0;	//인증번호 만료 처리
				
				//전송하기 버튼으로 변경
				sendCertifiFlag = 1;
				btnSend.setIcon(icSendET);
				btnSend.setRolloverIcon(icSendRo);
				btnSend.setPressedIcon(icSendPr);
				btnSend.setEnabled(false);	//전송하기 버튼 비활성화
			}
		} else if(obj == btnCancel) {
			overlapFlag = false;	//중복확인 초기화
			sendCertifiFlag = 1;	//전송하기로 변경
			certifiFlag = false;	//인증완료 초기화
			
			login.setVisible(true);	//로그인 화면 활성화
			setVisible(false);	//회원가입 화면 비활성화
		} else if(obj == btnOk) {
			
			usersInId = tfId.getText();					//입력받은 아이디 저장
			usersInPw = getPassword(tfPw);				//입력받은 비밀번호 저장
			usersInName = tfName.getText();				//입력받은 이름 저장
			
			//입력받은 생년월일 YYYYMMDD 형태로 저장
			usersInBirthday = Integer.toString(year);
			if(month < 10) usersInBirthday = usersInBirthday + "0" + Integer.toString(month);
			else usersInBirthday = usersInBirthday + Integer.toString(month);
			if(day < 10) usersInBirthday = usersInBirthday + "0" + Integer.toString(day);
			else usersInBirthday = usersInBirthday + Integer.toString(day);
			
			usersInPhoneNum = tfPhonNum.getText();		//입력받은 휴대폰번호 저장
			
			//입력받은 데이터 유효성 검사
			if(Boilerplate.idValidation(usersInId) && Boilerplate.nameValidation(usersInName) && Boilerplate.birthdayValidation(usersInBirthday) && Boilerplate.phonNumValidation(usersInPhoneNum)) {
				
				String pw2 = getPassword(tfPw2);
				
				if(usersInPw.equals(pw2)) {	//비밀번호와 비밀번호 확인 일치여부 검사
					if(Boilerplate.pwValidation(usersInPw)) {
						String pwSalt = Encryption.Salt();	//SHA512 암호화에 사용할 난수 생성
						usersInPw = Encryption.SHA512(usersInPw, pwSalt);	//입력받은 비밀번호를 Salt 값으로 SHA512 암호화
						
						String sqlInsert = "INSERT INTO ALLNIGHT.USERS (USERSID, USERSPW, USERSNAME, USERSBIRTHDAY, USERSPHONNUM, USERSCASH, USERSPWSALT)"
								+ " VALUES('" + usersInId + "', '" + usersInPw + "', '" + usersInName + "', '" + usersInBirthday + "', '" + usersInPhoneNum + "', 0, '" + pwSalt + "')";	//회원정보 Insert문 생성
						DB.executeSQL(sqlInsert);	//DB로 Insert문 전송
						
						LocalDateTime nowDateTime = LocalDateTime.now();	//현재 날짜와 시간
						String formatedDateTime = nowDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						
						sqlInsert = "ALTER session set NLS_DATE_FORMAT = 'yyyy-mm-dd hh24:mi:ss'";	//날짜 포맷 설정
						DB.executeSQL(sqlInsert);	//DB로 Insert문 전송
						
						sqlInsert = "INSERT INTO ALLNIGHT.POINT (POINTID, USERSID, ORDERDATE, POINTSTATUS, POINTVALUE, POINT)"
								+ "VALUES(1, '" + usersInId + "', '" + formatedDateTime + "', '세팅', 0, 0)";		//회원 포인트 Insert문 생성
						DB.executeSQL(sqlInsert);	//DB로 Insert문 전송
						
						JOptionPane.showMessageDialog(login, "회원가입이 완료되었습니다.", "회원가입 완료", JOptionPane.PLAIN_MESSAGE);
						
						login.setVisible(true);	//로그인 화면 활성화
						setVisible(false);	//회원가입 화면 비활성화
					} else {
						JOptionPane.showMessageDialog(login, "사용할 수 없는 비밀번호입니다.\n비밀번호는 영문, 숫자, 특수문자 조합으로\n최소 8자리 이상, 최대 15자리 이하로 사용 가능합니다.\n다시 시도해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
						
						setPwPH(tfPw2);	//비밀번호 확인 텍스트필드 초기화
						btnOk.setEnabled(false);	//확인 버튼 비활성화
					}
				} else {
					JOptionPane.showMessageDialog(login, "비밀번호와 비밀번호 확인이 일치하지 않습니다.\n다시 시도해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
					
					setPwPH(tfPw2);	//비밀번호 확인 텍스트필드 초기화
					btnOk.setEnabled(false);	//확인 버튼 비활성화
				}
			} else {
				JOptionPane.showMessageDialog(login, "정확하게 입력되지 않은 항목이 있습니다.\n다시 시도해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
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
			
			String pw1 = getPassword(tfPw);
			
			//비밀번호 텍스트필드 PlaceHolder
			if(pw1.equals("  비밀번호")) {
				clearPwPH(tfPw);	//PlaceHolder 초기화
			}
		} else if(obj == tfPw2) {
			
			String pw2 = getPassword(tfPw2);
			
			//비밀번호 확인 텍스트필드 PlaceHolder
			if(pw2.equals("  비밀번호 확인")) {
				clearPwPH(tfPw2);	//PlaceHolder 초기화
			}
		} else if(obj == tfName) {
			//이름 텍스트필드 PlaceHolder
			if(tfName.getText().equals("  이름")) {
				clearPH(tfName);	//PlaceHolder 초기화
			}
		} else if(obj == tfPhonNum) {
			//휴대폰번호 텍스트필드 PlaceHolder
			if(tfPhonNum.getText().equals("  휴대폰번호('-'제외)")) {
				clearPH(tfPhonNum);	//PlaceHolder 초기화
			}
		} else if(obj == tfCertifiNum) {
			//인증번호 텍스트필드 PlaceHolder
			if(tfCertifiNum.getText().equals("  인증번호")) {
				clearPH(tfCertifiNum);	//PlaceHolder 초기화
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
			
			String pw1 = getPassword(tfPw);
			
			//비밀번호 텍스트필드 PlaceHolder
			if(pw1.isEmpty()) {
				setPwPH(tfPw);	//PlaceHolder 세팅
			}
		} else if(obj == tfPw2) {
			
			String pw2 = getPassword(tfPw2);
			
			//비밀번호 확인 텍스트필드 PlaceHolder
			if(pw2.isEmpty()) {
				setPwPH(tfPw2);	//PlaceHolder 세팅
			}
		} else if(obj == tfName) {
			//이름 텍스트필드 PlaceHolder
			if(tfName.getText().isEmpty()) {
				setPH(tfName);	//PlaceHolder 세팅
			}
		} else if(obj == tfPhonNum) {
			//휴대폰번호 텍스트필드 PlaceHolder
			if(tfPhonNum.getText().isEmpty()) {
				setPH(tfPhonNum);	//PlaceHolder 세팅
			}
		} else if(obj == tfCertifiNum) {
			//인증번호 텍스트필드 PlaceHolder
			if(tfCertifiNum.getText().isEmpty()) {
				setPH(tfCertifiNum);	//PlaceHolder 세팅
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
		
		if(!tfId.getText().equals("  아이디")) {
			if(tfId.getText().length() >= 5 && tfId.getText().length() <= 12 && !overlapFlag) {	//중복확인 버튼 활성화 조건 검사
				btnOverlapChk.setEnabled(true);	//중복확인 버튼 활성화
			} else {
				btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
			}
		}
		
		if(!tfPhonNum.getText().equals("  휴대폰번호('-'제외)") && sendCertifiFlag == 1 && !certifiFlag) {
			if(Boilerplate.phonNumValidation(tfPhonNum.getText())) {	//전송하기 버튼 활성화 조건 검사
				btnSend.setEnabled(true);	//전송하기 버튼 활성화
			} else {
				btnSend.setEnabled(false);	//전송하기 버튼 비활성화
			}
		}
		
		if(!tfCertifiNum.getText().equals("  인증번호") && sendCertifiFlag == 2 && !certifiFlag) {
			if(Boilerplate.certifiNumValidation(tfCertifiNum.getText())) {	//인증하기 버튼 활성화 조건 검사
				btnSend.setEnabled(true);	//인증하기 버튼 활성화
			} else {
				btnSend.setEnabled(false);	//인증하기 버튼 비활성화
			}
		}
		
		okBtnChk();	//확인 버튼 활성화 조건 검사
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == cbYear) {
			year = (int) cbYear.getSelectedItem();
		} else if(obj == cbMonth) {
			month = (int) cbMonth.getSelectedItem();
			
			//선택된 생월에 따라 선택 가능한 생일 항목 수정 
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(cbDay.getItemCount() == 30) cbDay.addItem(31);
				else if(cbDay.getItemCount() == 28) {
					cbDay.addItem(29);
					cbDay.addItem(30);
					cbDay.addItem(31);
				}
			} else if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(cbDay.getItemCount() == 31) cbDay.removeItem(31);
				else if(cbDay.getItemCount() == 28) {
					cbDay.addItem(29);
					cbDay.addItem(30);
				}
			} else if(month == 2) {
				if(cbDay.getItemCount() == 31) {
					cbDay.removeItem(31);
					cbDay.removeItem(30);
					cbDay.removeItem(29);
				}
				else if(cbDay.getItemCount() == 30) {
					cbDay.removeItem(30);
					cbDay.removeItem(29);
				}
			}
		} else if(obj == cbDay) {
			day = (int) cbDay.getSelectedItem();
		}
		
	}
	
	public static void setSendCertifiFlag(int sendCertifiFlagIn) {
		
		sendCertifiFlag = sendCertifiFlagIn;
		
	}

	public static void setCertifiNum(int certifiNumIn) {
		
		certifiNum = certifiNumIn;
		
	}

}
