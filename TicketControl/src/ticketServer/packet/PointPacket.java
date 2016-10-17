package ticketServer.packet;

public class PointPacket extends TicketPacket{
	private static final long serialVersionUID = 2L;
	private int point;
	
	public PointPacket(int point) {
		this.point = point;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public void result(){
		System.out.println(getClass().getName() + " : " + point);
	};
}