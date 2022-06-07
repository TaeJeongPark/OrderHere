package orderhere.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orderhere.order.cart.Cart;
import orderhere.order.orderdetails.OrderList;

public class CommonPanel extends JFrame implements MouseListener{
	private static JPanel p_top;
	private static JLabel btnback;
	private static JPanel p_title;
	private static JLabel lbl_title;
	private static JLabel logo;
	private static int titleX,titleY;
	private static ImageIcon iiconbtnbackEnabledTrue;
	private static ImageIcon iiconbtnbackRollover;
	private static ImageIcon iiconbtnbackPressed;
	private String title;
	
	public JPanel createTop(String title) 
	{
		this.title = title;
		//최상위 컴포넌트
		p_top = new JPanel();
		p_top.setSize(1050, 158);
		p_top.setLocation(0, 0);
		p_top.setBackground(new Color(54,36,21));
		p_top.setLayout(null);
		
		//뒤로가기 컴포넌트
		
		iiconbtnbackEnabledTrue = new ImageIcon("images/common/Btn_Back_EnabledTrue.png");
		iiconbtnbackRollover = new ImageIcon("images/common/Btn_Back_Rollover.png");
		iiconbtnbackPressed = new ImageIcon("images/common/Btn_Back_Pressed.png");
		
		btnback = new JLabel(iiconbtnbackEnabledTrue);
		btnback.setSize(75,74);
		btnback.setLocation(26, 40);
		btnback.addMouseListener(this);
		
		//프레임명 컴포넌트
		p_title = new JPanel();
		p_title.setSize(555, 77);
		p_title.setLocation(26+75+147, 40);
		p_title.setBackground(new Color(54,36,21));
		p_title.setLayout(null);
		
		lbl_title = new JLabel(title);
		lbl_title.setForeground(Color.WHITE);
		lbl_title.setSize(getTitleW(title),getTitleH(title));
		lbl_title.setFont((new Font("Santana-Black", Font.PLAIN, 40)));
		lbl_title.setLocation(getTitleLocationX(),getTitleLocationY());
		p_title.add(lbl_title);
		
		//로고 컴포넌트
		ImageIcon iiconlogo = new ImageIcon("images/common/logo_titlebar.png");
		logo = new JLabel(iiconlogo);
		logo.setSize(100,100);
		logo.setLocation(904, 40);
		
		p_top.add(btnback);
		p_top.add(p_title);
		p_top.add(logo);
		
		return p_top;
		
	}
	
	public static int getTitleW(String title) {
		int[] fontsizex = {108,260,286,216,333};//cart,orderlist,pointusage,payment,orderdetail
		String[] fontTitle = {"Cart","Order List","Point Usage","Payment","OrderDetails"};
		if(title.equals(fontTitle[0])) return fontsizex[0];
		else if(title.equals(fontTitle[1])) return fontsizex[1];
		else if(title.equals(fontTitle[2])) return fontsizex[2];
		else if(title.equals(fontTitle[3])) return fontsizex[3];
		else if(title.equals(fontTitle[4])) return fontsizex[4];
		return 0;
	}
	
	public static int getTitleH(String title) {
		int[] fontsizey = {38,50,55,48,50};
		String[] fontTitle = {"Cart","Order List","Point Usage","Payment","OrderDetails"};
		if(title.equals(fontTitle[0])) return fontsizey[0];
		else if(title.equals(fontTitle[1])) return fontsizey[1];
		else if(title.equals(fontTitle[2])) return fontsizey[2];
		else if(title.equals(fontTitle[3])) return fontsizey[3];
		else if(title.equals(fontTitle[4])) return fontsizey[4];
		return 0;
	}
	
	private static int getTitleLocationX() {
		return (p_title.getWidth())/2 - (lbl_title.getWidth())/2;
	}
	
	private static int getTitleLocationY() {
		return (p_title.getHeight())/2 - (lbl_title.getHeight())/2;
	}

	public static String toAddCommaAtPrice(int price) {
		/*  First Writed By SonhYunHa, Inha Technical College grade 2, Korea 
		 * Writed in 2022.05.22 */
		if(price<1000&&price>=0) return price+"";
		
		String str="";
		int iOriginSum = price;
		String sOriginSum = String.valueOf(iOriginSum);
		
		if(iOriginSum>=1000&&iOriginSum<10000) {
			char[] caOriginSum = sOriginSum.toCharArray();
			char[] arrcopy = {' ',',',' ',' ',' '};
			arrcopy[0] = caOriginSum[0];
			for (int i = 2; i < 5; i++) {
				arrcopy[i] = caOriginSum[i-1];
			}
			return String.valueOf(arrcopy);
		}else if(iOriginSum>=10000&&iOriginSum<100000) {
			char[] caOriginSum = sOriginSum.toCharArray();
			char[] arrcopy = {' ',' ',',',' ',' ',' '};
			arrcopy[0] = caOriginSum[0];
			arrcopy[1] = caOriginSum[1];
			for (int i = 3; i < 6; i++) {
				arrcopy[i] = caOriginSum[i-1];
			}
			return String.valueOf(arrcopy);
		}else if(iOriginSum>=100000&&iOriginSum<1000000) {
			char[] caOriginSum = sOriginSum.toCharArray();
			char[] arrcopy = {' ',' ',' ',',',' ',' ',' '};
			arrcopy[0] = caOriginSum[0];
			arrcopy[1] = caOriginSum[1];
			arrcopy[2] = caOriginSum[2];
			for (int i = 4; i < 7; i++) {
				arrcopy[i] = caOriginSum[i-1];
			}
			return String.valueOf(arrcopy);
		}
		return str;
	}

	public static String transformTimeFormat(String time) {
		/*  First Writed By SonhYunHa, Inha Technical College grade 2, Korea 
		 * Writed in 2022.05.30 */
		char[] carrTime = time.toCharArray();
		for(char c : carrTime) 
		{
			System.out.println(c);
		}
		if(carrTime[6]=='P') 
		{
			carrTime[0] = (char)((int)carrTime[0] + 1);
			carrTime[1] = (char)((int)carrTime[1] + 2);
		}
		char[] carrOtherFormatTime = {' ',' ',' ',':',' ',' ',' '};
		
		carrOtherFormatTime[0] = carrTime[0];
		carrOtherFormatTime[1] = carrTime[1];
		carrOtherFormatTime[3] = carrTime[2];
		carrOtherFormatTime[5] = carrTime[3];
		carrOtherFormatTime[6] = carrTime[4];
		
		return String.valueOf(carrOtherFormatTime);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		btnback.setIcon(iiconbtnbackPressed);
		if(this.title.equals("Payment")) 
		{
			redraw(new Cart());
		}else if(this.title.equals("OrderDetails")) 
		{
			redraw(new OrderList());
		}else if(this.title.equals("Point Usage")||this.title.equals("Order List")||this.title.equals("Cart")) 
		{
			redraw(new MainPanel());
		}
	}
	public static void redraw(JPanel panel) {
		Main.getMf().remove(Main.getMf().getCurrentPanel());
		Main.getMf().setCurrentPanel(panel);
		Main.getMf().add(Main.getMf().getCurrentPanel());
		Main.getMf().getContentPane().setVisible(false);
		Main.getMf().getContentPane().setVisible(true);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnback.setIcon(iiconbtnbackRollover);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		btnback.setIcon(iiconbtnbackRollover);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		btnback.setIcon(iiconbtnbackEnabledTrue);
	}

	public static JLabel getLbl_title() {
		return lbl_title;
	}
	
}
