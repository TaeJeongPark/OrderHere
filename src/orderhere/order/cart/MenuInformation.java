package orderhere.order.cart;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import orderhere.common.DB;
import orderhere.common.UsersData;
import orderhere.order.CommonPanel;

public class MenuInformation extends JPanel implements ActionListener, MouseListener, ItemListener {

	private int iMenuid;
	private String usersid = UsersData.getUsersId();
	private int storeid = UsersData.getStoreId();
	private int iIsSameCart = UsersData.getiIsSameCart();
	
	private String sMenuName;
	private int iMenuPrice;
	private String sMenuCategory;
	private String sMenuImage;
	private String sMenuExplanation;
	private String sMenuAllergy;
	
	private JPanel p_body;
	
	private final String paraMenuName;
	private JButton btnToCart;
	private JRadioButton[] rbStatus;
	private JRadioButton[] rbPacking;
	private JRadioButton[] rbSize;
	private JSpinner[] spinner;
	private Vector<String> vecData;
	private JComboBox<String> combo;
	private ButtonGroup bgStatus;
	private ButtonGroup bgPacking;
	private ButtonGroup bgSize;
	
	private String paraStatus;
	private String paraPacking;
	private String paraSize;
	private String paraSyrupName;
	private int paraSyrupNum;
	private String paraCream="미추가";
	private int paraQtty;
	private JRadioButton rbCream;
	private JSpinner spnSyrup;
	private JSpinner spnQtty;
	
	public MenuInformation(String menuname) 
	{
		paraMenuName = menuname;
		
		getDataFromDB();
		
		setSize(1050,750);
		setLocation(0,0);
		setLayout(null);
		
		add(new CommonPanel().createTop("Menu Information"));
		createBody();
	}

