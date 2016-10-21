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
	InetAddress local = null;
	
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
		
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
				
		System.out.println("응답 포인트 : " + point);
		
		ClientDAO dao = new ClientDAO();
		dao.DBConnection();
		int selectPoint = Integer.parseInt(dao.ClientSelect(local.getHostAddress()));
		int value = point+selectPoint;
		cvo = new ClientVO(local.getHostAddress(),value);
		dao.ClientUpdate(cvo);
		
		Platform.runLater(()->{
			PointController.instance.getCurrentPoint().setText(value+"");
		});
	};
}