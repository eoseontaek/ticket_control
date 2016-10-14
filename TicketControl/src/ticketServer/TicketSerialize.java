package ticketServer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TicketSerialize {
	public static byte [] serialize(Object obj){
		byte [] bytes = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(out);
			oos.writeObject(obj);
			oos.flush();
			bytes = out.toByteArray();
		} catch (IOException e) {
		}finally {
			try {
				oos.close();
				out.close();
			} catch (IOException e) {}
		}

		return bytes;
	}
	
	public static Object deserialize(byte[] data) {
		ObjectInputStream ois = null;
		Object obj = null;

		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(data));
			obj = ois.readObject();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}finally {
			try {
				ois.close();
			} catch (IOException e) {}
		}
		return obj;
	}
}
