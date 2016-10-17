package ticketServer.packet;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuPacket extends TicketPacket{
	private static final long serialVersionUID = 4L;
	private ArrayList<String> menuList;
	
	public MenuPacket(ArrayList<String> menuList) {
		this.menuList = menuList;
	}
	
	public ArrayList<String> getMenuList() {
		return menuList;
	}
	
	public void setMenuList(ArrayList<String> menuList) {
		this.menuList = menuList;
	}

	@Override
	public void result() {
		if(!menuList.isEmpty()){
			Iterator<String> iterator = menuList.iterator();
			while(iterator.hasNext()){
				String data = iterator.next();
				System.out.println(data);
			}
		}
	}
}
