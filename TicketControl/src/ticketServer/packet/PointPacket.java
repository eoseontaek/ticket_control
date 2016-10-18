package ticketServer.packet;

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
		System.out.println("응답 포인트 : " + point);
		
		ClientDAO dao = new ClientDAO();
		dao.DBConnection();
		int value = point+dao.ClientSelect();
		cvo = new ClientVO(value);
		System.out.println(dao.ClientUpdate(cvo));
		
		Platform.runLater(()->{
			PointController.instance.getCurrentPoint().setText(value+"");
		});
		
		
	};
}