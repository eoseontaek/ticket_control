package ticketServer;

import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;
import java.util.Vector;

public class TicketServer {
	private AsynchronousChannelGroup channelGroup;
	private AsynchronousServerSocketChannel serverSocketChannel;
	private List<Client> connections = new Vector<>();
	
	class Client{
		AsynchronousSocketChannel socketChannel;
		
		public Client(AsynchronousSocketChannel socketChannel) {
			this.socketChannel = socketChannel;
			
			receive();
		}
		
		void receive(){ }
		void send(String data){ }
	}

	public void startServer(){ }
	public void stopServer(){ }
	
	public static void main(String[] args) {
		TicketServer ticketServer = new TicketServer();
		
		ticketServer.startServer();
	}
}
