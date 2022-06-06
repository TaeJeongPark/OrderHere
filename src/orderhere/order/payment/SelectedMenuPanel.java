package orderhere.order.payment;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import orderhere.order.cart.CartData;

public class SelectedMenuPanel extends JPanel {
	
	private JPanel[] pMenuLbl;
	private JLabel[] lblMenunames;
	private JLabel[] lblMenuOptions;
	private JLabel[] lblMenuQuantities;
	private JLabel[] lblMenuPrice;
	private int cartnum;
	private CartData cd;

	public SelectedMenuPanel(int cartnum,CartData cd) 
	{
		this.cartnum = cartnum;
		this.cd = cd;
		
		pMenuLbl = new JPanel[cartnum];
		lblMenunames = new JLabel[cartnum];
		lblMenuOptions = new JLabel[cartnum];
		lblMenuQuantities = new JLabel[cartnum];
		lblMenuPrice = new JLabel[cartnum];
		
		setLayout(null);
		JLabel lblSelectedMenuTitle = new JLabel("담은 메뉴");
		lblSelectedMenuTitle.setSize(138,30);
		lblSelectedMenuTitle.setLocation(352, 13);
		lblSelectedMenuTitle.setFont(new Font("맑은 고딕",Font.BOLD,22));
		add(lblSelectedMenuTitle);
		
		addMenuLabelPanel();
	}

	private void addMenuLabelPanel() {
		for (int i = 0; i < cartnum; i++) {
			pMenuLbl[i] = new JPanel();
			pMenuLbl[i].setLayout(null);
			pMenuLbl[i].setSize(797,53);
			pMenuLbl[i].setLocation(0, 53+53*i);
			pMenuLbl[i].setBackground(Color.WHITE);
//			pMenuLbl[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			add(pMenuLbl[i]);
			
			lblMenunames[i] = new JLabel();
			lblMenunames[i].setText(cd.getSarrMenuNames()[i]);
			lblMenunames[i].setSize(350,25);
			lblMenunames[i].setLocation(20, 5);
			lblMenunames[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
			pMenuLbl[i].add(lblMenunames[i]);

			lblMenuOptions[i] = new JLabel();
			lblMenuOptions[i].setText(cd.getSarrMenuOptions()[i]);
			lblMenuOptions[i].setSize(719,20);
			lblMenuOptions[i].setLocation(20,9+20);
			lblMenuOptions[i].setForeground(new Color(83,119,245));
			lblMenuOptions[i].setFont(new Font("맑은 고딕",Font.PLAIN,14));
			pMenuLbl[i].add(lblMenuOptions[i]);		
					
			lblMenuQuantities[i] = new JLabel();
			lblMenuQuantities[i].setText(cd.getSarrMenuCartQuantities()[i]);
			lblMenuQuantities[i].setSize(75,27);
			lblMenuQuantities[i].setLocation(409,5);
			lblMenuQuantities[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
			pMenuLbl[i].add(lblMenuQuantities[i]);
			
			lblMenuPrice[i] = new JLabel();
			lblMenuPrice[i].setText(cd.getSarrMenuPrice()[i]);
			lblMenuPrice[i].setSize(110, 27);
			lblMenuPrice[i].setLocation(693, 5);
			lblMenuPrice[i].setFont(new Font("맑은 고딕",Font.BOLD,18));
			pMenuLbl[i].add(lblMenuPrice[i]);
		}
	}
	
}
