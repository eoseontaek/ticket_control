package ticketClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.Executors;

import ticketServer.TicketPacket;
import ticketServer.TicketSerialize;

public class TicketClient {
	private AsynchronousChannelGroup channelGroup;
	private AsynchronousSocketChannel socketChannel;
	
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 6001;
	private static final int BUFFER_SIZE = 1024;
	
	void startClient(){
		try {
			channelGroup = AsynchronousChannelGroup.withFixedThreadPool(Runtime.getRuntime().availableProcessors(),
					Executors.defaultThreadFactory());
			socketChannel = AsynchronousSocketChannel.open(channelGroup);

			socketChannel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), null, new CompletionHandler<Void, Void>() {

				@Override
				public void completed(Void result, Void attachment) {
					System.out.println("[����Ϸ�]");

					receive();
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					// TODO Auto-generated method stub
					System.out.println("[������žȵ�]");
					if (socketChannel.isOpen()) {
						stopClient();
					}
				}
			});

		} catch (IOException e) {
		}
	}
	
	void stopClient() {
		System.out.println("[�������]");

		if ((channelGroup != null) && (channelGroup.isShutdown() == false)) {
			try {
				channelGroup.shutdownNow();
			} catch (IOException e) {
			}
		}
	}
	
	void receive(){
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);

		socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				byte[] bytes = attachment.array();
				TicketPacket obj = (TicketPacket) TicketSerialize.deserialize(bytes);
				System.out.println("[�ޱ�Ϸ�] " + "Command : " + obj.getCommand() + ", Point : " + obj.getPoint());

				ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
				socketChannel.read(byteBuffer, byteBuffer, this);
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("[������žȵ�]");
				stopClient();
			}
		});
	}

	void send(Object obj){
		byte [] data = TicketSerialize.serialize(obj);
		
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		ByteBuffer byteBuffer = buffer.wrap(data, 0, data.length);
		
		socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {

			@Override
			public void completed(Integer result, Void attachment) {
//				System.out.println("[������ �Ϸ�]");
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				System.out.println("[������žȵ�]");
				stopClient();
			}
		});
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		TicketClient client = new TicketClient();
		client.startClient();
		
		while(true){
			System.out.print("intput(exit : 0) >> ");
			int data = scanner.nextInt();
			
			if(data == 0) break;
			
			client.send(new TicketPacket("Command -" + data, data));
			
		}
		
		client.stopClient();
		
	}
}
