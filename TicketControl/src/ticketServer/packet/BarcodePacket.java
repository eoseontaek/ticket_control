package ticketServer.packet;

public class BarcodePacket extends TicketPacket{
	private static final long serialVersionUID = 3L;
	private String barcode;
	
	/// Brocade //////////////////////
	// menu(2) + Date(6) + Count(4) //
	//////////////////////////////////
	
	public BarcodePacket(String barcode) {
		this.barcode = barcode;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@Override
	public void result() {
		System.out.println("바코드 정보 : " + barcode);
	}
	
}