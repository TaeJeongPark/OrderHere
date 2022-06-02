package orderhere.common;

/**
* @packageName	: orderhere.common
* @fileName		: UsersData.java
* @author		: TaeJeong Park
* @date			: 2022.05.29
* @description	: 로그인 회원 정보를 담고 있는 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.29		TaeJeong Park		최초 생성
* 2022.05.29		TaeJeong Park		기능 구현 완료
*/
public class UsersData {

	private static String usersId;	//회원 아이디
	private static int storeId;		//회원이 선택한 매장의 매장번호
	private static int iIsSameCart;	//동일한 장바구니에 담긴 아이템인지 판별하는 번호
	
	public static String getUsersId() {
		return usersId;
	}
	
	public static void setUsersId(String usersIdIn) {
		usersId = usersIdIn;
	}
	
	public static int getStoreId() {
		return storeId;
	}
	
	public static void setStoreId(int storeIdIn) {
		storeId = storeIdIn;
	}

	public static int getiIsSameCart() {
		return iIsSameCart;
	}

	public static void setiIsSameCart(int iIsSameCart) {
		UsersData.iIsSameCart = iIsSameCart;
	}
	
}
