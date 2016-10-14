package ticketClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.Executors;

import ticketServer.BarcodePacket;
import ticketServer.MenuPacket;
import ticketServer.PointPacket;
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
					System.out.println("[연결완료]");

					receive();
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					// TODO Auto-generated method stub
					System.out.println("[서버통신안됨]");
					if (socketChannel.isOpen()) {
						stopClient();
					}
				}
			});

		} catch (IOException e) {
		}
	}
	
	void stopClient() {
		System.out.println("[연결끊음]");

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
				TicketPacket packet = (TicketPacket) TicketSerialize.deserialize(bytes);

				if (packet != null) {
					packet.result();
				}
				else {
					System.out.println("Warning!!  packet이 null입니다.");
				}
				
				ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
				socketChannel.read(byteBuffer, byteBuffer, this);
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("[서버통신안됨]");
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
//				System.out.println("[보내기 완료]");
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				System.out.println("[서버통신안됨]");
				stopClient();
			}
		});
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		TicketClient client = new TicketClient();
		client.startClient();
		
		while(true){
			boolean bexit = false;
			System.out.println("1. point");
			System.out.println("2. barcode");
			System.out.println("3. menu");
			System.out.println("0. exit");
			System.out.print(">> ");
			int data = scanner.nextInt();
			
			switch(data){
			case 0:
				bexit = true;
				break;
			case 1:
				client.send(new PointPacket(10000));
				break;
			case 2:
				client.send(new BarcodePacket(null));
				break;
			case 3:
				client.send(new MenuPacket(null));
				break;
			}
			
			if(bexit == true) break;
			System.out.println();
		}
		
		client.stopClient();
		
	}
}
