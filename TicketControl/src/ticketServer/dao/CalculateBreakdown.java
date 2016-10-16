package ticketServer.dao;

public class CalculateBreakdown {
	private int num;
	private String purchaser_data;
	private String calculate_days;
	private int calculate_amount;
	
	public CalculateBreakdown() {
		this(0, null, null, 0);
	}

	public CalculateBreakdown(int num, String purchaser_data, String calculate_days, int calculate_amount) {
		this.num = num;
		this.purchaser_data = purchaser_data;
		this.calculate_days = calculate_days;
		this.calculate_amount = calculate_amount;
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
	public String getCalculate_days() {
		return calculate_days;
	}
	public void setCalculate_days(String calculate_days) {
		this.calculate_days = calculate_days;
	}
	public int getCalculate_amount() {
		return calculate_amount;
	}
	public void setCalculate_amount(int calculate_amount) {
		this.calculate_amount = calculate_amount;
	}
}
