package login;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class LoginFrame extends JFrame {

	public LoginFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
        setSize(1050, 750);
        setLayout(new BorderLayout());

        setVisible(true);
    }
	
}
