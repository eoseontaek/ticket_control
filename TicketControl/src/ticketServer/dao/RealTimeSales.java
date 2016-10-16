package ticketServer.dao;

public class RealTimeSales {
	private int num;	// 인데스
	private String purchaser_data;	// 구매자 정보
	private String purchaser_time;	// 구매시간
	private int purchase_amount;	// 구매금액
	
	public RealTimeSales() {
		this(0, null, null, 0);
	}
	
	public RealTimeSales(int num, String purchaser_data, String purchaser_time, int purchase_amount) {
		this.num = num;
		this.purchaser_data = purchaser_data;
		this.purchaser_time = purchaser_time;
		this.purchase_amount = purchase_amount;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPurchaser_data() {
		return purchaser_data;
	}
	public void setPurchaser_data(String purchaser_data) {
		this.purchaser_data = purchaser_data;
	}
	public String getPurchaser_time() {
		return purchaser_time;
	}
	public void setPurchaser_time(String purchaser_time) {
		this.purchaser_time = purchaser_time;
	}
	public int getPurchase_amount() {
		return purchase_amount;
	}
	public void setPurchase_amount(int purchase_amount) {
		this.purchase_amount = purchase_amount;
	}
}
