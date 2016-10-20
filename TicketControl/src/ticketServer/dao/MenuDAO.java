package ticketServer.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MenuDAO extends TicketDAO{
	// 해당 주의 월요일 찾기
	public String today(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		String today = sdf.format(calendar.getTime());
		return today;
	}

	public String searchMonday(String today){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		
		Date date = null;
		try {
			date = sdf.parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		calendar.setTime(date);
		calendar.add(Calendar.DATE, Calendar.MONDAY - day_of_week);
		
		String monday = sdf.format(calendar.getTime());

		return monday;
	}
	
	// day 메뉴
	public Menu [] getMenu(String day){
		Menu [] dayMenu = new Menu[2];
		connectDB();
		
		sql = "select * from menu where information_date='"+ day +"';";
		//System.out.println(sql);
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
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
				dayMenu[cnt++] = new Menu(num, information_date, menu_type, rice, soup, sidedish1, sidedish2, sidedish3, image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return dayMenu;
	}
	
	public boolean delMenu(String day){
		connectDB();
		
		sql = "";
		
		return false;
	}
	
	public List<Menu[]> getWeekMenu(String monday){
		Menu [] dayMenu = new Menu[2];
		List<Menu []> list = Collections.synchronizedList(new ArrayList<>());
//		ArrayList<Menu []> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		String day = monday;
		for(int i = Calendar.MONDAY; i <= Calendar.FRIDAY; i++){
			dayMenu = getMenu(day);
			if(dayMenu == null) 
				continue;
			
			list.add(dayMenu);
			
			Date date = null;
			try {
				date = sdf.parse(day);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			day = sdf.format(calendar.getTime());
		}
		
		return list;
	}
	
	public ArrayList<Menu> getAll() {
		connectDB();
		
		sql = "select * from menu;";
		List<Menu> menuList = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			menuList = Collections.synchronizedList(new ArrayList<>());
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
		return (ArrayList<Menu>) menuList;
	}

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
//			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return menu;
	}

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
