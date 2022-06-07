package orderhere.order.orderdetails;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import orderhere.order.CommonPanel;
import orderhere.order.MainPanel;
import orderhere.order.db.DB;

public class OrderDetails extends JPanel{
	
		private String usersid = MainPanel.getUsersId();
		private int iIsSameCart;
	
		private String sOrderDate;
		private int iStoreId;
		private String sStoreName;
		private String sStoreAddress;
		private String sOrderReservedTime;
		private int iOrderUsageCash;
		private int iOrderUsagePoint;
		private int iOrderSum;
		
		private int[] cartid;
		private String[] sarrMenuNames;
		private int[] iarrCartQuantities;
		private int[] iarrMenuPrice;
		private String[] sarrMenuOptions;
		
		private int cartnum;
		
		private JLabel[] lblMenuName;
		private JLabel[] lblMenuQuantity;
		private JLabel[] lblMenuPrice;
		private JLabel[] lblMenuOptions;
		
		public OrderDetails(int iIsSameCart) 
		{
			this.iIsSameCart = iIsSameCart;
			
			getDataFromDB();
			
			setSize(1050,750);
			setLocation(0,0);//화면 가운데 배치 필요
			setLayout(null);
			
			add(new CommonPanel().createTop("OrderDetails"));
			createBody();
			
			setVisible(true);
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
			
			if(cartnum>=3) pOrderDetails.setPreferredSize(new Dimension(800, 189+56*cartnum+183));
			else if(cartnum>=0&&cartnum<3) pOrderDetails.setPreferredSize(new Dimension(800, 189+56*3+179));
			
			JScrollPane jsp = new JScrollPane(pOrderDetails, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setSize(670,539);
			jsp.setLocation(165, 30);
			jsp.getVerticalScrollBar().setUnitIncrement(8);
			
			p_body.add(jsp);

			JLabel lblOrderid = new JLabel("주문번호 : ");
			lblOrderid.setSize(264,28);
			lblOrderid.setLocation(42, 34);
			lblOrderid.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderid);
			
			JLabel lblOrderStore = new JLabel("주문매장 : 스타벅스 "+sStoreName+"("+sStoreAddress+")");
			lblOrderStore.setSize(591,28);
			lblOrderStore.setLocation(42, 34+28);
			lblOrderStore.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderStore);
			
			JLabel lblOrderDate = new JLabel("주문일시 : "+sOrderDate);
			lblOrderDate.setSize(303,28);
			lblOrderDate.setLocation(42, 34+28+28);
			lblOrderDate.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pOrderDetails.add(lblOrderDate);
			
			JLabel lblReservedTime = new JLabel("예약시간 : "+sOrderReservedTime);
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
			pOrderedMenu.setLayout(null);
			pOrderedMenu.setLocation(0, 189);
			pOrderedMenu.setSize(652,56*cartnum);
			pOrderDetails.add(pOrderedMenu);
			
			JPanel[] pOrderedMenuList = new JPanel[cartnum];
			lblMenuName = new JLabel[cartnum];
			lblMenuQuantity = new JLabel[cartnum];
			lblMenuPrice  = new JLabel[cartnum];
			lblMenuOptions = new JLabel[cartnum];
			
			for (int i = 0; i < cartnum; i++) {
				pOrderedMenuList[i] = new JPanel();
				pOrderedMenuList[i].setLayout(null);
				pOrderedMenuList[i].setSize(652,56);
				pOrderedMenuList[i].setLocation(0,0+56*i);
				pOrderedMenuList[i].setBackground(Color.WHITE);
				
				lblMenuName[i] = new JLabel(sarrMenuNames[i]);
				lblMenuName[i].setSize(368,27);
				lblMenuName[i].setLocation(42, 0);
				lblMenuName[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
				pOrderedMenuList[i].add(lblMenuName[i]);
				
				System.out.print(sarrMenuNames[i]);
				lblMenuQuantity[i] = new JLabel("x"+ iarrCartQuantities[i]);
				lblMenuQuantity[i].setSize(42,27);
				lblMenuQuantity[i].setLocation(42+368, 0);
				lblMenuQuantity[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
				pOrderedMenuList[i].add(lblMenuQuantity[i]);
				
				lblMenuPrice[i] = new JLabel(CommonPanel.toAddCommaAtPrice(iarrMenuPrice[i])+" 원");
				lblMenuPrice[i].setSize(108,27);
				lblMenuPrice[i].setLocation(42+368+42+54+51, 0);
				lblMenuPrice[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
				pOrderedMenuList[i].add(lblMenuPrice[i]);
				
				lblMenuOptions[i] = new JLabel(sarrMenuOptions[i]);
				lblMenuOptions[i].setSize(368,27);
				lblMenuOptions[i].setLocation(42, 25);
				lblMenuOptions[i].setFont(new Font("맑은 고딕",Font.PLAIN,14));
				lblMenuOptions[i].setForeground(new Color(112,152,255));
				pOrderedMenuList[i].add(lblMenuOptions[i]);
				
				pOrderedMenu.add(pOrderedMenuList[i]);
			}
			
			if(cartnum>=3) pOrderedMenu.setPreferredSize(new Dimension(800, 56*cartnum));
			else if(cartnum>=0&&cartnum<3) pOrderedMenu.setPreferredSize(new Dimension(800, 56*3));
			
			JPanel pBottom = new JPanel();
			pBottom.setLayout(null);
			pBottom.setSize(652,182);
			pBottom.setLocation(0,189+56*cartnum);
			pBottom.setBackground(Color.WHITE);
			
			JLabel lblSum= new JLabel("총 주문금액");
			lblSum.setSize(164,36);
			lblSum.setLocation(266, 23);
			lblSum.setFont(new Font("맑은 고딕",Font.BOLD,22));
			pBottom.add(lblSum);
			
			JLabel lblPay = new JLabel("총 주문 금액");
			lblPay.setSize(123,21);
			lblPay.setLocation(42, 78);//78+21+21+15
			lblPay.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblPay);
			
			JLabel lblUsagePoint = new JLabel("사용 포인트");
			lblUsagePoint.setSize(123,21);
			lblUsagePoint.setLocation(42, 78+21+8);//78
			lblUsagePoint.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblUsagePoint);
			
			JLabel lblUsageCash = new JLabel("사용 캐시");
			lblUsageCash.setSize(123,21);
			lblUsageCash.setLocation(42, 78+21+21+15);
			lblUsageCash.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblUsageCash);

