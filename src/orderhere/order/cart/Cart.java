package orderhere.order.cart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import com.raven.event.EventTimePicker;
import com.raven.swing.TimePicker;

import orderhere.order.CommonPanel;
import orderhere.order.Test;
import orderhere.order.db.DB;
import orderhere.order.payment.Payment;

public class Cart extends JFrame implements ActionListener, EventTimePicker, ItemListener, MouseListener{
	
		private String usersid = "aa1234";
		private int storeid = 1;
		
		private TimePicker tp;
		private JPanel p_body;
		private JLabel lblReservedTime;
		private CartMenuPanel[] cmps;
		private CartDBData[] cdbdata;
		private CartDB db;
		private int iTimeSelectedNum;
		private JButton btn_del;
		private JButton btn_delall;
		private JButton btn_more;
		private JButton btn_reserve;
		private JButton btn_pay;
		private ImageIcon iconmore;
		private ImageIcon iconpay;
		private ImageIcon iconreserveEnabledTrue;
		private ImageIcon iconreserveRollover;
		private ImageIcon iconreservePressed;
		private ImageIcon iconreservecancelEnabledTrue;
		private ImageIcon iconreservecancelRollover;
		private ImageIcon iconreservecancelPressed;
		private JPanel cartpanel;
		private JLabel lblSumPrice;
		private JLabel lblCartNum;
		private int sumPrice;
		private int sumQuantity;
		private int cartnum;
		private JScrollPane jsp;
		private JCheckBox[] ckb;
		private String[] mouseEventText = {"Delete","DeleteAll","More","Paymeny","Reserve","ReservedCancel"};
		private ImageIcon iconreserveEnabledFalse;
		private ImageIcon iconpayEnabledFalse;
		private ImageIcon icondelEnabledFalse;
		private ImageIcon icondelallEnabledFalse;

		private String menuname;
		private String option;
		private String menucartquantity;
		private String menuprice;
		private CartData cd;
		private int iIsReserved=0;
		
		public Cart() 
		{
			getDataFromDB();
			
			setSize(1050,789);
			setLocation(100,0);//화면 가운데 배치 필요
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(null);
			
			add(new CommonPanel().createTop("Cart"));
			createBody();
			
			setVisible(true);
		}

