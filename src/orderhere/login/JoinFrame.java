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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import orderhere.common.DB;
import orderhere.common.Encryption;
import orderhere.common.Validation;

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
* 2022.05.21		TaeJeong Park		레이아웃 구현 완료
*/
public class JoinFrame extends JFrame implements ActionListener, FocusListener, KeyListener {
	
	private String usersInId;		//사용자에게 입력 받은 아이디
	private String usersInPw;		//사용자에게 입력 받은 비밀번호
	private String usersInName; 	//사용자에게 입력 받은 이름
	private String usersInBirthday; //사용자에게 입력 받은 생년월일
	private int usersInPhoneNum; 	//사용자에게 입력 받은 휴대폰번호
	
	private LoginFrame lf;
	private JPanel pnBackground;
	private JTextField tfId;
	private JButton btnOverlapChk;
	private JPasswordField tfPw;
	private JPasswordField tfPw2;
	private JTextField tfName;
	private JComboBox<Integer> cbYear;
	private Integer[] yearList = {2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903};
	private JComboBox<Integer> cbMonth;
	private Integer[] monthList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private JComboBox<Integer> cbDay;
	private Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private JTextField tfPhonNum;
	private AbstractButton btnSend;
	private JTextField tfCertifiNum;
	private JButton btnCancel;
	private JButton btnOk;
	private JLabel lblCount;
	private boolean overlapFlag = false;
	
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
		
		lf = this.lf;
        
        makeTitle();	//타이틀 영역 생성
        makeInput();	//인풋필드 영역 생성
        DB.init();		//DB 연결

        add(pnBackground, BorderLayout.CENTER);
        
        setVisible(true);
		
	}

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

	private void makeInput() {
		
		JPanel pnInputBackground = new JPanel();
		pnInputBackground.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnInputBackground.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new GridLayout(8, 1, 5, 5));
