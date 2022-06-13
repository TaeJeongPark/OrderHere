package orderhere.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import orderhere.common.Boilerplate;
import orderhere.common.DB;
import orderhere.common.UsersData;
import orderhere.login.Login;
import orderhere.order.CommonPanel;
import orderhere.order.cart.Cart;
import orderhere.order.cart.MenuInformation;
import orderhere.order.orderdetails.OrderList;
import orderhere.order.pointusage.PointUsage;
import orderhere.start.MainFrame;

/**
* @packageName	: orderhere.main
* @fileName		: Main.java
* @author		: TaeJeong Park
* @date			: 2022.06.09
* @description	: 메인 화면(0003)
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.06.09		TaeJeong Park		최초 생성
* 2022.06.09		TaeJeong Park		레이아웃 구현 완료
* 2022.06.10		TaeJeong Park		기능 구현 완료
*/
public class Main extends JPanel implements ActionListener, ItemListener {

	private MainFrame mainFrame = null;		//메인 프레임 객체
	
	private String usersName;				//접속한 사용자 이름
	private int usersCash;					//접속한 사용자 보유 캐시
	private int usersPoint;					//접속한 사용자 보유 포인트
	
	private JPanel background;				//배경 이미지 패널
	private JButton btnLogout;				//로그아웃 버튼
	private JLabel lblname;					//접속한 사용자 이름 라벨
	private JLabel lblCashData;				//접속한 사용자 보유 캐시 라벨
	private JLabel lblPointData;			//접속한 사용자 보유 포인트 라벨
	private JComboBox<String> cbStore;		//지점 선택 콤보박스
	private Vector<Integer> vecStoreId;		//지점 아이디 저장 벡터
	private Vector<String> vecStoreName;	//지점명 저장 벡터
	private JButton btnStoreInfo;			//지점정보 버튼
	private JButton btnPoinList;			//포인트내역 버튼
	private JButton btnOrderList;			//주문내역 버튼
	private JButton btnCart;				//장바구니 버튼
	private JPanel pnMenu;					//메뉴 패널
	private int menuCnt;					//메뉴 갯수 카운트 변수
	private String category;				//메뉴 카테고리 저장 변수
	private JScrollPane scpane;				//메뉴 리스트 스크롤팬

	private ImageIcon icLogoutET;			//로그아웃 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icLogoutRo;			//로그아웃 버튼(Rollover) 이미지 아이콘
	private ImageIcon icLogoutPr;			//로그아웃 버튼(Pressed) 이미지 아이콘
	private ImageIcon icStoreInfoET;		//매장정보 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icStoreInfoRo;		//매장정보 버튼(Rollover) 이미지 아이콘
	private ImageIcon icStoreInfoPr;		//매장정보 버튼(Pressed) 이미지 아이콘
	private ImageIcon icPointListET;		//포인트내역 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icPointListRo;		//포인트내역 버튼(Rollover) 이미지 아이콘
	private ImageIcon icPointListPr;		//포인트내역 버튼(Pressed) 이미지 아이콘
	private ImageIcon icOrderListET;		//주문내역 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icOrderListRo;		//주문내역 버튼(Rollover) 이미지 아이콘
	private ImageIcon icOrderListPr;		//주문내역 버튼(Pressed) 이미지 아이콘
	private ImageIcon icCartET;				//장바구니 버튼(EnabledTrue) 이미지 아이콘
	private ImageIcon icCartRo;				//장바구니 버튼(Rollover) 이미지 아이콘
	private ImageIcon icCartPr;				//장바구니 버튼(Pressed) 이미지 아이콘
	
