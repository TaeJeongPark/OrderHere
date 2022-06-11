package orderhere.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orderhere.common.Boilerplate;

/**
* @packageName	: orderhere.main
* @fileName		: FirstMenuListPanel.java
* @author		: TaeJeong Park
* @date			: 2022.06.10
* @description	: 메인화면 메뉴 아이템 패널
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.06.10		TaeJeong Park		최초 생성
* 2022.06.10		TaeJeong Park		레이아웃 구현 완료
* 2022.06.10		TaeJeong Park		기능 구현 완료
*/
public class MenuListPanel extends JPanel {
	
	private static JButton btn;

	public MenuListPanel(String memuName, int memuPrice, String memuImage, String category, Main main) {
		
		setSize(286, 100);
		setBackground(new Color(255, 236, 198));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(null);
		
		
		//메뉴 이름
		JLabel lblMenuName = new JLabel(memuName);
		lblMenuName.setFont(new Font("맑은고딕", Font.BOLD, 15));	//콤보박스 폰트 설정
		lblMenuName.setSize(164, 20);
		lblMenuName.setLocation(99, 12);
		
		add(lblMenuName);
		
		
		//메뉴 가격
		JLabel lblMenuPrice = new JLabel(Boilerplate.setComma(memuPrice) + " 원", JLabel.RIGHT);
		lblMenuPrice.setFont(new Font("맑은고딕", Font.BOLD, 15));	//콤보박스 폰트 설정
		lblMenuPrice.setSize(164, 20);
		lblMenuPrice.setLocation(90, 68);
		
		add(lblMenuPrice);
		
		
		//메뉴 이미지
		JLabel lblMenuImg = new JLabel(new ImageIcon("images/menu/" + category + "/" + memuImage));
		lblMenuImg.setSize(75, 75);
		lblMenuImg.setLocation(12, 12);
		
		add(lblMenuImg);
		
		
		btn = new JButton(new ImageIcon("images/main/Btn_Menu_EnabledTrue.png"));
		btn.setRolloverIcon(new ImageIcon("images/main/Btn_Menu_Rollover.png"));
		btn.setPressedIcon(new ImageIcon("images/main/Btn_Menu_Pressed.png"));
		btn.setName(memuName);
		btn.setFont(new Font(null, Font.PLAIN, 0));
		btn.setSize(286, 100);
		btn.setLocation(0, 0);
		btn.setBorderPainted(false);		//버튼 테두리 설정
		btn.setContentAreaFilled(false);	//버튼 배경 표시 설정
		btn.setFocusPainted(false);			//포커스 테두리 표시 설정
		btn.setOpaque(false);				//투명하게 설정
		btn.addActionListener(main);
		
		add(btn);
		
	}

	public static JButton getBtn() {
		
		return btn;
		
	}
	
}
