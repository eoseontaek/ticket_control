package ticketServer.dao;

public class Barcode {
	private String barcode;
	private int use_ox;

	public Barcode() {
		this(null, 0);
	}
	
	public Barcode(String barcode, int use_ox) {
		this.barcode = barcode;
		this.use_ox = use_ox;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getUse_ox() {
		return use_ox;
	}

	public void setUse_ox(int use_ox) {
		this.use_ox = use_ox;
	}
}
