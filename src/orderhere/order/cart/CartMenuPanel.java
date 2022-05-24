package orderhere.order.cart;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CartMenuPanel extends JPanel{
	
	private int imenuid;
	private JLabel lblMenuImage;
	private JLabel lblMenuName;
	private JLabel lblMenuOption;
	private JLabel lblCartQuantity;
	private JLabel lblMenuPrice;
	private int iMenuPrice;
	
	public CartMenuPanel() 
	{
		setLayout(null);
		setSize(608,140);
		setBackground(new Color(255,236,198));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ImageIcon iconMenuImage = new ImageIcon("images/menu/cart/블렌디드/MangoBananaBlended.jpg");
		
		lblMenuImage = new JLabel(iconMenuImage);
		lblMenuImage.setSize(140, 140);
		lblMenuImage.setLocation(0, 0);
		
		lblMenuName = new JLabel("메뉴 이름");
		lblMenuName.setSize(425, 25);
		lblMenuName.setLocation(140+10, 19);
		lblMenuName.setFont(new Font("menuname", Font.PLAIN, 16));
		
		lblMenuOption = new JLabel("옵션"); 
		lblMenuOption.setSize(425, 25);
		lblMenuOption.setLocation(140+10, 19+25);
		lblMenuOption.setFont(new Font("menuoption", Font.PLAIN, 15));
		lblMenuOption.setForeground(new Color(83,119,245));
		
		lblCartQuantity = new JLabel("수량"); 
		lblCartQuantity.setSize(95, 25);
		lblCartQuantity.setLocation(140+10, 19+25+25+7);
		lblCartQuantity.setFont(new Font("cartquantity", Font.PLAIN, 16));
		
		lblMenuPrice = new JLabel("가격"); 
		lblMenuPrice.setSize(144, 25);
		lblMenuPrice.setLocation(140+10, 19+25+25+7+25);
		lblMenuPrice.setFont(new Font("menuprice", Font.PLAIN, 16));
		
		add(lblMenuImage);
		add(lblMenuName);
		add(lblMenuOption);
		add(lblCartQuantity);
		add(lblMenuPrice);
	}
	
	public int getImenuid() {
		return imenuid;
	}

	public void setImenuid(int imenuid) {
		this.imenuid = imenuid;
	}



	public JLabel getLblMenuImage() {
		return lblMenuImage;
	}

	public void setLblMenuImage(JLabel lblMenuImage) {
		this.lblMenuImage = lblMenuImage;
	}

	public JLabel getLblMenuName() {
		return lblMenuName;
	}

	public void setLblMenuName(JLabel lblMenuName) {
		this.lblMenuName = lblMenuName;
	}

	public JLabel getLblMenuOption() {
		return lblMenuOption;
	}

	public void setLblMenuOption(JLabel lblMenuOption) {
		this.lblMenuOption = lblMenuOption;
	}

	public JLabel getLblCartQuantity() {
		return lblCartQuantity;
	}

	public void setLblCartQuantity(JLabel lblCartQuantity) {
		this.lblCartQuantity = lblCartQuantity;
	}

	public JLabel getLblMenuPrice() {
		return lblMenuPrice;
	}

	public void setLblMenuPrice(JLabel lblMenuPrice) {
		this.lblMenuPrice = lblMenuPrice;
	}

	public int getiMenuPrice() {
		return iMenuPrice;
	}

	public void setiMenuPrice(int iMenuPrice) {
		this.iMenuPrice = iMenuPrice;
	}

	
}
