package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO extends TicketDAO<Menu>{

	@Override
	public ArrayList<Menu> getAll() {
		connectDB();
		
		sql = "select * from menu;";
		ArrayList<Menu> menuList = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			menuList = new ArrayList<>();
			while(rs.next()){
				int num = rs.getInt("num");
				String information_date = rs.getString("information_date");
				int menu_type = rs.getInt("menu_type");
				String rice = rs.getString("rice");
				String soup = rs.getString("soup");
				String sidedish1 = rs.getString("sidedish1");
				String sidedish2 = rs.getString("sidedish2");
				String sidedish3 = rs.getString("sidedish3");
				String image = rs.getString("image");
				
				Menu menu = new Menu(num, information_date, menu_type, rice, soup, sidedish1, sidedish2, sidedish3, image);
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return menuList;
	}

	@Override
	public Menu getData(int index) {
		connectDB();
		
		sql = "select * from menu where num=?;";
		Menu menu = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			menu = new Menu();
			rs.next();
			menu.setNum(rs.getInt("num"));
			menu.setInformation_date(rs.getString("information_date"));
			menu.setMenu_type(rs.getInt("menu_type"));
			menu.setRice(rs.getString("rice"));
			menu.setSoup(rs.getString("soup"));
			menu.setSidedish1(rs.getString("sidedish1"));
			menu.setSidedish2(rs.getString("sidedish2"));
			menu.setSidedish3(rs.getString("sidedish3"));
			menu.setImage(rs.getString("image"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return menu;
	}

	@Override
	public boolean insertData(Menu t) {
		connectDB();
		
		sql = "insert into menu(num,information_date,menu_type,rice,soup,sidedish1,sidedish2,sidedish2,image)"
				+ " values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getInformation_date());
			pstmt.setInt(3, t.getMenu_type());
			pstmt.setString(4, t.getRice());
			pstmt.setString(5, t.getSoup());
			pstmt.setString(6, t.getSidedish1());
			pstmt.setString(7, t.getSidedish2());
			pstmt.setString(8, t.getSidedish3());
			pstmt.setString(9, t.getImage());
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true;
	}

	@Override
	public boolean delData(int index) {
		connectDB();
		
		sql = "delete from menu where num=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true; 
	}

	@Override
	public boolean delAll() {
		connectDB();

		sql = "delete from menu;";
		try {
			pstmt = connection.prepareStatement(sql);
			int result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true;
	}

	@Override
	public boolean updateData(Menu t) {
		connectDB();
		
		sql = "update menu set num=?,information_date=?,menu_type=?,rice=?,soup=?,sidedish1=?,sidedish2=?,sidedish2=?,image=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getInformation_date());
			pstmt.setInt(3, t.getMenu_type());
			pstmt.setString(4, t.getRice());
			pstmt.setString(5, t.getSoup());
			pstmt.setString(6, t.getSidedish1());
			pstmt.setString(7, t.getSidedish2());
			pstmt.setString(8, t.getSidedish3());
			pstmt.setString(9, t.getImage());
			
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true;
	}
}
