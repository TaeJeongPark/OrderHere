package orderhere.order.payment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import orderhere.order.CommonPanel;
import orderhere.order.Test;
import orderhere.order.cart.CartData;
import orderhere.order.db.DB;
import orderhere.order.orderdetails.OrderDetails;

public class Payment extends JFrame implements ActionListener, MouseListener{
	
		private int cartid = 1;
		private String usersid;
		private int storeid;
		private String storeAddress;
		private String storeName;
		private int iUsersPoint = 0;
		private int iUsersCash=0;
		private int iSumPrice;
		private int iIsReserved;
		private String sReservedTime;
		private int iToPay;
		private CartData cd;
		private JButton btnReset;
		private JButton btnApply;
		private JTextArea ta;
		private JLabel lblDToPay;
		private JTextField tfPoint;
		private JButton btnOrder;
		private int cartnum;
		private int inputPoint;
		
		public Payment(CartData cd) 
		{
			this.cd = cd;
			
			usersid = cd.getUsersid();
			storeid = cd.getStoreid();
			iSumPrice = cd.getiSumPrice();
			iIsReserved = cd.getiIsReserved();//1이면 에약, 0이면 미예약
			sReservedTime = cd.getsReservedTime();
			cartnum = cd.getCartnum();
			
			getDataFromDB();
			
			iToPay = iSumPrice;

			setSize(1050,789);
			setLocation(100,0);//화면 가운데 배치 필요
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(null);
			
			add(new CommonPanel().createTop("Payment"));
			createBody();
			
			setVisible(true);
		}


		private void createBody() {
			JPanel p_body = new JPanel();
			p_body.setSize(1050, 750-158);
			p_body.setLocation(0, 158);
			p_body.setBackground(new Color(234,199,132));
			p_body.setLayout(null);
			add(p_body);
			
			JLabel lblStoreName = new JLabel("매장 이름 : 스타벅스 "+storeName);
			lblStoreName.setSize(396,26);
			lblStoreName.setLocation(120, 0);
			lblStoreName.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblStoreName);
			
