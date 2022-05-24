package orderhere.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommonPanel extends JFrame{
	private static JPanel p_top;
	private static JLabel btnback;
	private static JPanel p_title;
	private static JLabel lbl_title;
	private static JLabel logo;
	private static int titleX,titleY;
	
	public static JPanel createTop(String title) 
	{
		//최상위 컴포넌트
		p_top = new JPanel();
		p_top.setSize(1050, 158);
		p_top.setLocation(0, 0);
		p_top.setBackground(new Color(54,36,21));
		p_top.setLayout(null);
		
		//뒤로가기 컴포넌트
		ImageIcon iiconbtnback = new ImageIcon("images/common/Btn_Back_EnabledTrue.png");
		btnback = new JLabel(iiconbtnback);
		btnback.setSize(75,74);
		btnback.setLocation(26, 40);
		
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
		String[] fontTitle = {"CART","ORDER LIST","POINT USAGE","PAYMENT","ORDER DETAILS"};
		if(title.equals(fontTitle[0])) return fontsizex[0];
		else if(title.equals(fontTitle[1])) return fontsizex[1];
		else if(title.equals(fontTitle[2])) return fontsizex[2];
		else if(title.equals(fontTitle[3])) return fontsizex[3];
		else if(title.equals(fontTitle[4])) return fontsizex[4];
		return 0;
	}
	
	public static int getTitleH(String title) {
		int[] fontsizey = {38,50,55,48,50};
		String[] fontTitle = {"CART","ORDER LIST","POINT USAGE","PAYMENT","ORDER DETAILS"};
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
		}
		return str;
	}
}
