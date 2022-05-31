package orderhere.order.orderdetails;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import orderhere.order.CommonPanel;
import orderhere.order.db.DB;

public class OrderDetails extends JPanel{

		public OrderDetails(int iIsSameCart) 
		{
			getDataFromDB();
			
			setSize(1050,750);
			setLocation(0,0);//화면 가운데 배치 필요
			setLayout(null);
			
			add(new CommonPanel().createTop("ORDER DETAILS"));
			createBody();
			
			setVisible(true);
		}

		private void getDataFromDB() {
			DB.init();
			
		}

		private void createBody() {
			JPanel p_body = new JPanel();
			p_body.setSize(1050, 750-158);
			p_body.setLocation(0, 158);
			p_body.setBackground(new Color(234,199,132));
			p_body.setLayout(null);
			
			JPanel pOrderDetails = new JPanel();
			pOrderDetails.setSize(651, 539);
			pOrderDetails.setLayout(null);
			pOrderDetails.setBackground(Color.WHITE);
			JScrollPane jsp = new JScrollPane(pOrderDetails, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			JLabel lblOrderid = new JLabel("주문번호 : ");
			lblOrderid.setSize(264,28);
			lblOrderid.setLocation(42, 34);
			lblOrderid.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderid);
			
			JLabel lblOrderStore = new JLabel("주문매장 : ");
			lblOrderStore.setSize(591,28);
			lblOrderStore.setLocation(42, 34+28);
			lblOrderStore.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderStore);
			
			JLabel lblOrderDate = new JLabel("주문일시 : ");
			lblOrderDate.setSize(253,28);
			lblOrderDate.setLocation(42, 34+28+28);
			lblOrderDate.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderDate);
			
			JLabel lblReservedTime = new JLabel("예약시간 : ");
			lblReservedTime.setSize(253,28);
			lblReservedTime.setLocation(42, 34+28+28+28);
			lblReservedTime.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblReservedTime);
			
			JLabel lblTitle = new JLabel("주문한 메뉴");
			lblTitle.setSize(164,36);
			lblTitle.setLocation(266, 157);
			lblTitle.setFont(new Font("맑은 고딕",Font.BOLD,22));
			pOrderDetails.add(lblTitle);
			
			JPanel pOrderedMenu = new JPanel();
			
			
			jsp.setSize(670,539);
			jsp.setLocation(60, 30);
			p_body.add(jsp);
			
			add(p_body);
			
		}
}
