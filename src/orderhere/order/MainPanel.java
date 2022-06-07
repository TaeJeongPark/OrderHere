package orderhere.order;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orderhere.order.cart.Cart;
import orderhere.order.orderdetails.OrderDetails;
import orderhere.order.orderdetails.OrderList;
import orderhere.order.pointusage.PointUsage;

public class MainPanel extends JPanel implements ActionListener{
	
	private static String usersId="aa1234";	
	private static int storeId=1;		
	private static int iIsSameCart=5;	
	
	private JPanel p_top;
	private JPanel p_title;
	private JLabel logo;
	private JPanel p_body;
	private JButton btn_point;
	private JButton btn_orderedList;
	private JButton btn_cart;

	public MainPanel() 
	{
		setSize(1050,750);
		setLocation(0,0);//화면 가운데 배치 필요
		setLayout(null);
		
		createBody();
	}

	private void createBody() {
		p_top = new JPanel();
		p_top.setSize(1050, 158);
		p_top.setLocation(0, 0);
		p_top.setBackground(new Color(11,66,26));
		p_top.setLayout(null);
		
		p_title = new JPanel();
		p_title.setSize(555, 77);
		p_title.setLocation(26+75+147, 40);
		p_title.setBackground(new Color(11,66,26));
		p_title.setLayout(null);
		
		ImageIcon iiconlogo = new ImageIcon("images/common/logo_titlebar.png");
		logo = new JLabel(iiconlogo);
		logo.setSize(100,100);
		logo.setLocation(904, 40);
		
		p_top.add(p_title);
		p_top.add(logo);
		
		add(p_top);
		
		p_body = new JPanel();
		p_body.setSize(1050, 750-158);
		p_body.setLocation(0, 158);
		p_body.setBackground(new Color(234,199,132));
		p_body.setLayout(null);
		
		add(p_body);
		
		addButton();
		
	}

	private void addButton() {
		btn_point = new JButton("포인트 내역");
		btn_point.setSize(183,50);
		btn_point.setLocation(426, 750-158-22-50);
		btn_point.addActionListener(this);
		p_body.add(btn_point);
		
		btn_orderedList = new JButton("주문 내역");
		btn_orderedList.setSize(183,50);
		btn_orderedList.setLocation(426+20+183, 750-158-22-50);
		btn_orderedList.addActionListener(this);
		p_body.add(btn_orderedList);
		
		btn_cart = new JButton("장바구니");
		btn_cart.setSize(183,50);
		btn_cart.setLocation(426+20+183+20+183, 750-158-22-50);
		btn_cart.addActionListener(this);
		p_body.add(btn_cart);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_point) 
		{
			CommonPanel.redraw(new PointUsage());
			
		}else if(e.getSource()==btn_orderedList) 
		{
			CommonPanel.redraw(new OrderList());
			
		}else if(e.getSource()==btn_cart) 
		{
			CommonPanel.redraw(new Cart());
		}
	}

	public static String getUsersId() {
		return usersId;
	}

	public static void setUsersId(String usersId) {
		MainPanel.usersId = usersId;
	}

	public static int getStoreId() {
		return storeId;
	}

	public static void setStoreId(int storeId) {
		MainPanel.storeId = storeId;
	}

	public static int getiIsSameCart() {
		return iIsSameCart;
	}

	public static void setiIsSameCart(int iIsSameCart) {
		MainPanel.iIsSameCart = iIsSameCart;
	}

	
}
