package orderhere.order.orderdetails;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import orderhere.order.CommonPanel;

public class OrderDetails extends JFrame{

		public OrderDetails() 
		{
			setSize(1050,789);
			setLocation(100,0);//화면 가운데 배치 필요
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(null);
			
			add(new CommonPanel().createTop("ORDER DETAILS"));
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
			
		}
}
