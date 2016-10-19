package ticketClient.DAO;

public class ClientVO {
	String ip;
	int point;

	public ClientVO() {}
	public ClientVO(int point) {
		this.point = point;
	}
	public ClientVO(String ip, int point) {
		this.ip = ip;
		this.point = point;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