	private void getDataFromDB() {
		
		ResultSet rs = null;

		//menuid 조회
		try {
			rs = DB.getResult("select menuid from menu where menuname='"+paraMenuName+"'");
			if(rs.next()) 
			{
				iMenuid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			rs = DB.getResult("select * from menu where menuid="+iMenuid);
			if(rs.next()) 
			{
				sMenuName = rs.getString("menuname");
				iMenuPrice = rs.getInt("menuprice");
				sMenuCategory = rs.getString("menucategory");
				sMenuImage = rs.getString("menuimage");
				sMenuExplanation = rs.getString("menuexplanation");
				sMenuAllergy = rs.getString("menuallergy");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private void createBody() {
		p_body = new JPanel();
		p_body.setSize(1050, 750-158);
		p_body.setLocation(0, 158);
		p_body.setBackground(new Color(234,199,132));
		p_body.setLayout(null);
		
		addLeft();
		addRight();
		
		add(p_body);
	}


	private void addLeft() {
		JLabel lblMenuImage = new JLabel(new ImageIcon("images/menu/Menu Information/"+sMenuCategory+"/"+sMenuImage));
		lblMenuImage.setSize(400, 400);
		lblMenuImage.setLocation(50, 36);
		p_body.add(lblMenuImage);
		
		JLabel lblMenuName = new JLabel(sMenuName);
		lblMenuName.setSize(330,30);
		lblMenuName.setLocation(51, 36+400+6);
		lblMenuName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		p_body.add(lblMenuName);
		
		JLabel lblMenuPrice = new JLabel(CommonPanel.toAddCommaAtPrice(iMenuPrice)+"원");
		lblMenuPrice.setSize(84,25);
		lblMenuPrice.setLocation(51+300+25+25, 36+400+6+3);
		lblMenuPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p_body.add(lblMenuPrice);
		
		JLabel lblMenuExplanation = new JLabel("<html>" + sMenuExplanation + "<html>");
		lblMenuExplanation.setSize(396, 36);
		lblMenuExplanation.setLocation(51, 36+400+6+15+5);
		lblMenuExplanation.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		p_body.add(lblMenuExplanation);
		
		JLabel lblMenuAllergy = new JLabel(sMenuAllergy);
		lblMenuAllergy.setSize(384, 16);
		lblMenuAllergy.setLocation(51, 36+400+6+15+5+36+9);
		lblMenuAllergy.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		p_body.add(lblMenuAllergy);
	}
	
	private void addRight() {
		
		JLabel lblOptionsChoiceTitle = new JLabel("옵션 선택");
		lblOptionsChoiceTitle.setSize(107,32);
		lblOptionsChoiceTitle.setLocation(36+400+35, 36);
		lblOptionsChoiceTitle.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		p_body.add(lblOptionsChoiceTitle);
		
		String[] sStatus = {"HOT","ICED"};
		rbStatus = new JRadioButton[2];
		bgStatus = new ButtonGroup();
		
		JLabel[] lblEssentialOption = new JLabel[3];
		
		for (int i = 0; i < 3; i++) {
			lblEssentialOption[i] = new JLabel("* 필수");
			lblEssentialOption[i].setSize(75, 22);
			lblEssentialOption[i].setFont(new Font("맑은 고딕", Font.BOLD, 16));
			lblEssentialOption[i].setForeground(Color.RED);
		}
		
		for (int i = 0; i < 2; i++) {
			rbStatus[i] = new JRadioButton(sStatus[i]);
			rbStatus[i].setSize(75, 22);
			rbStatus[i].setLocation(36+400+35+90*i, 125);
			rbStatus[i].setBackground(new Color(234,199,132));
			rbStatus[i].setFont(new Font("맑은 고딕", Font.BOLD, 16));
			rbStatus[i].addItemListener(this);
			bgStatus.add(rbStatus[i]);
			p_body.add(rbStatus[i]);
		}
		lblEssentialOption[0].setLocation(36+400+35+90*2, 125);
		p_body.add(lblEssentialOption[0]);
			
		String[] sPacking =	{"테이크아웃","매장컵"};
		rbPacking = new JRadioButton[2];
		bgPacking = new ButtonGroup();
		
		for (int i = 0; i < 2; i++) {
			rbPacking[i] = new JRadioButton(sPacking[i]);
			rbPacking[i].setSize(110, 22);
			rbPacking[i].setLocation(36+400+35+135*i, 125+50);
			rbPacking[i].setBackground(new Color(234,199,132));
			rbPacking[i].setFont(new Font("맑은 고딕", Font.BOLD, 16));
			rbPacking[i].addItemListener(this);
			bgPacking.add(rbPacking[i]);
			p_body.add(rbPacking[i]);
		}
		lblEssentialOption[1].setLocation(36+400+35+135*2-10, 125+50);
		p_body.add(lblEssentialOption[1]);
		
		String[] sSize = {"Tall","GRANDE","VENTI"};
		rbSize = new JRadioButton[3];
		bgSize = new ButtonGroup();

		for (int i = 0; i < 3; i++) {
			rbSize[i] = new JRadioButton(sSize[i]);
			rbSize[i].setSize(105, 22);
			rbSize[i].setLocation(36+400+35+105*i, 125+50+50);
			rbSize[i].setBackground(new Color(234,199,132));
			rbSize[i].setFont(new Font("맑은 고딕", Font.BOLD, 16));
			rbSize[i].addItemListener(this);
			bgSize.add(rbSize[i]);
			p_body.add(rbSize[i]);
		}
		lblEssentialOption[2].setLocation(36+400+35+105*3, 125+50+50);
		p_body.add(lblEssentialOption[2]);		
		
		String[] sSyrup = {"바닐라","카라멜","초코","시나몬"};
		vecData = new Vector<String>();
		for (int i = 0; i < sSyrup.length; i++) {
			vecData.add(sSyrup[i]);
		}
		combo = new JComboBox<String>(vecData);
		combo.setSize(80, 25);
		combo.setLocation(36+400+35, 125+50+50+50);
		combo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p_body.add(combo);
		
		SpinnerNumberModel numberModelSyrup = new SpinnerNumberModel(0, 0, 10, 1);
		spnSyrup = new JSpinner(numberModelSyrup);
		spnSyrup.setSize(80, 25);
		spnSyrup.setLocation(36+400+35+80+30, 125+50+50+50);
		spnSyrup.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p_body.add(spnSyrup);

		rbCream = new JRadioButton("휘핑크림 추가");
		rbCream.setSize(180,22);
		rbCream.setLocation(36+400+35,125+50+50+52+54);
		rbCream.setBackground(new Color(234,199,132));
		rbCream.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		rbCream.addItemListener(this);
		p_body.add(rbCream);
		
		JLabel lblQtty = new JLabel("수량 ");
		lblQtty.setSize(75, 22);
		lblQtty.setLocation(36+400+35, 125+50+50+52+54+50);
		lblQtty.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		p_body.add(lblQtty);
		
		SpinnerNumberModel numberModelQtty = new SpinnerNumberModel(1, 1, 10, 1);
		spnQtty = new JSpinner(numberModelQtty);
		spnQtty.setSize(50, 25);
		spnQtty.setLocation(36+400+35+50, 125+50+50+52+54+50);
		spnQtty.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p_body.add(spnQtty);

		JLabel lblSum = new JLabel("총 금액");
		lblSum.setSize(106, 32);
		lblSum.setLocation(36+400+35+93, 750-158-70);
		lblSum.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		p_body.add(lblSum);
		
		JLabel lblSumData = new JLabel(CommonPanel.toAddCommaAtPrice(iMenuPrice)+"원");
		lblSumData.setSize(145, 38);
		lblSumData.setLocation(36+400+35+93+106+50, 750-158-74);
		lblSumData.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		p_body.add(lblSumData);
		
		btnToCart = new JButton(new ImageIcon("images/cart/Btn_Cart_EnabledTrue.png"));
		btnToCart.setSize(183, 50);
		btnToCart.setLocation(1050-213, 750-158-80);
		btnToCart.addActionListener(this);
		btnToCart.addMouseListener(this);
		p_body.add(btnToCart);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnToCart) 
		{
			if(rbStatus[0].isSelected()==false&&rbStatus[1].isSelected()==false)
			{
				JOptionPane.showMessageDialog(this, "hot/ice 옵션을 선택해주세요", "옵션 미선택", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(rbPacking[0].isSelected()==false&&rbPacking[1].isSelected()==false)
			{
				JOptionPane.showMessageDialog(this, "테이크아웃/매장컵 옵션을 선택해주세요", "옵션 미선택", JOptionPane.ERROR_MESSAGE);
				return;
			}	
			if(rbSize[0].isSelected()==false&&rbSize[1].isSelected()==false&&rbSize[2].isSelected()==false)
			{
				JOptionPane.showMessageDialog(this, "컵 사이즈 옵션을 선택해주세요", "옵션 미선택", JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			
			paraSyrupName =  combo.getSelectedItem().toString();
			paraSyrupNum =  Integer.parseInt(spnSyrup.getValue().toString());
			paraQtty = Integer.parseInt(spnQtty.getValue().toString());
			
			System.out.println(paraStatus+paraPacking+paraSize+paraSyrupName+paraSyrupNum+paraCream+paraQtty);
			
			insertCartDataToDB();
			CommonPanel.redraw(new Cart());
		}
		
	}

	private void insertCartDataToDB() {
		ResultSet rs = null;
		
		int cartid =-1;
		rs = DB.getResult("select max(cartid) from cart");
		try {
			if(rs.next()) 
			{
				cartid = rs.getInt(1)+1;
			}else 
			{
				cartid = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally 
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DB.executeSQL("insert into cart(cartid,usersid,menuid,storeid,cartquantity,cartstatus,cartpacking,"
				+ "cartsize,cartsyrup,cartsyrupnum,cartwhippedcream,cartidissamecart) values "
				+ "("+cartid+",'"+usersid+"',"+iMenuid+","+storeid+","+paraQtty+",'"+paraStatus+"','"+paraPacking+"','"
				+ paraSize+"','"+paraSyrupName+"',"+paraSyrupNum+",'"+paraCream+"',"+iIsSameCart+")");
		try {
			DB.conn.commit();
		} catch (SQLException e) {
			System.out.println("menuinfo: commit 에러");
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==btnToCart) btnToCart.setIcon(new ImageIcon("images/cart/Btn_Cart_Pressed.png"));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==btnToCart) btnToCart.setIcon(new ImageIcon("images/cart/Btn_Cart_Rollover.png"));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==btnToCart) btnToCart.setIcon(new ImageIcon("images/cart/Btn_Cart_Rollover.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==btnToCart) btnToCart.setIcon(new ImageIcon("images/cart/Btn_Cart_EnabledTrue.png"));
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==rbStatus[0]) 
		{
			if(rbStatus[0].isSelected()) paraStatus = rbStatus[0].getText();
			else paraStatus = "none";
		}else if(e.getSource()==rbStatus[1]) 
		{
			if(rbStatus[1].isSelected()) paraStatus = rbStatus[1].getText();
			else paraStatus = "none";
		}else if(e.getSource()==rbPacking[0]) 
		{
			if(rbPacking[0].isSelected()) paraPacking = rbPacking[0].getText();
			else paraPacking = "none";
		}else if(e.getSource()==rbPacking[1]) 
		{
			if(rbPacking[1].isSelected()) paraPacking = rbPacking[1].getText();
			else paraPacking = "none";
		}else if(e.getSource()==rbSize[0]) 
		{
			if(rbSize[0].isSelected()) paraSize = rbSize[0].getText();
			else paraSize = "none";
		}else if(e.getSource()==rbSize[1]) 
		{
			if(rbSize[1].isSelected()) paraSize = rbSize[1].getText();
			else paraSize = "none";
		}else if(e.getSource()==rbSize[2]) 
		{
			if(rbSize[2].isSelected()) paraSize = rbSize[2].getText();
			else paraSize = "none";
		}else if(e.getSource()==rbCream) 
		{
			if(rbCream.isSelected()) paraCream = "휘핑크림";
			else paraCream = " ";
		}
	}
}