		private void createBody() {
			
			p_body = new JPanel();
			p_body.setSize(1050, 750-158);
			p_body.setLocation(0, 158);
			p_body.setBackground(new Color(234,199,132));
			p_body.setLayout(null);
			
			btn_del = new JButton(new ImageIcon("images/cart/Btn_Delete_EnabledTrue.png"));
			btn_del.setSize(81,27);
			btn_del.setLocation(495, 5);
			btn_del.setBackground(new Color(148,178,255));
			btn_del.setForeground(Color.WHITE);
			btn_del.setBorder(BorderFactory.createEmptyBorder());
			btn_del.addActionListener(this);
			btn_del.addMouseListener(this);
			p_body.add(btn_del);
			
			btn_delall = new JButton(new ImageIcon("images/cart/Btn_DeleteAll_EnabledTrue.png"));
			btn_delall.setSize(81,27);
			btn_delall.setLocation(495+81+11, 5);
			btn_delall.setBackground(new Color(148,178,255));
			btn_delall.setForeground(Color.WHITE);
			btn_delall.setBorder(BorderFactory.createEmptyBorder());
			btn_delall.addActionListener(this);
			btn_delall.addMouseListener(this);
			p_body.add(btn_delall);
			
			//장바구니 메뉴 패널 추가
			addCenter();
			
			//예약 시간 설정 구현 코드
			tp = new TimePicker();
			tp.addEventTimePicker(this);
			
			lblReservedTime = new JLabel("예약 시간을 선택해주세요.");
			lblReservedTime.setSize(258,26);
			lblReservedTime.setLocation(41+608+19+100,118);
			lblReservedTime.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
			p_body.add(lblReservedTime);

			//하단 버튼 구현
			icondelEnabledFalse = new ImageIcon("images/cart/Btn_Delete_EnabledFalse.png");
			icondelallEnabledFalse = new ImageIcon("images/cart/Btn_DeleteAll_EnabledFalse.png");
			
			iconmore = new ImageIcon("images/cart/Btn_More_EnabledTrue.png");
			iconpay = new ImageIcon("images/cart/Btn_Payment_EnabledTrue.png");
			iconpayEnabledFalse = new ImageIcon("images/cart/Btn_Payment_EnabledFalse.png");
			iconreserveEnabledFalse = new ImageIcon("images/cart/Btn_Reserve_EnabledFalse.png");
			iconreserveEnabledTrue = new ImageIcon("images/cart/Btn_Reserve_EnabledTrue.png");
			iconreserveRollover = new ImageIcon("images/cart/Btn_Reserve_Rollover.png");
			iconreservePressed = new ImageIcon("images/cart/Btn_Reserve_Pressed.png");
			iconreservecancelEnabledTrue = new ImageIcon("images/cart/Btn_ReservedCancel_EnabledTrue.png");
			iconreservecancelRollover = new ImageIcon("images/cart/Btn_ReservedCancel_Rollover.png");
			iconreservecancelPressed = new ImageIcon("images/cart/Btn_ReservedCancel_Pressed.png");
			
			btn_more = new JButton(iconmore);
			btn_more.setSize(183,50);
			btn_more.setLocation(236,509);
			btn_more.setBackground(new Color(61,74,227));
			btn_more.setForeground(Color.WHITE);
			btn_more.setBorder(BorderFactory.createEmptyBorder());
			btn_more.addMouseListener(this);
			p_body.add(btn_more);
			
			btn_reserve = new JButton(iconreserveEnabledTrue);
			btn_reserve.setSize(183,50);
			btn_reserve.setLocation(236+183+15,509);
			btn_reserve.setBackground(new Color(61,74,227));
			btn_reserve.setForeground(Color.WHITE);
			btn_reserve.setBorder(BorderFactory.createEmptyBorder());
			btn_reserve.addActionListener(this);
			btn_reserve.addMouseListener(this);
			
			p_body.add(btn_reserve);
			
			btn_pay = new JButton(iconpay);
			btn_pay.setSize(183,50);
			btn_pay.setLocation(236+183+15+183+15,509);
			btn_pay.setBackground(new Color(61,74,227));
			btn_pay.setForeground(Color.WHITE);
			btn_pay.setBorder(BorderFactory.createEmptyBorder());
			btn_pay.addMouseListener(this);
			btn_pay.addActionListener(this);
			
			//장바구니에 담은 갯수가 0 일 때 버튼 ui처리
			if(cartnum==0) 
			{
				btn_del.setIcon(icondelEnabledFalse);
				btn_del.removeActionListener(this);
				btn_del.removeMouseListener(this);
				
				btn_delall.setIcon(icondelallEnabledFalse);
				btn_delall.removeActionListener(this);
				btn_delall.removeMouseListener(this);
				
				btn_reserve.setIcon(iconreserveEnabledFalse);
				btn_reserve.removeActionListener(this);
				
				btn_pay.setIcon(iconpayEnabledFalse);
				btn_pay.removeActionListener(this);
				btn_pay.removeMouseListener(this);
			}
			
			p_body.add(btn_pay);
			
			add(p_body);
		}

