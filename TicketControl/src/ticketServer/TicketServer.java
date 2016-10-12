package ticketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;

public class TicketServer {
	private AsynchronousChannelGroup channelGroup;
	private AsynchronousServerSocketChannel serverSocketChannel;
	private List<Client> connections = new Vector<>();
	
	private static final int SERVER_PORT = 6001;
	private static final int BUFFER_SIZE = 1024;
	
	class Client{
		AsynchronousSocketChannel socketChannel;
		
		public Client(AsynchronousSocketChannel socketChannel) {
			this.socketChannel = socketChannel;
			
			receive();
		}
		
		void receive(){ 
			ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
			socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {

					// 수신 데이터 처리 ///////////////////////////////////////////
					attachment.flip();
					Charset charset = Charset.forName("UTF-8");
					String data = charset.decode(attachment).toString();
					
					// 요청 클라이언트로 응답처리 /////////////////////////////////
					Client.this.send(data);
					
					ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
					socketChannel.read(byteBuffer, byteBuffer, this);
				}

				@Override
				public void failed(Throwable arg0, ByteBuffer arg1) {
					try {
						connections.remove(Client.this);
						socketChannel.close();
					} catch (IOException e) {}
				}
			});
		}
		
		void send(String data){ 
			Charset charset = Charset.forName("UTF-8");
			ByteBuffer byteBuffer = charset.encode(data);
			socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {

				@Override
				public void completed(Integer result, Void attachment) {
					
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					try {
						connections.remove(Client.this);
						socketChannel.close();
					} catch (IOException e) {}
				}
			});
			
		}
	}

	public void startServer(){ 
		try {
			channelGroup = AsynchronousChannelGroup.withFixedThreadPool(Runtime.getRuntime().availableProcessors(), Executors.defaultThreadFactory());
			serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
			serverSocketChannel.bind(new InetSocketAddress(SERVER_PORT));
		} catch (IOException e) {
			if (serverSocketChannel.isOpen()){
				stopServer();
				return;
			}
		}
		
		serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

			@Override
			public void completed(AsynchronousSocketChannel socketChannel, Void arg1) {
				
				Client client = new Client(socketChannel);
				connections.add(client);
				
				serverSocketChannel.accept(null, this);
			}

			@Override
			public void failed(Throwable arg0, Void arg1) {
				if (serverSocketChannel.isOpen()){
					stopServer();
				}
			}
		});
		
	}
	
	public void stopServer(){
		if (!connections.isEmpty()){
			connections.clear();
		}
		
		if((channelGroup != null) && (!channelGroup.isShutdown())){
			try {
				channelGroup.shutdownNow();
			} catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		TicketServer ticketServer = new TicketServer();
		
		ticketServer.startServer();
	}
}
