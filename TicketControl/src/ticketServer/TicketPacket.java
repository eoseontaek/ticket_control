package ticketServer;

import java.io.Serializable;

public abstract class TicketPacket implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public abstract void result();
}






