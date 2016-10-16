package ticketServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class TicketDAO<T> {
	private static final String jdbcDriver = "com.mysql.jdbc.Driver";
	private static final String jdbcUrl = "jdbc:mysql://70.12.109.93/barcode_scanner";

	Connection connection;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String sql;
	
	public void connectDB(){
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcUrl, "root", "sds902");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 로딩 오류 발생.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection 생성 오류 발생.");
			e.printStackTrace();
		}
	}
	
	public void closeDB(){
		try {
			if (pstmt != null){
				pstmt.close();
			}
			if (connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract ArrayList<T> getAll();
	public abstract T getData(int index);
	public abstract boolean insertData(T t);
	public abstract boolean delData(int index);
	public abstract boolean delAll();
	public abstract boolean updateData(T t);
}
