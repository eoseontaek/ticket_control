package ticketServer.packet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.application.Platform;
import ticketClient.DAO.ClientDAO;
import ticketClient.DAO.ClientVO;
import ticketClient.point.PointController;

public class PointPacket extends TicketPacket{
	private static final long serialVersionUID = 2L;
	private int point;
	
	
	ClientVO cvo;
	
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
		
		InetAddress local = null;
		
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("응답 포인트 : " + point);
		
		ClientDAO dao = new ClientDAO();
		dao.DBConnection();
		int value = point+dao.ClientSelect(local.getHostAddress());
		cvo = new ClientVO(value);
		dao.ClientUpdate(cvo);
		
		Platform.runLater(()->{
			PointController.instance.getCurrentPoint().setText(value+"");
		});
	};
}