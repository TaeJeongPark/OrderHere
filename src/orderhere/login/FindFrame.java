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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import orderhere.common.DB;

/**
* @packageName	: orderhere.login
* @fileName		: FindFrame.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: 아이디/비밀번호 찾기 화면(0002)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
* 2022.05.22		TaeJeong Park		레이아웃 구현 완료
*/
public class FindFrame extends JFrame implements ActionListener, FocusListener, KeyListener, ItemListener {

	private LoginFrame lf;
	private JButton btnBack;
	private JTabbedPane tabPane;
	
	//아이디 찾기
	private JTextField tfNameId;			//이름 입력 텍스트필드
	private JComboBox<Integer> cbYear;		//생년 콤보박스
	private Integer[] yearList = {2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903};
	private JComboBox<Integer> cbMonth;		//생월 콤보박스
	private Integer[] monthList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private JComboBox<Integer> cbDay;		//생일 콤보박스
	private Integer[] dayList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
	private JTextField tfPhonNumId;			//휴대폰번호 입력 텍스트필드
	private JButton btnSendId;				//전송 버튼
	private JTextField tfCertifiNumId;		//인증번호 입력 텍스트필드
	private JLabel lblCountId;				//인증시간 카운트 라벨
	private JButton btnIdFind;				//아이디 찾기 버튼
	
	//비밀번호 찾기
	private JTextField tfIdPw;				//아이디 입력 텍스트필드
	private JTextField tfPhonNumPw;			//휴대폰번호 입력 텍스트필드
	private JButton btnSendPw;				//전송 버튼
	private JTextField tfCertifiNumPw;		//인증번호 입력 텍스트필드
	private JLabel lblCountPw;				//인증시간 카운트 라벨
	private JButton btnPwFind;				//비밀번호 찾기 버튼
	private JTextField tfNewPwPw;			//새 비밀번호 입력 텍스트필드
	private JTextField tfNewPw2Pw;			//새 비밀번호 확인 입력 텍스트필드
	private JButton btnPwChange;			//비밀번호 변경 버튼
	
	//로그인 프레임
	public FindFrame(String title, LoginFrame lf) {
		
		setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
        setSize(1050, 789);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(new Color(1, 168, 98));
        
        
        //타이틀바 아이콘 설정
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("images/common/logo_titleBar.png");
        setIconImage(img);
		
		this.lf = lf;
        
		tabPane = new JTabbedPane(JTabbedPane.TOP);	//탭 팬 생성
		
        makeTitle();	//타이틀 영역 생성
        makeIdFind();	//아이디 조회 영역 생성
        makePwFind();	//비밀번호 조회 영역 생성
        DB.init();		//DB 연결

        add(tabPane, BorderLayout.CENTER);

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
		
		JLabel lblTitle = new JLabel("Find ID/PW");
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
		
		//뒤로가기 버튼 생성
		JPanel pnBack = new JPanel();
		pnBack.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnBack.setPreferredSize(new Dimension(120, 120));	//텍스트필드 크기 설정
		pnBack.setBorder(new EmptyBorder(30, 0, 0, 0));		//패널 패딩 설정
		pnBack.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		btnBack = new JButton(new ImageIcon("images/common/Btn_Back_EnabledTrue.png"));
		btnBack.setRolloverIcon(new ImageIcon("images/common/Btn_Back_Rollover.png"));
		btnBack.setPressedIcon(new ImageIcon("images/common/Btn_Back_Pressed.png"));
		btnBack.setBorderPainted(false);					//버튼 테두리 설정
		btnBack.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnBack.setFocusPainted(false);						//포커스 표시 설정
		btnBack.setOpaque(false);							//투명하게 설정
		btnBack.setPreferredSize(new Dimension(75, 73));	//버튼 크기 설정
		btnBack.addActionListener(this);
		
		pnBack.add(btnBack);
		pnTitleBackground.add(pnBack, BorderLayout.WEST);
		
		add(pnTitleBackground, BorderLayout.NORTH);
		
	}

