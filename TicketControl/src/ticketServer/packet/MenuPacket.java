package ticketServer.packet;

import java.util.ArrayList;
import java.util.Iterator;

import ticketServer.dao.Menu;

public class MenuPacket extends TicketPacket{
	private static final long serialVersionUID = 4L;
	private ArrayList<Menu []> menuList;
	
	public MenuPacket() {
		this(null);
	}
	
	public MenuPacket(ArrayList<Menu []> menuList) {
		this.menuList = menuList;
	}
	
	public ArrayList<Menu []> getMenuList() {
		return menuList;
	}
	
	public void setMenuList(ArrayList<Menu []> menuList) {
		this.menuList = menuList;
	}

	@Override
	public void result() {
		System.out.println("메뉴패킷 수신완료");
//		if (!menuList.isEmpty()) {
//			Iterator<Menu[]> iterator = menuList.iterator();
//			while (iterator.hasNext()) {
//				Menu[] array = iterator.next();
//				for (Menu menu : array) {
//
//					System.out.println(menu.getNum() + " " + menu.getInformation_date() + " " + menu.getMenu_type()
//							+ " " + menu.getRice() + " " + menu.getSidedish1() + " " + menu.getSidedish2() + " "
//							+ menu.getSidedish3() + " " + menu.getImage() + " ");
//				}
//			}
//		}
	}
}
