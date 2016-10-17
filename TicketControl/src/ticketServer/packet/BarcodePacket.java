package ticketServer.packet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class BarcodePacket extends TicketPacket{
	private static final long serialVersionUID = 3L;
	private String barcodePath;
	
	public BarcodePacket(String barcodePath) {
		this.barcodePath = barcodePath;
	}
	
	public void setBarcodePath(String barcodePath) {
		this.barcodePath = barcodePath;
	}
	
	public String getBarcodePath() {
		return barcodePath;
	}

	@Override
	public void result() {

		FileChannel from = null;
		FileChannel to = null;
		try {
			Path path_from = Paths.get(barcodePath);
			from = FileChannel.open(path_from, EnumSet.of(StandardOpenOption.READ));
			int size = (int)Files.size(path_from);
			Path path_to = Paths.get("D:\\JAVA\\Temp", Thread.currentThread().getName() +"_img.jpg");
			to = FileChannel.open(path_to, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(size);
			
			from.read(byteBuffer);
			byteBuffer.flip();
			to.write(byteBuffer);
			byteBuffer.clear();
			
		} catch (IOException e) {
		} finally {
			try {
				if ((to != null) && to.isOpen()) {
					to.close();
				}
				if ((from != null) && from.isOpen()) {
					from.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
}