	private void makeIdFind() {
		
		JPanel pnIdFindBg = new JPanel();
		pnIdFindBg.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnIdFindBg.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JPanel pnIdFindInput = new JPanel();
		pnIdFindInput.setLayout(new GridLayout(6, 1, 5, 5));
		pnIdFindInput.setBorder(new EmptyBorder(30, 0, 0, 0));	//패널 패딩 설정
		pnIdFindInput.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		
		//안내 메시지 생성
		JLabel lblInfo = new JLabel("아이디를 찾기 위해 아래의 정보를 입력해주세요", JLabel.CENTER);
		lblInfo.setFont(new Font("맑은고딕", Font.BOLD, 18));		//라벨 폰트 설정
		
		
		//이름 텍스트필드 생성
		tfNameId = new JTextField("  이름");
		tfNameId.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfNameId.setFont(new Font("맑은고딕", Font.PLAIN, tfNameId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfNameId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfNameId.addFocusListener(this);
		tfNameId.addKeyListener(this);
		
		
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
		tfPhonNumId = new JTextField("  휴대폰번호('-'제외)");
		tfPhonNumId.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfPhonNumId.setFont(new Font("맑은고딕", Font.PLAIN, tfPhonNumId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPhonNumId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPhonNumId.addFocusListener(this);
		tfPhonNumId.addKeyListener(this);
		
		//전송하기 버튼 생성
		btnSendId = new JButton(new ImageIcon("images/join/Btn_Send_EnabledTrue.png"));
		btnSendId.setRolloverIcon(new ImageIcon("images/join/Btn_Send_Rollover.png"));
		btnSendId.setPressedIcon(new ImageIcon("images/join/Btn_Send_Pressed.png"));
		btnSendId.setBorderPainted(false);					//버튼 테두리 설정
		btnSendId.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnSendId.setFocusPainted(false);					//포커스 표시 설정
		btnSendId.setOpaque(false);							//투명하게 설정
		btnSendId.setPreferredSize(new Dimension(90, 48));	//버튼 크기 설정
		btnSendId.setEnabled(false);						//비활성화 상태로 생성
		btnSendId.addActionListener(this);
		
		
		//인증번호 필드
		JPanel pnCertif = new JPanel();
		pnCertif.setLayout(new BorderLayout());
		pnCertif.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//인증번호 텍스트필드 생성
		tfCertifiNumId = new JTextField("  인증번호");
		tfCertifiNumId.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfCertifiNumId.setFont(new Font("맑은고딕", Font.PLAIN, tfNameId.getFont().getSize()));	//텍스트필드 폰트 설정
		tfCertifiNumId.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfCertifiNumId.addFocusListener(this);
		tfCertifiNumId.addKeyListener(this);
		
		//인증시간 카운트 라벨 생성
		lblCountId = new JLabel();
		lblCountId.setPreferredSize(new Dimension(90, 48));	//라벨 크기 설정
		lblCountId.setFont(new Font("맑은고딕", Font.PLAIN, lblCountId.getFont().getSize()));	//라벨 폰트 설정
		lblCountId.setForeground(Color.RED);	//라벨 폰트 생상 설정
		lblCountId.setHorizontalAlignment(JLabel.CENTER);	//라벨 가운데 정렬
		lblCountId.setOpaque(true);	//라벨 투명도 설정
		lblCountId.setBackground(Color.WHITE);	//라벨 색상 하얀색으로 설정
		
		
		//아이디 찾기 버튼 영역
		JPanel pnFindBtn = new JPanel();
		pnFindBtn.setLayout(new FlowLayout());
		pnFindBtn.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//아이디 찾기 버튼 생성
		btnIdFind = new JButton(new ImageIcon("images/findidpw/Btn_Find_EnabledTrue.png"));
		btnIdFind.setRolloverIcon(new ImageIcon("images/findidpw/Btn_Find_Rollover.png"));
		btnIdFind.setPressedIcon(new ImageIcon("images/findidpw/Btn_Find_Pressed.png"));
		btnIdFind.setBorderPainted(false);					//버튼 테두리 설정
		btnIdFind.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnIdFind.setFocusPainted(false);					//포커스 표시 설정
		btnIdFind.setOpaque(false);							//투명하게 설정
		btnIdFind.setPreferredSize(new Dimension(183, 50));	//버튼 크기 설정
		btnIdFind.addActionListener(this);
		
		
		pnBirthday.add(cbYear, BorderLayout.WEST);
		pnBirthday.add(cbMonth, BorderLayout.CENTER);
		pnBirthday.add(cbDay, BorderLayout.EAST);
		
		pnPhonNumInput.add(tfPhonNumId, BorderLayout.CENTER);
		pnPhonNumInput.add(btnSendId, BorderLayout.EAST);
		
		pnCertif.add(tfCertifiNumId, BorderLayout.CENTER);
		pnCertif.add(lblCountId, BorderLayout.EAST);
		
		pnFindBtn.add(btnIdFind);
		
		pnIdFindInput.add(lblInfo);
		pnIdFindInput.add(tfNameId);
		pnIdFindInput.add(pnBirthday);
		pnIdFindInput.add(pnPhonNumInput);
		pnIdFindInput.add(pnCertif);
		pnIdFindInput.add(pnFindBtn);
		
		pnIdFindBg.add(pnIdFindInput);
		
		tabPane.addTab("아이디찾기", pnIdFindBg);
		
	}
	
	private void makePwFind() {
		
		JPanel pnPwFindBg = new JPanel();
		pnPwFindBg.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnPwFindBg.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		JPanel pnPwFindInput = new JPanel();
		pnPwFindInput.setLayout(new GridLayout(8, 1, 5, 5));
		pnPwFindInput.setBorder(new EmptyBorder(30, 0, 0, 0));		//패널 패딩 설정
		pnPwFindInput.setBackground(new Color(1, 168, 98));		//패널 색상 배경생과 동일하게 설정
		
		
		//안내 메시지 생성
		JLabel lblInfo = new JLabel("비밀번호를 찾기 위해 아래의 정보를 입력해주세요", JLabel.CENTER);
		lblInfo.setFont(new Font("맑은고딕", Font.BOLD, 18));		//라벨 폰트 설정
		
		
		//아이디 텍스트필드 생성
		tfIdPw = new JTextField("  아이디");
		tfIdPw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfIdPw.setFont(new Font("맑은고딕", Font.PLAIN, tfIdPw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfIdPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfIdPw.addFocusListener(this);
		tfIdPw.addKeyListener(this);
		
		
		//휴대폰번호 필드
		JPanel pnPhonNumInput = new JPanel();
		pnPhonNumInput.setLayout(new BorderLayout());
		pnPhonNumInput.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//휴대폰번호 텍스트필드 생성
		tfPhonNumPw = new JTextField("  휴대폰번호('-'제외)");
		tfPhonNumPw.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfPhonNumPw.setFont(new Font("맑은고딕", Font.PLAIN, tfPhonNumPw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfPhonNumPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfPhonNumPw.addFocusListener(this);
		tfPhonNumPw.addKeyListener(this);
		
		//전송하기 버튼 생성
		btnSendPw = new JButton(new ImageIcon("images/join/Btn_Send_EnabledTrue.png"));
		btnSendPw.setRolloverIcon(new ImageIcon("images/join/Btn_Send_Rollover.png"));
		btnSendPw.setPressedIcon(new ImageIcon("images/join/Btn_Send_Pressed.png"));
		btnSendPw.setBorderPainted(false);					//버튼 테두리 설정
		btnSendPw.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnSendPw.setFocusPainted(false);					//포커스 표시 설정
		btnSendPw.setOpaque(false);							//투명하게 설정
		btnSendPw.setPreferredSize(new Dimension(90, 48));	//버튼 크기 설정
		btnSendPw.setEnabled(false);						//비활성화 상태로 생성
		btnSendPw.addActionListener(this);
		
		
		//인증번호 필드
		JPanel pnCertif = new JPanel();
		pnCertif.setLayout(new BorderLayout());
		pnCertif.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//인증번호 텍스트필드 생성
		tfCertifiNumPw = new JTextField("  인증번호");
		tfCertifiNumPw.setPreferredSize(new Dimension(324, 48));	//텍스트필드 크기 설정
		tfCertifiNumPw.setFont(new Font("맑은고딕", Font.PLAIN, tfCertifiNumPw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfCertifiNumPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfCertifiNumPw.addFocusListener(this);
		tfCertifiNumPw.addKeyListener(this);
		
		//인증시간 카운트 라벨 생성
		lblCountPw = new JLabel();
		lblCountPw.setPreferredSize(new Dimension(90, 48));	//라벨 크기 설정
		lblCountPw.setFont(new Font("맑은고딕", Font.PLAIN, lblCountPw.getFont().getSize()));	//라벨 폰트 설정
		lblCountPw.setForeground(Color.RED);	//라벨 폰트 생상 설정
		lblCountPw.setHorizontalAlignment(JLabel.CENTER);	//라벨 가운데 정렬
		lblCountPw.setOpaque(true);	//라벨 투명도 설정
		lblCountPw.setBackground(Color.WHITE);	//라벨 색상 하얀색으로 설정
		
		
		//비밀번호 찾기 버튼
		JPanel pnFindBtn = new JPanel();
		pnFindBtn.setLayout(new FlowLayout());
		pnFindBtn.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//비밀번호 찾기 버튼 생성
		btnPwFind = new JButton(new ImageIcon("images/findidpw/Btn_Find_EnabledTrue.png"));
		btnPwFind.setRolloverIcon(new ImageIcon("images/findidpw/Btn_Find_Rollover.png"));
		btnPwFind.setPressedIcon(new ImageIcon("images/findidpw/Btn_Find_Pressed.png"));
		btnPwFind.setBorderPainted(false);					//버튼 테두리 설정
		btnPwFind.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnPwFind.setFocusPainted(false);					//포커스 표시 설정
		btnPwFind.setOpaque(false);							//투명하게 설정
		btnPwFind.setPreferredSize(new Dimension(183, 50));	//버튼 크기 설정
		btnPwFind.addActionListener(this);
		
		
		//새 비밀번호 텍스트필드 생성
		tfNewPwPw = new JTextField("  새 비밀번호");
		tfNewPwPw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfNewPwPw.setFont(new Font("맑은고딕", Font.PLAIN, tfNewPwPw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfNewPwPw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfNewPwPw.addFocusListener(this);
		tfNewPwPw.addKeyListener(this);
		
		
		//새 비밀번호 확인 텍스트필드 생성
		tfNewPw2Pw = new JTextField("  새 비밀번호 확인");
		tfNewPw2Pw.setPreferredSize(new Dimension(414, 48));	//텍스트필드 크기 설정
		tfNewPw2Pw.setFont(new Font("맑은고딕", Font.PLAIN, tfNewPw2Pw.getFont().getSize()));	//텍스트필드 폰트 설정
		tfNewPw2Pw.setForeground(Color.GRAY);	//텍스트필드 폰트 생상 설정
		tfNewPw2Pw.addFocusListener(this);
		tfNewPw2Pw.addKeyListener(this);
		
		
		//비밀번호 변경 버튼
		JPanel pnChangeBtn = new JPanel();
		pnChangeBtn.setLayout(new FlowLayout());
		pnChangeBtn.setBackground(new Color(1, 168, 98));	//패널 색상 배경생과 동일하게 설정
		
		//비밀번호 변경 버튼 생성
		btnPwChange = new JButton(new ImageIcon("images/findidpw/Btn_Change_EnabledTrue.png"));
		btnPwChange.setRolloverIcon(new ImageIcon("images/findidpw/Btn_Change_Rollover.png"));
		btnPwChange.setPressedIcon(new ImageIcon("images/findidpw/Btn_Change_Pressed.png"));
		btnPwChange.setBorderPainted(false);					//버튼 테두리 설정
		btnPwChange.setContentAreaFilled(false);				//버튼 배경 표시 설정
		btnPwChange.setFocusPainted(false);						//포커스 표시 설정
		btnPwChange.setOpaque(false);							//투명하게 설정
		btnPwChange.setPreferredSize(new Dimension(183, 50));	//버튼 크기 설정
		btnPwChange.addActionListener(this);
		
		
		pnPhonNumInput.add(tfPhonNumPw, BorderLayout.CENTER);
		pnPhonNumInput.add(btnSendPw, BorderLayout.EAST);
		
		pnCertif.add(tfCertifiNumPw, BorderLayout.CENTER);
		pnCertif.add(lblCountPw, BorderLayout.EAST);
		
		pnFindBtn.add(btnPwFind);
		
		pnChangeBtn.add(btnPwChange);
		
		pnPwFindInput.add(lblInfo);
		pnPwFindInput.add(tfIdPw);
		pnPwFindInput.add(pnPhonNumInput);
		pnPwFindInput.add(pnCertif);
		pnPwFindInput.add(pnFindBtn);
		pnPwFindInput.add(tfNewPwPw);
		pnPwFindInput.add(tfNewPw2Pw);
		pnPwFindInput.add(pnChangeBtn);
		
		pnPwFindBg.add(pnPwFindInput);
		
		tabPane.addTab("비밀번호찾기", pnPwFindBg);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == btnBack) {
			lf.setVisible(true);	//로그인 화면 활성화
			dispose();				//현재 화면 종료
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