	//메인 화면
	public Main() {
		
		initUsersData();	// 장바구니 번호 초기화
		
		setLayout(new BorderLayout());
		
        mainFrame = (MainFrame) MainFrame.getMainFrame();	//메인 프레임 객체 주소 저장
        
        mainFrame.setTitle("Main");
        
        
        //배경 이미지 생성
        ImageIcon backImage = new ImageIcon("images/background/MainBackground.png");
        
        background = new JPanel() {
            public void paintComponent(Graphics g) {
            	
                g.drawImage(backImage.getImage(), 0, 0, null);
                
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        
        
		makeImageIcon();		//이미지 아이콘 생성
		
		searchUsersData();		//사용자 정보 조회
		searchStoreData();		//지점 정보 조회
		
        makeTitle();			//타이틀 영역 생성
        makeMenuList();			//메뉴 리스트 영역 생성
        makeBtnArea();			//버튼 영역 생성
        
        add(background, BorderLayout.CENTER);
        
    }

	//이미지 아이콘 생성
	private void makeImageIcon() {
		
		icLogoutET = new ImageIcon("images/main/Btn_Logout_EnabledTrue.png");		//로그아웃 버튼(EnabledTrue) 이미지 아이콘
		icLogoutRo = new ImageIcon("images/main/Btn_Logout_Rollover.png");			//로그아웃 버튼(Rollover) 이미지 아이콘
		icLogoutPr = new ImageIcon("images/main/Btn_Logout_Pressed.png");			//로그아웃 버튼(Pressed) 이미지 아이콘
		
		icStoreInfoET = new ImageIcon("images/main/Btn_StoreInfo_EnabledTrue.png");	//매장정보 버튼(EnabledTrue) 이미지 아이콘
		icStoreInfoRo = new ImageIcon("images/main/Btn_StoreInfo_Rollover.png");	//매장정보 버튼(Rollover) 이미지 아이콘
		icStoreInfoPr = new ImageIcon("images/main/Btn_StoreInfo_Pressed.png");		//매장정보 버튼(Pressed) 이미지 아이콘
		
		icPointListET = new ImageIcon("images/main/Btn_PointList_EnabledTrue.png");	//포인트내역 버튼(EnabledTrue) 이미지 아이콘
		icPointListRo = new ImageIcon("images/main/Btn_PointList_Rollover.png");	//포인트내역 버튼(Rollover) 이미지 아이콘
		icPointListPr = new ImageIcon("images/main/Btn_PointList_Pressed.png");		//포인트내역 버튼(Pressed) 이미지 아이콘
		
		icOrderListET = new ImageIcon("images/main/Btn_OrderList_EnabledTrue.png");	//주문내역 버튼(EnabledTrue) 이미지 아이콘
		icOrderListRo = new ImageIcon("images/main/Btn_OrderList_Rollover.png");	//주문내역 버튼(Rollover) 이미지 아이콘
		icOrderListPr = new ImageIcon("images/main/Btn_OrderList_Pressed.png");		//주문내역 버튼(Pressed) 이미지 아이콘
		
		icCartET = new ImageIcon("images/main/Btn_Cart_EnabledTrue.png");			//장바구니 버튼(EnabledTrue) 이미지 아이콘
		icCartRo = new ImageIcon("images/main/Btn_Cart_Rollover.png");				//장바구니 버튼(Rollover) 이미지 아이콘
		icCartPr = new ImageIcon("images/main/Btn_Cart_Pressed.png");				//장바구니 버튼(Pressed) 이미지 아이콘
		
	}
	
	//사용자 정보 조회
	private void searchUsersData() {

		ResultSet rs = DB.getResult("SELECT USERSNAME, USERSCASH FROM USERS WHERE USERSID = '" + (String) UsersData.getUsersId() + "'");	//USERS 테이블에서 사용자 이름, 보유 캐시 조회
		
		try {
			if(rs.next()) {
				usersName = rs.getString("USERSNAME");	//사용자 이름 데이터 저장
				usersCash = rs.getInt("USERSCASH");		//사용자 보유 캐시 데이터 저장
				
				System.out.println("(Main) 사용자 정보 조회 성공");
			}
		} catch (SQLException e) {
			System.out.println("(Main) 예외발생 : 사용자 정보 조회에 실패했습니다.");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				System.out.println("(Main) ResultSet 객체 종료");
			} catch (SQLException e1) {
				System.out.println("(Main) 예외발생 : ResultSet 객체 종료에 실패했습니다.");
				e1.printStackTrace();
			}
		}
		
		rs = DB.getResult("SELECT POINT FROM usersPOINT WHERE USERSID = '" + UsersData.getUsersId() + "'");	//Point 테이블에서 사용자 보유 포인트 조회
		
		try {
			if(rs.next()) {
				usersPoint = rs.getInt("POINT");		//사용자 보유 포인트 데이터 저장
				
				System.out.println("(Main) 사용자 정보 조회 성공");
			}
		} catch (SQLException e) {
			System.out.println("(Main) 예외발생 : 사용자 정보 조회에 실패했습니다.");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				System.out.println("(Main) ResultSet 객체 종료");
			} catch (SQLException e1) {
				System.out.println("(Main) 예외발생 : ResultSet 객체 종료에 실패했습니다.");
				e1.printStackTrace();
			}
		}
		
	}
	