			JLabel lblStoreAddress = new JLabel("매장 주소 :"+storeAddress);
			lblStoreAddress.setSize(766, 26);
			lblStoreAddress.setLocation(120, 20+5);
			lblStoreAddress.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblStoreAddress);
			
			JPanel pSelectedMenu = new SelectedMenuPanel(cartnum,cd);
			
			if(cartnum>=3) pSelectedMenu.setPreferredSize(new Dimension(800, 53*(cartnum+1)));
			else if(cartnum>=0&&cartnum<3) pSelectedMenu.setPreferredSize(new Dimension(800, 53*(3+1)));
			
			pSelectedMenu.setBackground(Color.WHITE);
			
			ta = new JTextArea();
			
			JScrollPane jsp = new JScrollPane(pSelectedMenu, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.getVerticalScrollBar().setUnitIncrement(8);
			
			jsp.setSize(797,179);
			jsp.setLocation(120,26+26+5);
			p_body.add(jsp);
			
			JLabel lblPayInfo = new JLabel("결제 정보");
			lblPayInfo.setSize(110, 30);
			lblPayInfo.setLocation(470, 250);
			lblPayInfo.setFont(new Font("맑은 고딕",Font.BOLD,22));
			p_body.add(lblPayInfo);
			
			JLabel lblSum = new JLabel("총 금액");//80,140,90 // 포인트, 보유 캐쉬
			lblSum.setSize(80,20);
			lblSum.setLocation(125, 290);
			lblSum.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblSum);
			
			JLabel lblPoint = new JLabel("포인트 (보유 : " + CommonPanel.toAddCommaAtPrice(iUsersPoint)+"P)");
			lblPoint.setSize(250,20);
			lblPoint.setLocation(123, 290+40);
			lblPoint.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblPoint);
			
			JLabel lblMinPoint = new JLabel("최소 1,000P 부터 사용 가능합니다");
			lblMinPoint.setSize(200, 15);
			lblMinPoint.setLocation(123, 290+40+20);
			lblMinPoint.setFont(new Font("맑은 고딕",Font.BOLD,11));
			lblMinPoint.setForeground(new Color(108,108,108));
			p_body.add(lblMinPoint);
			
			JLabel lblUsersCash = new JLabel("보유 캐쉬");
			lblUsersCash.setSize(90, 20);
			lblUsersCash.setLocation(123, 290+40+40);
			lblUsersCash.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblUsersCash);
			
			JLabel lblToPay = new JLabel("결제 금액");
			lblToPay.setSize(110,20);
			lblToPay.setLocation(120, 290+40+40+40);
			lblToPay.setFont(new Font("맑은 고딕",Font.BOLD,22));
			p_body.add(lblToPay);
			
			JLabel lblDSum = new JLabel(CommonPanel.toAddCommaAtPrice(iSumPrice)+" 원");
			lblDSum.setSize(108,21);
			lblDSum.setLocation(1080-260, 290);
			lblDSum.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblDSum);
			
			ImageIcon iconResetEnabledTrue = new ImageIcon("images/pay/Btn_Reset_EnabledTrue.png");
			
			btnReset = new JButton(iconResetEnabledTrue);
			btnReset.setSize(30, 30);
			btnReset.setLocation(1080-300, 290+40);
			btnReset.addActionListener(this);
			btnReset.addMouseListener(this);
			p_body.add(btnReset);
			
			tfPoint = new JTextField("");
			tfPoint.setSize(90, 30);
			tfPoint.setLocation(1080-270, 290+40);
			p_body.add(tfPoint);
			
			ImageIcon iconApplyEnabledTrue = new ImageIcon("images/pay/Btn_Apply_EnabledTrue.png");
			
			btnApply = new JButton(iconApplyEnabledTrue);
			btnApply.setSize(30, 30);
			btnApply.setLocation(1080-180, 290+40);
			btnApply.addActionListener(this);
			btnApply.addMouseListener(this);
			p_body.add(btnApply);
			
			JLabel lblDUsersCash = new JLabel(CommonPanel.toAddCommaAtPrice(iUsersCash)+" 원");
			lblDUsersCash.setSize(108, 21);
			lblDUsersCash.setLocation(1080-260, 290+40+40);
			lblDUsersCash.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblDUsersCash);
			
			lblDToPay = new JLabel(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
			lblDToPay.setSize(144, 21);
			lblDToPay.setLocation(1080-275, 290+40+40+40);
			lblDToPay.setFont(new Font("맑은 고딕",Font.BOLD,22));
			p_body.add(lblDToPay);
			
			String text="";
			if(iIsReserved==1) text = "예약시간은 " + sReservedTime+" 입니다.";
			
			JLabel lblReservedTime = new JLabel(text);
			lblReservedTime.setSize(395,30);
			lblReservedTime.setLocation(360, 460);
			lblReservedTime.setFont(new Font("맑은 고딕",Font.BOLD,22));
			p_body.add(lblReservedTime);
			
			ImageIcon iconOrder = new ImageIcon("images/pay/Btn_Order_EnabledTrue.png");
			
			btnOrder = new JButton(iconOrder);
			btnOrder.setSize(183,50);
			btnOrder.setLocation(420, 460+20+30);
			btnOrder.addMouseListener(this);
			btnOrder.addActionListener(this);
			p_body.add(btnOrder);
		}
			
		private void getDataFromDB() {
			DB.init();
			ResultSet rs;
			try {
				rs = DB.getResult("select storeAddress,storename from store where storeid="+storeid);
				rs.next();
				storeAddress = rs.getString(1);
				storeName = rs.getString(2);
				rs.close();
			} catch (SQLException e) {
				System.out.println("매장 데이터: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
			
			try {
				rs = DB.getResult("select userscash from users where usersid='"+usersid+"'");
				rs.next();
				iUsersCash = rs.getInt(1);
				rs.close();
			} catch (SQLException e) {
				System.out.println("유저 캐시: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
			
			try {
				rs = DB.getResult("select point from userspoint where usersid='"+usersid+"' order by pointid desc");
				if(rs.next()) iUsersPoint = rs.getInt(1);
				else iUsersPoint = 0;
				rs.close();
			} catch (SQLException e) {
				System.out.println("포인트: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnApply) 
			{
				inputPoint = 0;
				try{
					inputPoint = Integer.parseInt(tfPoint.getText());
				}catch(NumberFormatException e1) 
				{
					JOptionPane.showMessageDialog(null, "숫자만 입력해주세요", "오류", JOptionPane.ERROR_MESSAGE);
					tfPoint.setText("");
					iToPay =  iSumPrice;
					lblDToPay.setText(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
					inputPoint = 0;
					return;
				}catch(Exception e3) 
				{
					JOptionPane.showMessageDialog(null, "숫자만 입력해주세요", "오류", JOptionPane.ERROR_MESSAGE);
					inputPoint = 0;
					return;
				}
				if(inputPoint>iUsersPoint) 
				{
					JOptionPane.showMessageDialog(null, "보유 포인트를 초과하였습니다", "포인트 초과", JOptionPane.ERROR_MESSAGE);
					tfPoint.setText("");
					iToPay =  iSumPrice;
					lblDToPay.setText(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
					inputPoint = 0;
					return;
				}
				if(inputPoint<1000) 
				{
					JOptionPane.showMessageDialog(null, "최소 1,000P부터 사용가능합니다", "포인트가 적습니다", JOptionPane.ERROR_MESSAGE);
					tfPoint.setText("");
					iToPay =  iSumPrice;
					lblDToPay.setText(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
					inputPoint = 0;
					return;
				}
				iToPay =  iSumPrice - inputPoint;
				lblDToPay.setText(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
			}else if(e.getSource()==btnReset) 
			{
				tfPoint.setText("");
				iToPay = iSumPrice;
				lblDToPay.setText(CommonPanel.toAddCommaAtPrice(iToPay)+" 원");
			}else if(e.getSource()==btnOrder) 
			{
				if(iUsersCash<iToPay) 
				{
					JOptionPane.showMessageDialog(null, "캐시가 부족합니다", "캐시가 부족합니다", JOptionPane.ERROR_MESSAGE);
				}else if(iUsersCash>=iToPay) 
				{
					pay();
					Test.setActivatedFrame(new OrderDetails());
					this.dispose();
				}
			}
				
		}


		private void pay() {
			
			ResultSet rs = null;
			
			DB.executeSQL("update users set usersCash="+(iUsersCash-iToPay)+" where usersid='"+usersid+"'");
			
			int pointid = 0;
			try {
				rs = DB.getResult("select max(pointid) from userspoint");
				if(rs.next()) pointid = rs.getInt(1)+1;
				else pointid = 1;
				rs.close();
			} catch (SQLException e) {
				System.out.println("포인트 최대 값: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
			
			String orderdate = "";
			try {
				rs = DB.getResult("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from Dual");
				rs.next();
				orderdate = rs.getString(1);
				rs.close();
			} catch (SQLException e) {
				System.out.println("날짜: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
			
			int iGotPoint = (int)(Math.round(iToPay*0.01));
			int iChangedUsersPoint = iUsersPoint+iGotPoint;
			DB.executeSQL("insert into userspoint(usersid,pointid,orderdate,pointstatus,pointvalue,point) values ('"+
						usersid+"',"+pointid+",'"+orderdate+"','적립',"+iGotPoint+","+iChangedUsersPoint+")");
			
			if(inputPoint>0) 
			{
				iChangedUsersPoint = (iUsersPoint+iGotPoint)-inputPoint;
				DB.executeSQL("insert into userspoint(usersid,pointid,orderdate,pointstatus,pointvalue,point) values ("+
						usersid+","+(pointid+1)+","+orderdate+",'차감',"+inputPoint+","+iChangedUsersPoint+")");
			}
			
			int orderid = 0;
			try {
				rs = DB.getResult("select orderid from orders order by orderid desc");
				if(rs.next()) orderid = rs.getInt(1)+1;
				else orderid = 1;
				rs.close();
			} catch (SQLException e) {
				System.out.println("주문번호: 조회된 데이터가 없습니다.");
				e.printStackTrace();
			}
			DB.executeSQL("insert into orders(orderid,usersid,storeid,cartid,orderdate,orderusagepoint,orderusagecash,ordersum) values ("+
						orderid+",'"+usersid+"',"+storeid+","+cartid+",'"+orderdate+"',"+inputPoint+","+iToPay+","+iSumPrice+")");
			
			try {
				DB.conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==btnReset) btnReset.setIcon(new ImageIcon("images/pay/Btn_Reset_Pressed.png"));
			else if(e.getSource()==btnApply) btnApply.setIcon(new ImageIcon("images/pay/Btn_Apply_Pressed.png"));
			else if(e.getSource()==btnOrder) btnOrder.setIcon(new ImageIcon("images/pay/Btn_Order_Pressed.png"));
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==btnReset) btnReset.setIcon(new ImageIcon("images/pay/Btn_Reset_Rollover.png"));
			else if(e.getSource()==btnApply) btnApply.setIcon(new ImageIcon("images/pay/Btn_Apply_Rollover.png"));
			else if(e.getSource()==btnOrder) btnOrder.setIcon(new ImageIcon("images/pay/Btn_Order_Rollover.png"));
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==btnReset) btnReset.setIcon(new ImageIcon("images/pay/Btn_Reset_Rollover.png"));
			else if(e.getSource()==btnApply) btnApply.setIcon(new ImageIcon("images/pay/Btn_Apply_Rollover.png"));
			else if(e.getSource()==btnOrder) btnOrder.setIcon(new ImageIcon("images/pay/Btn_Order_Rollover.png"));
		}


		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==btnReset) btnReset.setIcon(new ImageIcon("images/pay/Btn_Reset_EnabledTrue.png"));
			else if(e.getSource()==btnApply) btnApply.setIcon(new ImageIcon("images/pay/Btn_Apply_EnabledTrue.png"));
			else if(e.getSource()==btnOrder) btnOrder.setIcon(new ImageIcon("images/pay/Btn_Order_EnabledTrue.png"));
		}
}
