package orderhere.order;

import javax.swing.JFrame;

import orderhere.order.cart.Cart;
import orderhere.order.payment.Payment;

//import orderhere.order.cart.Cart;

public class Test {
	
	private static JFrame activatedFrame;
	
	public static void main(String[] args) {
		
//		new Cart();
//		new OrderList();
		activatedFrame = new Cart();
//		new Payment();
//		new OrderDetails();
		System.out.println("     ");
	}

	public static JFrame getActivatedFrame() {
		return activatedFrame;
	}

	public static void setActivatedFrame(JFrame activatedFrame) {
		Test.activatedFrame = activatedFrame;
	}

	
}
