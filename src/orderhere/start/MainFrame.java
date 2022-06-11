package orderhere.start;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import orderhere.common.DB;
import orderhere.login.Login;

/**
* @packageName	: orderhere.start
* @fileName		: MainFrame.java
* @author		: TaeJeong Park
* @date			: 2022.06.02
* @description	: 메인 프레임
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2022.06.02        TaeJeong Park       최초 생성
* 2022.06.02        TaeJeong Park       기능 구현 완료
*/
public class MainFrame extends JFrame implements WindowListener {

	private static JFrame mainFrame;
	private JPanel currentPanel;
	
	//로그인 화면
	public MainFrame(String title) {
		
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
//        setLocationRelativeTo(null);
        setSize(1050, 789);
        setResizable(false);
        setLayout(new BorderLayout());
        
        mainFrame = this;
        
        
        //타이틀바 아이콘 설정
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("images/common/logo_titleBar.png");
        setIconImage(img);
        
        
        currentPanel = new Login();
        add(currentPanel);
        
        setVisible(true);
        
        DB.init();	//DB 연결
        
        addWindowListener(this);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		DB.closeDB(DB.conn, DB.stmt);	//DB 연결 종료
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	public static JFrame getMainFrame() {
		
		return mainFrame;
		
	}

	public static void setMainFrame(JFrame mainFrame) {
		
		MainFrame.mainFrame = mainFrame;
		
	}
	
	public JPanel getCurrentPanel() {
		
		return currentPanel;
		
	}

	public void setCurrentPanel(JPanel currentPanel) {
		
		this.currentPanel = currentPanel;
		
	}

}
