package orderhere.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame implements ActionListener {
	ImageIcon icon;
	private JLabel lblname;
	private JLabel lblcash;
	private Component lblpoint;
	private Font lblFont1;
	private Font lblFont2;
	private JPanel Menu;
	private JButton logoutbtn;
	private ImageIcon logoImage;
	private JLabel logolbl;
	private Component Logo;
	private JLabel lblcash2;
	private JLabel lblpoint2;
	private JComboBox<String> cbstore;
	private String [] storelist = {"지점선택","인하대점1","인하대점2","인하대점3","인하대점4","인하대점5","인하대점6"};
	private JButton StoreSelectbtn;
	private JLabel lblMenu;
	private Font lblFont3;
	private JLabel lblBestMenu;
	private JButton PointListbtn;
	private JButton OrderListbtn;
	private JButton Cartbtn;
	private JTabbedPane DrinkTab;
	private JLabel ColdBrewlbl;
	private JLabel Espressolbl;
	private JLabel Frappuccinolbl;
	private JLabel Blendedlbl;
	private JLabel StarbucksFizziolbl;
	private JLabel Otherslbl;
	private String [] ColdBrew = {"콛드 브루","콛드 브루2","콛드 브루3","콛드 브루4","콛드 브루5","콛드 브루6","콛드 브루4","콛드 브루4","콛드 브루4","콛드 브루4","콛드 브루4","콛드 브루4","콛드 브루4"};
	private JList<String> ColdBrewList;
	private String[] BestMenu = {"ddddddddd","ddddddddd","ddddddddd","ddddddddd","ddddddddd","ddddddddd"};
	private JList<String> BestMenuList;
	
	
		
		 public MainFrame() {
		        icon = new ImageIcon("images/mainframe/Group 422.png");
		       
		            
		        JPanel background = new JPanel() {
		            public void paintComponent(Graphics g) {
		           
		                g.drawImage(icon.getImage(), 0, 0, null);
		                
		                setOpaque(false); 
		                super.paintComponent(g);
		            }
		        };
				
		
		
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		        setTitle("메인 화면");
				setLocationRelativeTo(null);
				setSize(1050, 750);
				setLocation(300,50);
				setResizable(false);
				
				Toolkit tk  = Toolkit.getDefaultToolkit();
				Image img = tk.getImage("images/mainframe/logo_titleBar.png");
				setIconImage(img);
				
				//로그아웃버튼
				logoutbtn = new JButton(new ImageIcon("images/mainframe/Btn_Logout_EnavledTrue.png"));
				logoutbtn.setRolloverIcon(new ImageIcon("images/mainframe/Btn_Logout_Rollover.png"));
				logoutbtn.setPressedIcon(new ImageIcon("images/mainframe/Btn_Logout_Pressed.png"));
				logoutbtn.setBounds(250, 50, 81, 27);
				logoutbtn.setBorderPainted(false);
				logoutbtn.setFocusPainted(false);
				logoutbtn.addActionListener(this);
				
				//이름라벨
				lblFont1 = new Font("맑은고딕",Font.PLAIN,20);
				lblname = new JLabel("홍길동 님");
				lblname.setForeground(Color.WHITE);
				lblname.setBounds(30, 50, 230, 25);
				lblname.setFont(lblFont1);
				
				//로고
				Logo = new JLabel(new ImageIcon("images/mainframe/logo_titleBar.png"));
				Logo.setBounds(900, 30, 100, 100);
				
				//캐시 라벨
				lblFont2 = new Font("맑은고딕",Font.PLAIN,15);
				lblcash = new JLabel("보유 캐시");
				lblcash.setForeground(Color.WHITE);
				lblcash.setBounds(30, 90, 100, 25);
				lblcash.setFont(lblFont2);
				
				//원 라벨
				lblFont2 = new Font("맑은고딕",Font.PLAIN,15);
				lblcash2 = new JLabel("99,999,999,999,999,999 원");
				lblcash2.setForeground(Color.WHITE);
				lblcash2.setBounds(130, 90, 1000, 25);
				lblcash2.setFont(lblFont2);
				
				//포인트 라벨
				lblFont2 = new Font("맑은고딕",Font.PLAIN,15);
				lblpoint = new JLabel("보유 포인트");
				lblpoint.setForeground(Color.WHITE);
				lblpoint.setBounds(30, 125, 100, 25);
				lblpoint.setFont(lblFont2);
				
				//P 라벨
				lblFont2 = new Font("맑은고딕",Font.PLAIN,15);
				lblpoint2 = new JLabel("99,999,999,999,999,999 P");
				lblpoint2.setForeground(Color.WHITE);
				lblpoint2.setBounds(130, 125, 1000, 25);
				lblpoint2.setFont(lblFont2);
				
				//지점 콤보박스
				cbstore = new JComboBox<String>(storelist);
				cbstore.setBounds(400, 50, 222, 48);
				
				//매장선택 버튼
				StoreSelectbtn = new JButton(new ImageIcon("images/mainframe/Btn_StoreSelect_EnavledTrue.png"));
				StoreSelectbtn.setRolloverIcon(new ImageIcon("images/mainframe/Btn_StoreSelect_Rollover.png"));
				StoreSelectbtn.setPressedIcon(new ImageIcon("images/mainframe/Btn_StoreSelect_Pressed.png"));
				StoreSelectbtn.setBounds(630, 50, 90, 48);
				StoreSelectbtn.setBorderPainted(false);
				StoreSelectbtn.setFocusPainted(false);
				StoreSelectbtn.addActionListener(this);
				
				//Menu 라벨
				lblFont3 = new Font(null,Font.BOLD,15);
				lblMenu = new JLabel("Menu");
				lblMenu.setBounds(350, 220, 44, 18);
				lblMenu.setFont(lblFont3);
				
				//Best Menu 라벨
				lblFont3 = new Font(null,Font.BOLD,15);
				lblBestMenu = new JLabel("Best Menu");
				lblBestMenu.setBounds(800, 220, 100, 18);
				lblBestMenu.setFont(lblFont3);
				
				//음료
				
				
				//포인트내역 버튼
				PointListbtn = new JButton(new ImageIcon("images/mainframe/Btn_PointList_EnabledTrue.png"));
				PointListbtn.setRolloverIcon(new ImageIcon("images/mainframe/Btn_PointList_Rollover.png"));
				PointListbtn.setPressedIcon(new ImageIcon("images/mainframe/Btn_PointList_Pressed.png"));
				PointListbtn.setBounds(432, 640, 183, 50);
				PointListbtn.setBorderPainted(false);
				PointListbtn.setFocusPainted(false);
				PointListbtn.addActionListener(this);
				
				//주문내역 버튼
				OrderListbtn = new JButton(new ImageIcon("images/mainframe/Btn_OrderList_EnabledTrue.png"));
				OrderListbtn.setRolloverIcon(new ImageIcon("images/mainframe/Btn_OrderList_Rollover.png"));
				OrderListbtn.setPressedIcon(new ImageIcon("images/mainframe/Btn_OrderList_Pressed.png"));
				OrderListbtn.setBounds(630, 640, 183, 50);
				OrderListbtn.setBorderPainted(false);
				OrderListbtn.setFocusPainted(false);
				OrderListbtn.addActionListener(this);
				
				//장바구니 버튼
				Cartbtn = new JButton(new ImageIcon("images/mainframe/Btn_Cart_EnabledTrue.png"));
				Cartbtn.setRolloverIcon(new ImageIcon("images/mainframe/Btn_Cart_Rollover.png"));
				Cartbtn.setPressedIcon(new ImageIcon("images/mainframe/Btn_Cart_Pressed.png"));
				Cartbtn.setBounds(828, 640, 183, 50);
				Cartbtn.setBorderPainted(false);
				Cartbtn.setFocusPainted(false);
				Cartbtn.addActionListener(this);
				
				//음료 탭
				JPanel tp = new JPanel();
				JPanel tp2 = new JPanel();
				DrinkTab = new JTabbedPane();
				DrinkTab.addTab("Drink", tp);
				DrinkTab.addTab("Food", tp2);
				DrinkTab.setBounds(40, 230, 650, 400);
				
				ColdBrewlbl = new JLabel("Cold Brew");
				ColdBrewlbl.setFont(lblFont3);
				
				
				Espressolbl = new JLabel("Espresso");
				Espressolbl.setFont(lblFont3);
				
				Frappuccinolbl = new JLabel("Frappuccino");
				Frappuccinolbl.setFont(lblFont3);
				
				Blendedlbl = new JLabel("Blended");
				Blendedlbl.setFont(lblFont3);
				
				StarbucksFizziolbl = new JLabel("Starbucks Fizzio");
				StarbucksFizziolbl.setFont(lblFont3);
				
				Otherslbl = new JLabel("Others");
				Otherslbl.setFont(lblFont3);
				
				
				

				ColdBrewList = new JList<String>(ColdBrew);
				
			//	ColdBrewList.getSelectedIndex();
				ColdBrewList.setBounds(10, 10, 100, 10);
				
	
				
				tp.setLayout(new GridLayout(2,3,100,100));
				tp.setBackground(new Color(234,199,132));
				tp2.setBackground(new Color(234,199,132));
				tp.add(ColdBrewlbl);
				tp.add(Blendedlbl);
				tp.add(Espressolbl);
			//	tp.add(ColdBrewList);
				tp.add(StarbucksFizziolbl);
				tp.add(Frappuccinolbl);
				tp.add(Otherslbl);
				
				
//				BestMenuList = new JList<String>(BestMenu);
//				BestMenuList.setBounds(700, 250, 300, 380);
//				JScrollPane sp = new JScrollPane(BestMenuList,
//						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//				
				add(Logo);
				add(lblname);
				add(logoutbtn);
				add(lblcash);
				add(lblpoint);
				add(lblcash2);
				add(lblpoint2);
				add(cbstore);
				add(StoreSelectbtn);
				add(lblMenu);
				add(lblBestMenu);
				add(OrderListbtn);
				add(Cartbtn);
				add(PointListbtn);
				add(DrinkTab);
			//	add(BestMenuList);
//				BestMenuList.add(sp);
				add(background);
				
				
				
				
				
			
				
			
				

				
				
				
				
				
				
				
				setVisible(true);
		
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


