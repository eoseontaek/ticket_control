package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class LogInDAO extends TicketDAO<LogIn>{

	@Override
	public ArrayList<LogIn> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogIn getData(int index) {
		connectDB();
		
		sql = "select * from login where num=?;";
		LogIn login = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			rs.next();
			login = new LogIn();
			login.setNum(rs.getInt("num"));
			login.setId(rs.getString("id"));
			login.setPw(rs.getString("pw"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return login;
	}

	@Override
	public boolean insertData(LogIn t) {
		connectDB();
		
		sql = "insert into login(num,id,pw) values(?,?,?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getId());
			pstmt.setString(3, t.getPw());
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
		sql = "delete from login where num=?;";
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
		sql = "delete from login;";
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
	public boolean updateData(LogIn t) {
		// TODO Auto-generated method stub
		return false;
	}

}