//		pnInput.setBorder(new EmptyBorder(30, 0, 0, 0));	//패널 패딩 설정
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
		btnOverlapChk = new JButton(new ImageIcon("images/join/Btn_OverlapChk_EnabledTrue.png"));
		btnOverlapChk.setRolloverIcon(new ImageIcon("images/join/Btn_OverlapChk_Rollover.png"));
		btnOverlapChk.setPressedIcon(new ImageIcon("images/join/Btn_OverlapChk_Pressed.png"));
		btnOverlapChk.setBorderPainted(false);					//버튼 테두리 설정
		btnOverlapChk.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnOverlapChk.setFocusPainted(false);					//포커스 표시 설정
		btnOverlapChk.setOpaque(false);							//투명하게 설정
		btnOverlapChk.setPreferredSize(new Dimension(90, 48));	//버튼 크기 설정
		btnOverlapChk.setEnabled(false);						//비활성화 상태로 생성
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
		
		//생월 콤보박스 생성
		cbMonth = new JComboBox<Integer>(monthList);
		cbMonth.setPreferredSize(new Dimension(108, 48));	//콤보박스 크기 설정
		cbMonth.setFont(new Font("맑은고딕", Font.PLAIN, cbMonth.getFont().getSize()));	//콤보박스 폰트 설정
		
		//생일 콤보박스 생성
		cbDay = new JComboBox<Integer>(dayList);
		cbDay.setPreferredSize(new Dimension(108, 48));	//콤보박스 크기 설정
		cbDay.setFont(new Font("맑은고딕", Font.PLAIN, cbDay.getFont().getSize()));	//콤보박스 폰트 설정
		
		
		//휴대폰번호 필드
		JPanel pnPhonNumInput = new JPanel();
		pnPhonNumInput.setLayout(new BorderLayout());
		pnPhonNumInput.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//휴대폰번호 텍스트필드 생성
		tfPhonNum = new JTextField("  휴대폰번호");
		tfPhonNum.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfPhonNum.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPhonNum.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPhonNum.addFocusListener(this);
		tfPhonNum.addKeyListener(this);
		
		//전송하기 버튼 생성
		btnSend = new JButton(new ImageIcon("images/join/Btn_Send_EnabledTrue.png"));
		btnSend.setRolloverIcon(new ImageIcon("images/join/Btn_Send_Rollover.png"));
		btnSend.setPressedIcon(new ImageIcon("images/join/Btn_Send_Pressed.png"));
		btnSend.setBorderPainted(false);					//버튼 테두리 설정
		btnSend.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnSend.setFocusPainted(false);						//포커스 표시 설정
		btnSend.setOpaque(false);							//투명하게 설정
		btnSend.setPreferredSize(new Dimension(90, 48));	//버튼 크기 설정
		btnSend.setEnabled(false);							//비활성화 상태로 생성
		btnSend.addActionListener(this);
		
		
		//인증번호 필드
		JPanel pnCertif = new JPanel();
		pnCertif.setLayout(new BorderLayout());
		pnCertif.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//인증번호 텍스트필드 생성
		tfCertifiNum = new JTextField("  인증번호");
		tfCertifiNum.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfCertifiNum.setFont(new Font("맑은고딕", Font.PLAIN, tfName.getFont().getSize()));	//텍스트필드 폰트 설정
		tfCertifiNum.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfCertifiNum.addFocusListener(this);
		tfCertifiNum.addKeyListener(this);
		
		//인증시간 카운트 라벨 생성
		lblCount = new JLabel();
		lblCount.setPreferredSize(new Dimension(90, 48));	//라벨 크기 설정
		lblCount.setFont(new Font("맑은고딕", Font.PLAIN, tfId.getFont().getSize()));	//라벨 폰트 설정
		lblCount.setForeground(Color.RED);	//텍스트필드 폰트 생상 설정
		lblCount.setHorizontalAlignment(JLabel.CENTER);	//텍스트필드 가운데 정렬
		
		
		//취소, 확인 버튼
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new BorderLayout());
		pnBtn.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//취소 버튼 생성
		btnCancel = new JButton(new ImageIcon("images/join/Btn_Cancel_EnabledTrue.png"));
		btnCancel.setRolloverIcon(new ImageIcon("images/join/Btn_Cancel_Rollover.png"));
		btnCancel.setPressedIcon(new ImageIcon("images/join/Btn_Cancel_Pressed.png"));
		btnCancel.setBorderPainted(false);					//버튼 테두리 설정
		btnCancel.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnCancel.setFocusPainted(false);					//포커스 표시 설정
		btnCancel.setOpaque(false);							//투명하게 설정
		btnCancel.setPreferredSize(new Dimension(183, 50));	//버튼 크기 설정
		btnCancel.addActionListener(this);
		
		//확인 버튼 생성
		btnOk = new JButton(new ImageIcon("images/join/Btn_Ok_EnabledTrue.png"));
		btnOk.setRolloverIcon(new ImageIcon("images/join/Btn_Ok_Rollover.png"));
		btnOk.setPressedIcon(new ImageIcon("images/join/Btn_Ok_Pressed.png"));
		btnOk.setBorderPainted(false);					//버튼 테두리 설정
		btnOk.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnOk.setFocusPainted(false);					//포커스 표시 설정
		btnOk.setOpaque(false);							//투명하게 설정
		btnOk.setPreferredSize(new Dimension(183, 50));	//버튼 크기 설정
		btnOk.setEnabled(false);						//비활성화 상태로 생성
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
		pnBackground.add(pnInputBackground, BorderLayout.CENTER);
		
		
		String salt = Encryption.Salt();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == btnOverlapChk) {
			//아이디 유효성 검사
			if(Validation.idValidation(tfId.getText())) {
				System.out.println("중복확인 진행");
				
				usersInId = tfId.getText();	//사용자가 아이디 텍스트필드에 입력한 데이터 저장
				
				ResultSet rs = DB.getResult("select * from USERS WHERE USERSID like '" + usersInId + "'");	//USERS 테이블에서 일치하는 사용자 존재 유무 조회
				
				try {
					if(rs.next()) {
						System.out.println("아이디 중복");
						JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.\n다시 시도해주세요.", "아이디 중복", JOptionPane.ERROR_MESSAGE);
					} else {
						System.out.println("아이디 사용가능");
						
						int result = JOptionPane.showConfirmDialog(null, "사용 가능한 아이디입니다.\n" + usersInId + "로 사용하시겠습니까?", "아이디 사용 가능", JOptionPane.YES_NO_OPTION);
						
						if(result == JOptionPane.YES_OPTION) {
							tfId.setEnabled(false);	//아이디 텍스트필드 비활성화
							btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
							overlapFlag = true;
						} else {
							tfId.setText("  아이디");
							tfId.setForeground(Color.GRAY);
							btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
						}
					}
				} catch (SQLException e1) {
					System.out.println("예외발생 : 일치하는 회원 정보가 없습니다.");
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디입니다.\n아이디는 영문, 숫자 조합의 5자리 이상 12자리 이하로 사용 가능하며,\n첫 자리에 숫자를 사용할 수 없습니다.\n다시 시도해주세요.", "규칙 위배", JOptionPane.ERROR_MESSAGE);
			}
		} else if(obj == btnSend) {
			
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
		} else if(obj == tfPw2) {
			//비밀번호 확인 텍스트필드 PlaceHolder
			if(tfPw2.getText().equals("  비밀번호 확인")) {
				tfPw2.setEchoChar('●');	//작성되는 문자 '●'로 변환 설정
				tfPw2.setText("");
				tfPw2.setForeground(Color.BLACK);
			}
		} else if(obj == tfName) {
			//이름 텍스트필드 PlaceHolder
			if(tfName.getText().equals("  이름")) {
				tfName.setText("");
				tfName.setForeground(Color.BLACK);
			}
		} else if(obj == tfPhonNum) {
			//휴대폰번호 텍스트필드 PlaceHolder
			if(tfPhonNum.getText().equals("  휴대폰번호")) {
				tfPhonNum.setText("");
				tfPhonNum.setForeground(Color.BLACK);
			}
		} else if(obj == tfCertifiNum) {
			//인증번호 텍스트필드 PlaceHolder
			if(tfCertifiNum.getText().equals("  인증번호")) {
				tfCertifiNum.setText("");
				tfCertifiNum.setForeground(Color.BLACK);
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
		} else if(obj == tfPw2) {
			//비밀번호 확인 텍스트필드 PlaceHolder
			if(tfPw2.getText().isEmpty()) {
				tfPw2.setText("  비밀번호 확인");
				tfPw2.setEchoChar((char)0);	//작성되는 문자 그대로 보여지게 설정
				tfPw2.setForeground(Color.GRAY);
			}
		} else if(obj == tfName) {
			//이름 텍스트필드 PlaceHolder
			if(tfName.getText().isEmpty()) {
				tfName.setText("  이름");
				tfName.setForeground(Color.GRAY);
			}
		} else if(obj == tfPhonNum) {
			//휴대폰번호 텍스트필드 PlaceHolder
			if(tfPhonNum.getText().isEmpty()) {
				tfPhonNum.setText("  휴대폰번호");
				tfPhonNum.setForeground(Color.GRAY);
			}
		} else if(obj == tfCertifiNum) {
			//인증번호 텍스트필드 PlaceHolder
			if(tfCertifiNum.getText().isEmpty()) {
				tfCertifiNum.setText("  인증번호");
				tfCertifiNum.setForeground(Color.GRAY);
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
			if(tfId.getText().length() >= 5 && tfId.getText().length() <= 12) {	//중복확인 버튼 활성화 조건 검사
				btnOverlapChk.setEnabled(true);	//중복확인 버튼 활성화
				overlapFlag = true;
			} else {
				btnOverlapChk.setEnabled(false);	//중복확인 버튼 비활성화
				overlapFlag = false;
			}
		}
		
		if(!tfPhonNum.getText().equals("  휴대폰번호")) {
			if(Validation.phonNumValidation(tfPhonNum.getText())) {	//전송하기 버튼 활성화 조건 검사
				tfPhonNum.setEnabled(true);	//전송하기 버튼 활성화
			} else {
				tfPhonNum.setEnabled(false);	//전송하기 버튼 비활성화
			}
		}
		
		if(!tfId.getText().equals("  아이디") && !tfPw.getText().equals("  비밀번호") && !tfPw2.getText().equals("  비밀번호 확인") &&  !tfName.getText().equals("  이름") && !tfPhonNum.getText().equals("  휴대폰번호")) {
			if(overlapFlag) {
				
			}
		}
		
	}

}
