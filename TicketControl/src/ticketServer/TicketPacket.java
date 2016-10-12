package ticketServer;

import java.io.Serializable;

public class TicketPacket implements Serializable{

	private static final long serialVersionUID = 1L;

	///// Packet ±¸Á¶ /////////
	private String command;
	private int point;
	
	//////////////////////////
	
	public TicketPacket(String command, int point) {
		this.command = command;
		this.point = point;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}