	//지점 정보 조회
	private void searchStoreData() {

		vecStoreId = new Vector<Integer>();
		vecStoreName = new Vector<String>();
		
		ResultSet rs = DB.getResult("SELECT STOREID, STORENAME FROM STORE");	//STORE 테이블에서 지점 아이디, 이름 조회
		
		try {
			while(rs.next()) {
				vecStoreId.add(rs.getInt("STOREID"));			//지점 아이디 데이터 저장
				vecStoreName.add(rs.getString("STORENAME"));	//지점 이름 데이터 저장
				
				System.out.println("(Main) 지점 정보 조회 성공");
			}
		} catch (SQLException e) {
			System.out.println("(Main) 예외발생 : 지점 정보 조회에 실패했습니다.");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				System.out.println("(Main) ResultSet 객체 종료");
			} catch (SQLException e1) {
				System.out.println("(Main) 예외발생 : ResultSet 객체 종료에 실패했습니다.");
				e1.printStackTrace();
			}
		}
		
		UsersData.setStoreId(vecStoreId.get(0));	//사용자 접속 정보에 지점 아이디 데이터 저장
		
	}
	
	//타이틀 영역 생성
	private void makeTitle() {
		
		//로고 생성
		ImageIcon iiconlogo = new ImageIcon("images/common/logo_titlebar.png");
		JLabel logo = new JLabel(iiconlogo);
		logo.setSize(100,100);
		logo.setLocation(904, 40);
		
		add(logo);
		
		
		//로그아웃버튼
		btnLogout = new JButton(icLogoutET);
		btnLogout.setRolloverIcon(icLogoutRo);
		btnLogout.setPressedIcon(icLogoutPr);
		btnLogout.setBounds(250, 50, 81, 27);	//버튼 위치 설정
		btnLogout.setBorderPainted(false);		//버튼 테두리 설정
		btnLogout.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btnLogout.setFocusPainted(false);		//포커스 테두리 표시 설정
		btnLogout.setOpaque(false);				//투명하게 설정
		btnLogout.addActionListener(this);
		
		add(btnLogout);
		
		
		//이름라벨
		lblname = new JLabel(usersName + " 님");
		lblname.setForeground(Color.WHITE);
		lblname.setBounds(30, 50, 200, 25);
		lblname.setFont(new Font("맑은고딕", Font.BOLD, 20));
		
		add(lblname);
		
		
		//보유 캐시 라벨
		JLabel lblCash = new JLabel("보유 캐시");
		lblCash.setForeground(Color.WHITE);
		lblCash.setBounds(30, 90, 100, 25);
		lblCash.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		add(lblCash);
		
		
		//사용자 보유 캐시 데이터 라벨
		lblCashData = new JLabel(Boilerplate.setComma(usersCash) + " 원", JLabel.RIGHT);
		lblCashData.setForeground(Color.WHITE);
		lblCashData.setBounds(131, 90, 200, 25);
		lblCashData.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		add(lblCashData);
		
		
		//포인트 라벨
		JLabel lblPoint = new JLabel("보유 포인트");
		lblPoint.setForeground(Color.WHITE);
		lblPoint.setBounds(30, 125, 100, 25);
		lblPoint.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		add(lblPoint);
		
		
		//사용자 보유 포인트 데이터 라벨
		lblPointData = new JLabel(Boilerplate.setComma(usersPoint) + " P ", JLabel.RIGHT);
		lblPointData.setForeground(Color.WHITE);
		lblPointData.setBounds(131, 125, 200, 25);
		lblPointData.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		add(lblPointData);
		
		
		//지점 선택 콤보박스
		cbStore = new JComboBox<String>(vecStoreName);
		cbStore.setBounds(400, 80, 222, 48);
		cbStore.setFont(new Font("맑은고딕", Font.BOLD, 15));
		cbStore.setSelectedIndex(UsersData.getStoreId() - 1);
		System.out.println(UsersData.getStoreId());
		cbStore.addItemListener(this);
		
		add(cbStore);
		
		
		//매장정보 버튼
		btnStoreInfo = new JButton(icStoreInfoET);
		btnStoreInfo.setRolloverIcon(icStoreInfoRo);
		btnStoreInfo.setPressedIcon(icStoreInfoPr);
		btnStoreInfo.setBounds(630, 80, 90, 48);	//버튼 위치 설정
		btnStoreInfo.setBorderPainted(false);		//버튼 테두리 설정
		btnStoreInfo.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btnStoreInfo.setFocusPainted(false);		//포커스 테두리 표시 설정
		btnStoreInfo.setOpaque(false);			//투명하게 설정
		btnStoreInfo.addActionListener(this);
		
		add(btnStoreInfo);
		
		
		//Blended 라벨
		JLabel lblBlended = new JLabel("Blended", JLabel.CENTER);
		lblBlended.setBounds(163, 200, 100, 25);
		lblBlended.setFont(new Font("Santana-Black", Font.PLAIN, 20));
		
		add(lblBlended);
		
		
		//Espresso 라벨
		JLabel lblEspresso = new JLabel("Espresso", JLabel.CENTER);
		lblEspresso.setBounds(469, 200, 100, 25);
		lblEspresso.setFont(new Font("Santana-Black", Font.PLAIN, 20));
		
		add(lblEspresso);
		
		
		//Tea 라벨
		JLabel lblTea = new JLabel("Tea", JLabel.CENTER);
		lblTea.setBounds(775, 200, 100, 25);
		lblTea.setFont(new Font("Santana-Black", Font.PLAIN, 20));
		
		add(lblTea);
		
	}

	//메뉴 리스트 영역 생성
	private void makeMenuList() {
		
		for(int i = 0; i < 3; i++) {
			pnMenu = new JPanel();
			pnMenu.setBackground(new Color(255, 236, 198));
			pnMenu.setLayout(null);
			
			if(i == 0) category = "블렌디드";
			else if(i == 1) category = "에스프레소";
			else if(i == 2) category = "티";
			
			ResultSet rs = DB.getResult("SELECT COUNT(*) FROM MENU WHERE STOREID = " + UsersData.getStoreId() + " AND MENUCATEGORY = '" + category + "'");	//MENU 테이블에서 메뉴 카테고리에 해당되는 메뉴 갯수 조회
			
			try {
				if(rs.next()) {
					menuCnt = rs.getInt("COUNT(*)");
					
					System.out.println("(Main) 메뉴 리스트 카운트 조회 성공");
				}
			} catch (SQLException e) {
				System.out.println("(Main) 예외발생 : 메뉴 리스트 카운트 조회에 실패했습니다.");
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					System.out.println("(Main) ResultSet 객체 종료");
				} catch (SQLException e1) {
					System.out.println("(Main) 예외발생 : ResultSet 객체 종료에 실패했습니다.");
					e1.printStackTrace();
				}
			}
			
			MenuListPanel[] fmlp = new MenuListPanel[menuCnt];
			
			rs = DB.getResult("SELECT MENUID, MENUNAME, MENUPRICE, MENUIMAGE FROM MENU WHERE STOREID = " + UsersData.getStoreId() + " AND MENUCATEGORY = '" + category + "'");	//MENU 테이블에서 메뉴 정보 조회
			
			int cnt = 0;
			
			try {
				while(rs.next()) {
					String memuName = rs.getString("MENUNAME");
					int memuPrice = rs.getInt("MENUPRICE");
					String memuImage = rs.getString("MENUIMAGE");
					
					fmlp[cnt] = new MenuListPanel(memuName, memuPrice, memuImage, category, this);
					fmlp[cnt].setLocation(0, 100 * cnt);
					
					pnMenu.add(fmlp[cnt]);
					cnt++;
					
					System.out.println("(Main) 메뉴 리스트 메뉴 정보 조회 성공");
				}
			} catch (SQLException e) {
				System.out.println("(Main) 예외발생 :메뉴 리스트 메뉴 정보 조회에 실패했습니다.");
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					System.out.println("(Main) ResultSet 객체 종료");
				} catch (SQLException e1) {
					System.out.println("(Main) 예외발생 : ResultSet 객체 종료에 실패했습니다.");
					e1.printStackTrace();
				}
			}
			
			pnMenu.setPreferredSize(new Dimension(286, 100 * menuCnt));
			
			scpane = new JScrollPane(pnMenu,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			scpane.setSize(286, 400);
			scpane.setLocation(70 + (306 * i), 250);
			scpane.setBackground(new Color(255, 236, 198));
			scpane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			scpane.getVerticalScrollBar().setUnitIncrement(8);
			
			add(scpane);
			
		}
		
	}

	//버튼 영역 생성
	private void makeBtnArea() {
		
		//포인트내역 버튼
		btnPoinList = new JButton(icPointListET);
		btnPoinList.setRolloverIcon(icPointListRo);
		btnPoinList.setPressedIcon(icPointListPr);
		btnPoinList.setBounds(414, 670, 183, 50);	//버튼 위치 설정
		btnPoinList.setBorderPainted(false);		//버튼 테두리 설정
		btnPoinList.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btnPoinList.setFocusPainted(false);			//포커스 테두리 표시 설정
		btnPoinList.setOpaque(false);				//투명하게 설정
		btnPoinList.addActionListener(this);
		
		add(btnPoinList);
		
		
		//주문내역 버튼
		btnOrderList = new JButton(icOrderListET);
		btnOrderList.setRolloverIcon(icOrderListRo);
		btnOrderList.setPressedIcon(icOrderListPr);
		btnOrderList.setBounds(617, 670, 183, 50);
		btnOrderList.setBorderPainted(false);		//버튼 테두리 설정
		btnOrderList.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btnOrderList.setFocusPainted(false);		//포커스 테두리 표시 설정
		btnOrderList.setOpaque(false);				//투명하게 설정
		btnOrderList.addActionListener(this);
		
		add(btnOrderList);
		
		
		//장바구니 버튼
		btnCart = new JButton(icCartET);
		btnCart.setRolloverIcon(icCartRo);
		btnCart.setPressedIcon(icCartPr);
		btnCart.setBounds(820, 670, 183, 50);	//버튼 위치 설정
		btnCart.setBorderPainted(false);		//버튼 테두리 설정
		btnCart.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btnCart.setFocusPainted(false);			//포커스 테두리 표시 설정
		btnCart.setOpaque(false);				//투명하게 설정
		btnCart.addActionListener(this);
		
		add(btnCart);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		JButton btnMenuItem = null;
		
		if(obj != btnLogout && obj != btnStoreInfo && obj != btnPoinList && obj != btnOrderList && obj != btnCart) {
			btnMenuItem = (JButton) obj;	//메뉴 아이템 버튼
		}
		
		if(obj == btnLogout) {
			//사용자 접속 정보 초기화
			UsersData.setUsersId(null);
			UsersData.setStoreId(0);
			UsersData.setiIsSameCart(0);
			
			CommonPanel.redraw(new Login());		//로그인 화면으로 전환
		} else if(obj == btnStoreInfo) {
			JOptionPane.showMessageDialog(mainFrame, "서비스 준비중입니다.\n감사합니다.", "준비중인 서비스", JOptionPane.INFORMATION_MESSAGE);
		} else if(obj == btnPoinList) {
			CommonPanel.redraw(new PointUsage());	//포인트내역 화면으로 전환
		} else if(obj == btnOrderList) {
			CommonPanel.redraw(new OrderList());	//주문내역 화면으로 전환
		} else if(obj == btnCart) {
			CommonPanel.redraw(new Cart());			//장바구니 화면으로 전환
		} else if(obj == btnMenuItem) {
			System.out.println(btnMenuItem.getName());
			CommonPanel.redraw(new MenuInformation(btnMenuItem.getName()));	//메뉴 상세정보 화면으로 전환
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == cbStore) {
			UsersData.setStoreId(vecStoreId.get(cbStore.getSelectedIndex()));	//사용자 접속 정보에 지점 아이디 데이터 저장
			makeMenuList();	//메뉴 리스트 재생성
		}
		
	}
	
	private void initUsersData() {
		/* Writed by 송윤하 */
		
		ResultSet rsIsPaid = null;
		ResultSet rsCart = null;
		
		int iIsSameCart = -1;
		int cartid = -1;
		
		rsIsPaid = DB.getResult("select max(cartid) from cart where usersid='"+UsersData.getUsersId()+"'");
		try {
			if(rsIsPaid.next()) 
			{
				cartid = rsIsPaid.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Main.initUsersData(): db에러");
			e.printStackTrace();
		}finally {
			try {
				rsIsPaid.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(cartid==0) 
		{
			UsersData.setiIsSameCart(1); // 최초 장바구니
			return;
			
		}

		rsIsPaid = DB.getResult("select cartid from orders where cartid="+cartid);
		try {
			if(rsIsPaid.next()) 
			{
				//결제된 장바구니, 장바구니 같음 여부 번호 +1
				rsCart = DB.getResult("select cartidissamecart from cart where cartid="+cartid);
				if(rsCart.next()) iIsSameCart = rsCart.getInt(1)+1;
			}else 
			{   //미결제 장바구니, 장바구니 같음 여부 번호 그대로
				rsCart = DB.getResult("select cartidissamecart from cart where cartid="+cartid);
				if(rsCart.next()) iIsSameCart = rsCart.getInt(1);
			}
			UsersData.setiIsSameCart(iIsSameCart);
		} catch (SQLException e) {
			System.out.println("CARTIDISSAMECART: db에러");
			e.printStackTrace();
		}
	}
}
