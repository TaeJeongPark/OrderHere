package orderhere.order;

import javax.swing.JFrame;
import javax.swing.JPanel;

import orderhere.order.cart.Cart;
import orderhere.order.orderdetails.OrderList;
import orderhere.order.pointusage.PointUsage;

public class MainFrame extends JFrame{
	
	private JPanel currentPanel;
	
	public MainFrame()
	{
		setSize(1050,789);
		setLocation(100,0);//화면 가운데 배치 필요
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		currentPanel = new Cart();
//		currentPanel = new OrderList();
//		currentPanel = new PointUsage();
		add(currentPanel);

		setVisible(true);
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	public void setCurrentPanel(JPanel currentPanel) {
		this.currentPanel = currentPanel;
	}


}
