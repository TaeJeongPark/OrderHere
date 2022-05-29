package orderhere.order.cart;

public class CartData {

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
	
	public CartData(int cartnum) 
	{
		this.cartnum = cartnum;
		
		sarrMenuNames = new String[cartnum];
		sarrMenuOptions = new String[cartnum];
		sarrMenuCartQuantities = new String[cartnum];
		sarrMenuPrice = new String[cartnum];
		
		for(int i=0; i<cartnum; i++)
		{
			sarrMenuNames[i] = new String();
			sarrMenuOptions[i] = new String();
			sarrMenuCartQuantities[i] = new String();
			sarrMenuPrice[i] = new String();
		}
		
	}

	public void setData(int index,String sarrMenuName,String sarrMenuOption, String sarrMenuCartQuantity, String sarrMenuPrice) {
		sarrMenuNames[index] = sarrMenuName;
		sarrMenuOptions[index] = sarrMenuOption;
		sarrMenuCartQuantities[index] = sarrMenuCartQuantity;
		this.sarrMenuPrice[index] = sarrMenuPrice;
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
	
}
