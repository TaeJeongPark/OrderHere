package orderhere.order;

import javax.swing.JFrame;
import javax.swing.JPanel;

import orderhere.order.cart.Cart;
import orderhere.order.orderdetails.OrderList;
import orderhere.order.payment.Payment;
import orderhere.order.pointusage.PointUsage;

//import orderhere.order.cart.Cart;

public class Main {
	
	private static MainFrame mf;

	public static void main(String[] args) {
		
		mf = new MainFrame();
	}

	public static MainFrame getMf() {
		return mf;
	}

	public static void setMf(MainFrame mf) {
		Main.mf = mf;
	}

}
