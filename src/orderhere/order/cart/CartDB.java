package orderhere.order.cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import orderhere.order.db.DB;

public class CartDB {
	
	private String usersid = "aa1234";
	private int cartnum;
	private CartDBData[] cdbdata;
	private ResultSet rs;
	private ResultSet rsCart;
	private ResultSet rsMenu;
	
	public CartDB() 
	{
		DB.init();
		//장바구니에 담은 메뉴 갯수
		//ResultSet rs = DB.getResult("select menuid from cart where usersid='jj234'");
		rs = DB.getResult("select count(*) from cart where usersid='"+usersid+"'");
		try {
			if(rs.next()) 
			{
				cartnum = rs.getInt(1);
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("주문한 메뉴가 없습니다.");
			e.printStackTrace();
		}
		
		cdbdata = new CartDBData[cartnum];
		//메뉴 정보 받아옴.
		rsCart = DB.getResult("select menuid from cart where usersid='"+usersid+"' ORDER BY cartid asc");
		try {
			for (int i = 0; i < cartnum; i++) {
				cdbdata[i] = new CartDBData();
				if(rsCart.next()) {
					cdbdata[i].setMenuid(rsCart.getInt(1));
					rsMenu = DB.getResult("select * from menu where menuid="+rsCart.getInt(1));
					rsMenu.next();
					cdbdata[i].setMenuimage(rsMenu.getString("menuimage"));
					cdbdata[i].setMenuname(rsMenu.getString("menuname"));
					cdbdata[i].setMenuprice(rsMenu.getInt("menuprice"));
					cdbdata[i].setMenucategory(rsMenu.getString("menucategory"));
					rsMenu.close();
				}
			}
			rsCart.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//옵션 정보 받아옴
		rsCart = DB.getResult("select * from cart where usersid='"+usersid+"' ORDER BY cartid asc");
		try {
			for (int i = 0; i < cartnum; i++) {
								
				if(rsCart.next()) 
				{
					cdbdata[i].setOptionQuantity(rsCart.getInt("CARTQUANTITY"));
					cdbdata[i].setOptionStatus(rsCart.getString("CARTSTATUS"));
					cdbdata[i].setOptionPacking(rsCart.getString("CARTPACKING"));
					cdbdata[i].setOptionSize(rsCart.getString("CARTSIZE"));
					cdbdata[i].setOptionSyrup(rsCart.getString("CARTSYRUP"));
					cdbdata[i].setOpionSyrupNum(rsCart.getInt("CARTSYRUPNUM"));
					cdbdata[i].setOptionWhippedcream(rsCart.getString("CARTWHIPPEDCREAM"));
				}
			}
			rsCart.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCartAll(String usersid) 
	{
		try {
			DB.executeSQL("delete from cart where usersid='"+usersid+"'");
			DB.conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteCart(String usersid,int menuid) 
	{
		try {
			DB.executeSQL("delete from cart where usersid='"+usersid+"' and menuid="+menuid);
			DB.conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCartnum() {
		return cartnum;
	}

	public CartDBData[] getCdbdata() {
		return cdbdata;
	}

	public ResultSet getRs() {
		return rs;
	}
}
