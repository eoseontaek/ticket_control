package ticketClient;

import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;

public class TicketClient {
	private AsynchronousChannelGroup channelGroup;
	private AsynchronousSocketChannel socketChannel;
	
	void startClient(){}
	void stopClient(){}
	void receive(){}
	void send(String data){}
}