			JLabel lblPayData = new JLabel(CommonPanel.toAddCommaAtPrice(iOrderSum)+" 원");
			lblPayData.setSize(123,21);
			lblPayData.setLocation(42+500, 78);
			lblPayData.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblPayData);
			
			JLabel lblUsagePointData = new JLabel(CommonPanel.toAddCommaAtPrice(iOrderUsagePoint)+"P");
			lblUsagePointData.setSize(123,21);
			lblUsagePointData.setLocation(42+500, 78+21+8);
			lblUsagePointData.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblUsagePointData);
			
			JLabel lblUsageCashData = new JLabel(CommonPanel.toAddCommaAtPrice(iOrderUsageCash)+" 원");
			lblUsageCashData.setSize(123,21);
			lblUsageCashData.setLocation(42+500, 78+21+21+15);
			lblUsageCashData.setFont(new Font("맑은 고딕",Font.BOLD,18));
			pBottom.add(lblUsageCashData);
			
			
			pOrderDetails.add(pBottom);
			
			add(p_body);
			
		}
		private void getDataFromDB() {
			DB.init();
			cartnum=0;
			ResultSet rs = DB.getResult("select count(*) from orders where usersid='"+
					usersid+"' and cartidissamecart="+iIsSameCart);
			try {
				if(rs.next()) cartnum = rs.getInt(1);
			} catch (SQLException e1) {
				System.out.println("장바구니 개수: 조회된 데이터가 없습니다.");
				e1.printStackTrace();
			}finally 
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			cartid = new int[cartnum];
			
			rs = DB.getResult("select * from orders where usersid='"+
										usersid+"' and cartidissamecart="+iIsSameCart);
			try {
					for (int i = 0; i < cartnum; i++) {
						if(rs.next()) 
						{
							if(i==0) 
							{
								iStoreId = rs.getInt("storeid");
								sOrderDate = rs.getString("orderdate");
								sOrderReservedTime = rs.getString("orderreservedtime");
								iOrderUsagePoint = rs.getInt("orderusagepoint");
								iOrderUsageCash = rs.getInt("orderusagecash");
								iOrderSum = rs.getInt("ordersum");
							}
							cartid[i] = rs.getInt("cartid");
						}
					}
					
			} catch (SQLException e) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			rs = DB.getResult("select * from store where storeid="+iStoreId);
			try {
				if(rs.next()) 
				{
					sStoreName = rs.getString("storename");
					sStoreAddress = rs.getString("storeaddress");
				}
			} catch (SQLException e1) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
			
			Arrays.sort(cartid);
			
			int[] menuid = new int[cartnum];
			iarrCartQuantities = new int[cartnum];
			sarrMenuOptions = new String[cartnum];
			for (int i = 0; i < cartnum; i++) {
				rs = DB.getResult("select * from cart where cartid="+cartid[i]);
				try {
					if(rs.next()) 
					{
						menuid[i] = rs.getInt("menuid");
						iarrCartQuantities[i] = rs.getInt("cartquantity");
						sarrMenuOptions[i] = rs.getString("cartstatus")+"/"+
											rs.getString("cartpacking")+"/"+
											rs.getString("cartsize")+"/"+
											rs.getString("cartsyrup")+"/"+
											"x"+rs.getInt("cartsyrupnum")+"/"+
											rs.getString("cartwhippedcream");
						System.out.println(sarrMenuOptions[i]);
					}
				} catch (SQLException e) {
					System.out.println("메뉴 옵션: 조회된 데이터가 없습니다.");
					e.printStackTrace();
				}
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sarrMenuNames = new String[cartnum];
			iarrMenuPrice = new int[cartnum];
			
			for (int i = 0; i < cartnum; i++) {
				rs = DB.getResult("select * from menu where menuid="+menuid[i]);
				try {
					if(rs.next()) 
					{
						sarrMenuNames[i] = rs.getString("menuname");
						iarrMenuPrice[i] = rs.getInt("menuprice");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
