package orderhere.order.pointusage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import orderhere.order.CommonPanel;
import orderhere.order.db.DB;

public class PointUsage extends JPanel implements MouseListener{

		private JPanel p_body;
		private String[] sPointDate;
		private String[] sPointStatus;
		private int[] iPoint;		
		private JLabel[] lblPointDate;
		private JLabel[] lblPointStatus;
		private JLabel[] lblPoint;
		private JPanel pList;
		private JPanel[] pListInner;
		private JPanel pListInnerNothing;
		private int iListnum=0;
		private String usersid = "aa1234";
		private JTextField tfFrom;
		private JLabel lblFromTo;
		private JTextField tfTo;
		private JLabel lblSearch;
		private int iCurrentPoint=0;

		public PointUsage() 
		{
			getDataFromDB();
			
			setSize(1050,750);
			setLocation(0,0);//화면 가운데 배치 필요
			setLayout(null);
			
			add(new CommonPanel().createTop("Point Usage"));
			createBody();
			
			setVisible(true);
		}

		private void getDataFromDB() {
			DB.init();
			
			ResultSet rs = DB.getResult("select count(*) from userspoint where usersid='"+usersid +"'");
			try {
				if(rs.next()) iListnum = rs.getInt(1);
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			sPointDate = new String[iListnum];
			sPointStatus  = new String[iListnum];
			iPoint  = new int[iListnum];
			
			lblPointDate = new JLabel[iListnum];
			lblPointStatus = new JLabel[iListnum];
			lblPoint = new JLabel[iListnum];
			
			rs = DB.getResult("select orderdate,pointstatus,point from userspoint where usersid='"+usersid +"' order by pointid desc");
			try {
				for (int i = 0; i < iListnum; i++) {
					
					if(rs.next()) 
					{
						if(i==0) iCurrentPoint = rs.getInt("point");
						sPointDate[i] = rs.getString("orderdate");
						sPointStatus[i] = rs.getString("pointstatus");
						iPoint[i] = rs.getInt("point");
					}
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void createBody() {
			p_body = new JPanel();
			p_body.setSize(1050, 750-158);
			p_body.setLocation(0, 158);
			p_body.setBackground(new Color(234,199,132));
			p_body.setLayout(null);
			
			tfFrom = new JTextField(15);
			tfFrom.setHorizontalAlignment(JTextField.CENTER);
			tfFrom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			tfFrom.setLocation(91, 28);
			tfFrom.setSize(165,39);
			tfFrom.setFont(new Font("맑은 고딕",Font.PLAIN,18));
			tfFrom.setEditable(false);
			p_body.add(tfFrom);
			
			lblFromTo = new JLabel("~");
			lblFromTo.setLocation(91+165+19, 38);
			lblFromTo.setSize(15, 15);
			lblFromTo.setFont(new Font("맑은 고딕",Font.PLAIN,12));
			p_body.add(lblFromTo);
			
			tfTo = new JTextField(15);
			tfTo.setHorizontalAlignment(JTextField.CENTER);
			tfTo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			tfTo.setLocation(91+165+49, 28);
			tfTo.setSize(165,39);
			tfTo.setFont(new Font("맑은 고딕",Font.PLAIN,18));
			tfTo.setEditable(false);
			p_body.add(tfTo);
			
			lblSearch = new JLabel(new ImageIcon("images/orderdetails/Btn_Search_EnabledTrue.png"));
			lblSearch.setBackground(Color.blue);
			lblSearch.setSize(35, 35);
			lblSearch.setLocation(91+165+49+165+9,33);
			lblSearch.addMouseListener(this);
			p_body.add(lblSearch);
			
			JLabel lblExplain = new JLabel("최대 3개월까지 조회 가능합니다.");
			lblExplain.setSize(550, 23);
			lblExplain.setLocation(92, 76);
			lblExplain.setForeground(new Color(81,81,81));
			lblExplain.setFont(new Font("맑은 고딕",Font.PLAIN,15));
			p_body.add(lblExplain);
			
			JLabel lblCurrentPoint = new JLabel("보유 포인트");
			lblCurrentPoint.setSize(117, 26);
			lblCurrentPoint.setLocation(662, 66);
			lblCurrentPoint.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblCurrentPoint);
			
			JLabel lblCurrentPointData = new JLabel(CommonPanel.toAddCommaAtPrice(iCurrentPoint)+" P");
			lblCurrentPointData.setSize(117, 26);
			lblCurrentPointData.setLocation(662+117+48, 66);
			lblCurrentPointData.setFont(new Font("맑은 고딕",Font.BOLD,18));
			p_body.add(lblCurrentPointData);
			
			addOrderTitlePanel();
			
			pList = new JPanel();
			pList.setLayout(null);
			pList.setSize(836, 349);
			pList.setBackground(new Color(255,236,198));
			
			if(iListnum>=8) pList.setPreferredSize(new Dimension(836, 44*iListnum));
			else if(iListnum>=0&&iListnum<8) pList.setPreferredSize(new Dimension(836, 44*7));
			
			addListPanels();
			
			JScrollPane jsp = new JScrollPane(pList, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setSize(855,349);
			jsp.setLocation(92, 149);
			jsp.getVerticalScrollBar().setUnitIncrement(8);
			p_body.add(jsp);
			
			add(p_body);
			
		}

		private void addListPanels() {
			pListInner = new JPanel[iListnum];
			for (int i = 0; i < iListnum; i++) {
				pListInner[i] = new JPanel();
				pListInner[i].setLayout(null);
				pListInner[i].setSize(837,44);
				pListInner[i].setLocation(0, 44*i);
				pListInner[i].setBackground(new Color(255,236,198));
				pListInner[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				
				lblPointDate[i] = new JLabel(sPointDate[i]);
				lblPointDate[i].setSize(147,20);
				lblPointDate[i].setLocation(79+25,9);
				lblPointDate[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblPointDate[i]);
				
				lblPointStatus[i] = new JLabel(sPointStatus[i]);
				lblPointStatus[i].setSize(130,18);
				lblPointStatus[i].setLocation(79+25+147+141+40, 9);
				lblPointStatus[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblPointStatus[i]);
				
				lblPoint[i] = new JLabel(CommonPanel.toAddCommaAtPrice(iPoint[i])+" P");
				lblPoint[i].setSize(146,18);
				lblPoint[i].setLocation(79+25+147+141+40+130+112, 9);
				lblPoint[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
				pListInner[i].add(lblPoint[i]);
				
				pList.add(pListInner[i]);
			}			
		}

		private void addOrderTitlePanel() {
			JPanel pPointTitle = new JPanel();
			pPointTitle.setLayout(null);
			pPointTitle.setSize(854, 44);
			pPointTitle.setLocation(92, 106);
			pPointTitle.setBackground(new Color(255,236,198));
			pPointTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			JLabel lblPointStatusDate = new JLabel("포인트 사용/적립일");
			lblPointStatusDate.setSize(140, 18);
			lblPointStatusDate.setLocation(110, 15);
			lblPointStatusDate.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pPointTitle.add(lblPointStatusDate);
			
			JLabel lblPointStatus = new JLabel("구분");
			lblPointStatus.setSize(110, 18);
			lblPointStatus.setLocation(140+110+143+40, 15);
			lblPointStatus.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pPointTitle.add(lblPointStatus);
			
			JLabel lblPoint = new JLabel("포인트");
			lblPoint.setSize(110, 18);
			lblPoint.setLocation(170+110+143+110+141, 15);
			lblPoint.setFont(new Font("맑은 고딕",Font.BOLD,15));
			pPointTitle.add(lblPoint);
			
			p_body.add(pPointTitle);
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Pressed.png"));
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Rollover.png"));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_Rollover.png"));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==lblSearch) 
			{
				lblSearch.setIcon(new ImageIcon("images/orderdetails/Btn_Search_EnabledTrue.png"));
			}
		}
}
