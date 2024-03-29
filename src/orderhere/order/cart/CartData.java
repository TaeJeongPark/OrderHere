package orderhere.order.cart;

public class CartData {

	private int[] cartid;
	private String usersid;
	private int storeid;
	private String[] sarrMenuNames;
	private String[] sarrMenuOptions;
	private String[] sarrMenuCartQuantities;
	private String[] sarrMenuPrice;
	private String sReservedTime;
	private int iSumPrice;
	private int iIsReserved = 0;
	private int cartnum = 0;
	private int[] cartidisSameCart;
	
	public CartData(int cartnum) 
	{
		this.cartnum = cartnum;
		
		cartid = new int[cartnum];
		sarrMenuNames = new String[cartnum];
		sarrMenuOptions = new String[cartnum];
		sarrMenuCartQuantities = new String[cartnum];
		sarrMenuPrice = new String[cartnum];
		cartidisSameCart = new int[cartnum];
		
		for(int i=0; i<cartnum; i++)
		{
			cartid[i] = 0;
			sarrMenuNames[i] = new String();
			sarrMenuOptions[i] = new String();
			sarrMenuCartQuantities[i] = new String();
			sarrMenuPrice[i] = new String();
		}
		
	}

	public void setData(int index,int cartid, String sarrMenuName,
			String sarrMenuOption, String sarrMenuCartQuantity, String sarrMenuPrice,int caridisSameCart) {
		
		this.cartid[index] = cartid;
		sarrMenuNames[index] = sarrMenuName;
		sarrMenuOptions[index] = sarrMenuOption;
		sarrMenuCartQuantities[index] = sarrMenuCartQuantity;
		this.sarrMenuPrice[index] = sarrMenuPrice;
		this.cartidisSameCart[index] = caridisSameCart;
	}

	
	public int[] getCartid() {
		return cartid;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public String getsReservedTime() {
		return sReservedTime;
	}

	public void setsReservedTime(String sReservedTime) {
		this.sReservedTime = sReservedTime;
	}

	public int getiSumPrice() {
		return iSumPrice;
	}

	public void setiSumPrice(int iSumPrice) {
		this.iSumPrice = iSumPrice;
	}

	public int getiIsReserved() {
		return iIsReserved;
	}

	public void setiIsReserved(int iIsReserved) {
		this.iIsReserved = iIsReserved;
	}

	public int getCartnum() {
		return cartnum;
	}

	public String[] getSarrMenuNames() {
		return sarrMenuNames;
	}

	public String[] getSarrMenuOptions() {
		return sarrMenuOptions;
	}

	public String[] getSarrMenuCartQuantities() {
		return sarrMenuCartQuantities;
	}

	public String[] getSarrMenuPrice() {
		return sarrMenuPrice;
	}

	public int[] getCartidisSameCart() {
		return cartidisSameCart;
	}
	
}