		private void addCenter() {
			
			addCenterLeft();
			addCenterRight();
			
		}
		private void addCenterLeft() {
			
			cartpanel = new JPanel();
			cartpanel.setLocation(41, 44);
			cartpanel.setBackground(new Color(234,199,132));
			cartpanel.setLayout(null);
			
			//장바구니 담은 갯수별 ui레이아웃 조정
			if(cartnum>=3) cartpanel.setPreferredSize(new Dimension(800, 140*cartnum));
			else if(cartnum>=0&&cartnum<3) cartpanel.setPreferredSize(new Dimension(800, 140*3));
			
			jsp = new JScrollPane(cartpanel, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setSize(608+15,140*3);
			jsp.setLocation(41, 44);
			jsp.setBackground(new Color(234,199,132));
			jsp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jsp.getVerticalScrollBar().setUnitIncrement(8);
			
			if(cartnum==0) 
			{
				JLabel noCartText = new JLabel("메뉴를 선택해주세요.");
				noCartText.setSize(195,30);
				noCartText.setLocation(20, 20);
				noCartText.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				cartpanel.add(noCartText);
				p_body.add(jsp);
				return;
			}
			
			//장바구니 실데이터를 담을 패널 배열을 구현한 클래스
			cmps = new CartMenuPanel[cartnum];
			ckb = new JCheckBox[cartnum];
			
			cd = new CartData(cartnum);
			//CartMenuPanel 패널 배열에 db데이터 추가 및 
			//CartMenuPanel 패널 위치 조정, CartMenuPanel 패널을 cartpanel에 추가하는 코드
			for (int i = 0; i < cartnum; i++) {
				cmps[i] = new CartMenuPanel();
				ckb[i] = new JCheckBox();
				ImageIcon mi = new ImageIcon("images/menu/cart/"+
											cdbdata[i].getMenucategory()+
											"/"+cdbdata[i].getMenuimage());
				menuname = cdbdata[i].getMenuname();
				option = cdbdata[i].getOptionStatus()+" / "+
						cdbdata[i].getOptionPacking()+" / "+
						cdbdata[i].getOptionSize()+" / "+
						cdbdata[i].getOptionSyrup()+" / x"+
						cdbdata[i].getOpionSyrupNum()+" / "+
						cdbdata[i].getOptionWhippedcream();
				menucartquantity = "수량    "+"x "+cdbdata[i].getOptionQuantity();
				menuprice = "가격    "+CommonPanel.toAddCommaAtPrice(cdbdata[i].getMenuprice()*cdbdata[i].getOptionQuantity())+" 원";
				
				//결제 화면에 보내질 데이터
				cd.setData(i,menuname,option,"x "+cdbdata[i].getOptionQuantity(),
						CommonPanel.toAddCommaAtPrice(cdbdata[i].getMenuprice()*cdbdata[i].getOptionQuantity())+" 원");
				
				
				cmps[i].setImenuid(cdbdata[i].getMenuid());
				cmps[i].getLblMenuImage().setIcon(mi);
				cmps[i].getLblMenuName().setText(menuname);
				cmps[i].getLblMenuOption().setText(option);
				cmps[i].getLblCartQuantity().setText(menucartquantity);
				cmps[i].getLblMenuPrice().setText(menuprice);
				cmps[i].setiMenuPrice(cdbdata[i].getMenuprice());
				
				cmps[i].setLocation(0, 140*i);
				
				ckb[i].setIcon(new ImageIcon("images/cart/CheckBox.png"));
				ckb[i].setBackground(new Color(255,236,198));
				ckb[i].setSize(25,25);
				ckb[i].setLocation(608-25-7, 7);
				ckb[i].addItemListener(this);
				cmps[i].add(ckb[i]);
				
				
				
				cartpanel.add(cmps[i]);
			}
			p_body.add(jsp);
		}
		
		private void addCenterRight() {
			
			sumPrice=0;
			//총 금액, 총 수량 구하는 코드
			for (int i = 0; i < cartnum; i++) {
				sumPrice += cdbdata[i].getMenuprice()*cdbdata[i].getOptionQuantity();
			}
			sumQuantity = db.getCartnum();
			
			if(cartnum==0) 
			{
				lblSumPrice = new JLabel("총 금액                    "+sumPrice+" 원");
				lblCartNum = new JLabel("총 수량                    "+sumQuantity+" 개");
			}else if(cartnum>0) 
			{
				lblSumPrice = new JLabel("총 금액              "+CommonPanel.toAddCommaAtPrice(sumPrice)+" 원");
				lblCartNum = new JLabel("총 수량                    "+sumQuantity+" 개");
			}
			lblSumPrice.setSize(294,35);
			lblSumPrice.setLocation(41+608+19+50,118+93);
			lblSumPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
			p_body.add(lblSumPrice);
			
			lblCartNum.setSize(294,35);
			lblCartNum.setLocation(41+608+19+50,118+93+35);
			lblCartNum.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
			p_body.add(lblCartNum);
		}

		private void modifyCenterLeft() 
		{
			p_body.remove(jsp);
			addCenterLeft();
		}
		
		private void modifyCenterRight() 
		{
			sumPrice=0;
			//총 금액, 총 수량 구하는 코드
			for (int i = 0; i < cartnum; i++) {
				sumPrice += cdbdata[i].getMenuprice()*cdbdata[i].getOptionQuantity();
			}
			sumQuantity = db.getCartnum();
			
			if(cartnum==0) 
			{
				lblSumPrice.setText("총 금액                    "+sumPrice+" 원");
				lblCartNum.setText("총 수량                    "+sumQuantity+" 개");
			}else if(cartnum>0) 
			{
				lblSumPrice.setText("총 금액              "+CommonPanel.toAddCommaAtPrice(sumPrice)+" 원");
				lblCartNum.setText("총 수량                    "+sumQuantity+" 개");
			}
		}

		private void getDataFromDB() {
			// db의 장바구니 관련 테이블과 연결하는 클래스
			db = new CartDB();
			// 장바구니 관련 db에서 데이터를 가져와 프로그램에 저장하는 클래스
			cdbdata = db.getCdbdata();
			cartnum = cdbdata.length;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btn_reserve) {
				if(cartnum !=0) {
					if(btn_reserve.getIcon()==iconreservePressed) {
						iIsReserved = 1;
						tp.showPopup(this, 1050-220, 0);	
						lblReservedTime.setText("예약 시간: "+tp.getSelectedTime());
						lblReservedTime.setForeground(Color.RED);
						lblReservedTime.setLocation(41+608+19+108,118);
						btn_reserve.setIcon(iconreservecancelPressed);
					}else if(btn_reserve.getIcon()==iconreservecancelPressed) {
						iIsReserved = 0;
						lblReservedTime.setText("예약 시간을 선택해주세요.");
						lblReservedTime.setForeground(Color.BLACK);
						lblReservedTime.setSize(258,26);
						lblReservedTime.setLocation(41+608+19+100,118);
						btn_reserve.setIcon(iconreservePressed);
					}
				}else if(cartnum==0) 
				{
					btn_reserve.setIcon(iconreservecancelEnabledTrue);
					JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요","예약하기",JOptionPane.ERROR_MESSAGE);
					
				}
			}else if (e.getSource()==btn_delall) 
			{
				int c = JOptionPane.showConfirmDialog(null, "전부 삭제하시겠습니까?", "전체삭제", JOptionPane.YES_NO_OPTION);
				if(c==JOptionPane.YES_OPTION) 
				{
					db.deleteCartAll(usersid);
					getDataFromDB();
					reLoad();
				}else if(c==JOptionPane.NO_OPTION) System.out.println("취소");
				
			}else if (e.getSource()==btn_del) 
			{
				for (int i = 0; i < cartnum; i++) {
					if(ckb[i].isSelected()==true) db.deleteCart(usersid, cmps[i].getImenuid());
				}
				getDataFromDB();
				reLoad();
			}else if(e.getSource()==btn_pay) 
			{
				if(iIsReserved==1) cd.setsReservedTime(tp.getSelectedTime());
				else if(iIsReserved==0) cd.setsReservedTime("미예약");
				
				cd.setUsersid(usersid);
				cd.setStoreid(storeid);
				cd.setiSumPrice(sumPrice);
				cd.setiIsReserved(iIsReserved);
				
				Test.setActivatedFrame(new Payment(cd));
			
				this.dispose();
			}
		}

