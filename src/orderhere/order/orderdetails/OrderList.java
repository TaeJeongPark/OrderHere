package orderhere.order.orderdetails;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import orderhere.common.DB;
import orderhere.common.UsersData;
import orderhere.order.CommonPanel;

public class OrderList extends JPanel implements MouseListener{
		
		private String usersid = UsersData.getUsersId();
	
		private JPanel p_body;
		private String[] sOrderDate;
		private String[] sOrderStore;
		private String[] sOrderSum;		
		private JLabel[] lblOrderDate;
		private JLabel[] lblOrderStore;
		private JLabel[] lblOrderSum;
		private JPanel pList;
		private JPanel[] pListInner;
		private int[] iIsSameCart;
		private JPanel pListInnerNothing;
		private int iListnum;
		private JLabel lblSearch;
		private JTextField tfFrom;
		private JTextField tfTo;
		
		public OrderList() 
		{
			getDataFromDB();
			
			setSize(1050,750);
			setLocation(0,0);//화면 가운데 배치 필요
			setLayout(null);
			
			add(new CommonPanel().createTop("Order List"));
			createBody();
			
			setVisible(true);
		}

		private void getDataFromDB() {
			ResultSet rsOrders;
			ResultSet rsStore;
			
			rsOrders = DB.getResult("select count(distinct cartidissamecart) from orders where usersid='"+usersid+"'");
			try {
				rsOrders.next();
				iListnum = rsOrders.getInt(1);
				rsOrders.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(iListnum==0) return;
			
			sOrderDate = new String[iListnum];
			sOrderStore  = new String[iListnum];
			sOrderSum  = new String[iListnum];
			iIsSameCart = new int[iListnum];
			
			lblOrderDate = new JLabel[iListnum];
			lblOrderStore = new JLabel[iListnum];
			lblOrderSum = new JLabel[iListnum];
			
			rsOrders = DB.getResult("select distinct orderdate,storeid,ordersum,cartidissamecart from orders"
					+ " where usersid='"+usersid+"' order by cartidissamecart desc");
			try {
				for (int i = 0; i < iListnum; i++) {
					if(rsOrders.next()) 
					{
						sOrderDate[i] = rsOrders.getString("orderdate");
						int storeid = rsOrders.getInt("storeid");
						rsStore = DB.getResult("select storename from store where storeid="+storeid);
						if(rsStore.next()) 
						{
							sOrderStore[i] = rsStore.getString(1);
						}else 
						{
							sOrderStore[i] = "";
						}
						rsStore.close();
						sOrderSum[i] = rsOrders.getString("ordersum");
						iIsSameCart[i] = rsOrders.getInt("cartidissamecart");
					}
					
				}
				rsOrders.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}

		private void createBody() {
			p_body = new JPanel();
			p_body.setSize(1050, 750-158);
			p_body.setLocation(0, 158);
			p_body.setBackground(new Color(234,199,132));
			p_body.setLayout(null);
			
			tfFrom = new JTextField(15);
			tfFrom.setHorizontalAlignment(JTextField.CENTER);
			tfFrom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			tfFrom.setLocation(91, 28);
			tfFrom.setSize(165,39);
			tfFrom.setFont(new Font("맑은 고딕",Font.PLAIN,18));
			tfFrom.setEditable(false);
			tfFrom.addMouseListener(this);
			p_body.add(tfFrom);
			
			JLabel lblFromTo = new JLabel("~");
			lblFromTo.setLocation(91+165+19, 38);
			lblFromTo.setSize(15, 15);
			lblFromTo.setFont(new Font("맑은 고딕",Font.PLAIN,12));
			p_body.add(lblFromTo);
			
			tfTo = new JTextField(15);
			tfTo.setHorizontalAlignment(JTextField.CENTER);
			tfTo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			tfTo.setLocation(91+165+49, 28);
			tfTo.setSize(165,39);
			tfTo.setFont(new Font("맑은 고딕",Font.PLAIN,18));
			tfTo.setEditable(false);
			tfTo.addMouseListener(this);
			p_body.add(tfTo);
			
			lblSearch = new JLabel(new ImageIcon("images/orderdetails/Btn_Search_EnabledTrue.png"));
			lblSearch.setSize(35, 35);
			lblSearch.setLocation(91+165+49+165+9,33);
			lblSearch.addMouseListener(this);
			p_body.add(lblSearch);
			
			JLabel lblExplain = new JLabel("최대 3개월까지 조회 가능합니다.");
			lblExplain.setSize(550, 23);
			lblExplain.setLocation(92, 76);
			lblExplain.setForeground(new Color(81,81,81));
			lblExplain.setFont(new Font("맑은 고딕",Font.PLAIN,15));
			p_body.add(lblExplain);
			
			addOrderTitlePanel();
			
			pList = new JPanel();
			pList.setLayout(null);
			pList.setSize(836, 349);
			pList.setBackground(new Color(255,236,198));
			
			if(iListnum>=8) pList.setPreferredSize(new Dimension(836, 44*iListnum));
			else if(iListnum>=0&&iListnum<8) pList.setPreferredSize(new Dimension(836, 44*7));
			
			addListPanels();
			
			JScrollPane jsp = new JScrollPane(pList, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setSize(855,349);
			jsp.setLocation(92, 149);
			jsp.setBackground(new Color(255,236,198));
			jsp.getVerticalScrollBar().setUnitIncrement(8);
			p_body.add(jsp);
			
			
			add(p_body);
			
		}

		private void addListPanels() {
			
			if(iListnum==0) 
			{
				pListInnerNothing = new JPanel();
				pListInnerNothing.setLayout(null);
				pListInnerNothing.setSize(837,44*7);
				pListInnerNothing.setLocation(0,0);
				pListInnerNothing.setBackground(new Color(255,236,198));
				
				JLabel lblNothing = new JLabel("주문 내역이 없습니다");
				lblNothing.setSize(250,20);
				lblNothing.setLocation(20, 20);
				lblNothing.setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInnerNothing.add(lblNothing);
				
				pList.add(pListInnerNothing);
				return;
			}
			
			
			pListInner = new JPanel[iListnum];
			for (int i = 0; i < iListnum; i++) {
				pListInner[i] = new JPanel();
				pListInner[i].setLayout(null);
				pListInner[i].setSize(837,44);
				pListInner[i].setLocation(0, 44*i);
				pListInner[i].setBackground(new Color(255,236,198));
				pListInner[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				pListInner[i].addMouseListener(this);
				
				lblOrderDate[i] = new JLabel(sOrderDate[i]);
				lblOrderDate[i].setSize(147,20);
				lblOrderDate[i].setLocation(81, 12);
				lblOrderDate[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblOrderDate[i]);
				
				lblOrderStore[i] = new JLabel(sOrderStore[i]);
				lblOrderStore[i].setSize(173,20);
				lblOrderStore[i].setLocation(81+147+92+65, 12);
				lblOrderStore[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblOrderStore[i]);
				
				lblOrderSum[i] = new JLabel(CommonPanel.toAddCommaAtPrice(Integer.parseInt(sOrderSum[i]))+"원");
				lblOrderSum[i].setSize(173,20);
				lblOrderSum[i].setLocation(81+147+92+65+240, 12);
				lblOrderSum[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblOrderSum[i]);
				
				pList.add(pListInner[i]);
				
				
			}
			
		}

		private void addOrderTitlePanel() {
			JPanel pOrderTitle = new JPanel();
			pOrderTitle.setLayout(null);
			pOrderTitle.setSize(854, 44);
			pOrderTitle.setLocation(92, 106);
			pOrderTitle.setBackground(new Color(255,236,198));
			pOrderTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			JLabel lblOrderDate = new JLabel("주문 일시");
			lblOrderDate.setSize(110, 18);
			lblOrderDate.setLocation(130, 15);
			lblOrderDate.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pOrderTitle.add(lblOrderDate);
			
			JLabel lblOrderStore = new JLabel("주문 매장");
			lblOrderStore.setSize(110, 18);
			lblOrderStore.setLocation(130+110+143, 15);
			lblOrderStore.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pOrderTitle.add(lblOrderStore);
			
			JLabel lblOrderSum = new JLabel("총금액");
			lblOrderSum.setSize(110, 18);
			lblOrderSum.setLocation(130+110+143+110+141, 15);
			lblOrderSum.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pOrderTitle.add(lblOrderSum);
			
			
			p_body.add(pOrderTitle);
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getSource()==tfFrom||e.getSource()==tfTo||e.getSource()==lblSearch) 
			{
				JOptionPane.showMessageDialog(this, "서비스 준비중입니다.");
			}
			
			for (int i = 0; i < iListnum; i++) {
				if(e.getSource()==pListInner[i]) 
				{
					sendDataToOrderDetails(iIsSameCart[i]);
				}
			}
		}

		private void sendDataToOrderDetails(int iIsSameCart) {
			CommonPanel.redraw(new OrderDetails(iIsSameCart));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Pressed.png"));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Rollover.png"));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Rollover.png"));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_EnabledTrue.png"));
			}
		}
}