		private void reLoad() {
			if(cartnum==0) 
			{
				btn_del.setIcon(icondelEnabledFalse);
				btn_del.removeActionListener(this);
				btn_del.removeMouseListener(this);
				
				btn_delall.setIcon(icondelallEnabledFalse);
				btn_delall.removeActionListener(this);
				btn_delall.removeMouseListener(this);
				
				btn_reserve.setIcon(iconreserveEnabledFalse);
				btn_reserve.removeActionListener(this);
				
				btn_pay.setIcon(iconpayEnabledFalse);
				btn_pay.removeActionListener(this);
				btn_pay.removeMouseListener(this);
			}
			modifyCenterLeft();
			modifyCenterRight();
			p_body.setVisible(false);
			p_body.setVisible(true);
		}

		@Override
		public void timeSelected(String arg0) {
			// 최초 timeselected 메서드 자동 호출로 인해 텍스트 변경되는 현상 방지
			iTimeSelectedNum++;
			if(iTimeSelectedNum==1) return;
			
			lblReservedTime.setText("예약 시간: "+tp.getSelectedTime());
			lblReservedTime.setForeground(Color.RED);
			//lblReservedTime.setSize(158,26);
			lblReservedTime.setLocation(41+608+19+108,118);
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < cartnum; i++) {
				if(e.getSource()==ckb[i]) {
					if(e.getStateChange()==ItemEvent.SELECTED)
					ckb[i].setIcon(new ImageIcon(("images/cart/CheckBox_Check.png")));
					else if(e.getStateChange()==ItemEvent.DESELECTED)
					ckb[i].setIcon(new ImageIcon(("images/cart/CheckBox.png")));
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==btn_del) btn_del.setIcon(new ImageIcon("images/cart/Btn_Delete_Pressed.png"));
			else if(e.getSource()==btn_delall) btn_delall.setIcon(new ImageIcon("images/cart/Btn_DeleteAll_Pressed.png"));
			else if(e.getSource()==btn_more) btn_more.setIcon(new ImageIcon("images/cart/Btn_More_Pressed.png"));
			else if(e.getSource()==btn_reserve) 
			{
				if(btn_reserve.getIcon()==iconreservecancelRollover) {
					btn_reserve.setIcon(iconreservecancelPressed);
				}else if(btn_reserve.getIcon()==iconreserveRollover) {
					btn_reserve.setIcon(iconreservePressed);
				}
			}
			else if(e.getSource()==btn_pay) btn_pay.setIcon(new ImageIcon("images/cart/Btn_Payment_Pressed.png"));
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==btn_del) 
			{	
				if(cartnum!=0) btn_del.setIcon(new ImageIcon("images/cart/Btn_Delete_Rollover.png"));
				else if(cartnum==0) btn_del.setIcon(new ImageIcon("images/cart/Btn_Delete_EnabledFalse.png"));
			}
			else if(e.getSource()==btn_delall) 
			{
				if(cartnum!=0) btn_delall.setIcon(new ImageIcon("images/cart/Btn_DeleteAll_Rollover.png"));
				else if(cartnum==0) btn_delall.setIcon(new ImageIcon("images/cart/Btn_DeleteAll_EnabledFalse.png"));
			}
			else if(e.getSource()==btn_more) btn_more.setIcon(new ImageIcon("images/cart/Btn_More_Rollover.png"));
			else if(e.getSource()==btn_reserve) 
			{
				if(btn_reserve.getIcon()==iconreservecancelPressed) {
					btn_reserve.setIcon(iconreservecancelRollover);
				}else if(btn_reserve.getIcon()==iconreservePressed) {
					btn_reserve.setIcon(iconreserveRollover);
				}
			}
			else if(e.getSource()==btn_pay) btn_pay.setIcon(new ImageIcon("images/cart/Btn_Payment_Rollover.png"));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==btn_del) btn_del.setIcon(new ImageIcon("images/cart/Btn_Delete_Rollover.png"));
			else if(e.getSource()==btn_delall) btn_delall.setIcon(new ImageIcon("images/cart/Btn_DeleteAll_Rollover.png"));
			else if(e.getSource()==btn_more) btn_more.setIcon(new ImageIcon("images/cart/Btn_More_Rollover.png"));
			else if(e.getSource()==btn_reserve) 
			{
				if(btn_reserve.getIcon()==iconreservecancelEnabledTrue) {
					btn_reserve.setIcon(iconreservecancelRollover);
				}else if(btn_reserve.getIcon()==iconreserveEnabledTrue) {
					btn_reserve.setIcon(iconreserveRollover);
				}
			}
			else if(e.getSource()==btn_pay) btn_pay.setIcon(new ImageIcon("images/cart/Btn_Payment_Rollover.png"));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==btn_del) btn_del.setIcon(new ImageIcon("images/cart/Btn_Delete_EnabledTrue.png"));
			else if(e.getSource()==btn_delall) btn_delall.setIcon(new ImageIcon("images/cart/Btn_DeleteAll_EnabledTrue.png"));
			else if(e.getSource()==btn_more) btn_more.setIcon(new ImageIcon("images/cart/Btn_More_EnabledTrue.png"));
			else if(e.getSource()==btn_reserve) 
			{
				if(btn_reserve.getIcon()==iconreservecancelRollover) {
					btn_reserve.setIcon(iconreservecancelEnabledTrue);
				}else if(btn_reserve.getIcon()==iconreserveRollover) {
					btn_reserve.setIcon(iconreserveEnabledTrue);
				}
			}
			else if(e.getSource()==btn_pay) btn_pay.setIcon(new ImageIcon("images/cart/Btn_Payment_EnabledTrue.png"));
			 
		}
}
