package ticketClient.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
	private final String DB_URL = "jdbc:mysql://70.12.109.110:3306/java";
	private final String DB_ID = "root";
	private final String DB_PW = "sds902";
	
	private Connection con;

	public ClientDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Error");
			e.printStackTrace();
		}
	}
	
	public void DBConnection(){
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try {
			con.close();
			System.out.println("데이터베이스에 연결이 종료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int ClientInsert(ClientVO vo){
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "INSERT IGNORE INTO CLIENT_POINT(IP, POINT)"+ "VALUE(?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getIp());
			pstmt.setInt(2, vo.getPoint());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DAO insert Error");
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int ClientUpdate(ClientVO cvo){
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			String sql = "UPDATE CLIENT_POINT SET POINT=? WHERE IP='"+cvo.getIp()+"'";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cvo.getPoint());
			//pstmt.setString(2, cvo.getIp());
			
			result = pstmt.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DAO Update Error");
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int ClientDelete(){
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "";
			pstmt = con.prepareStatement(sql);

			//pstmt
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("DAO Delete Error");
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public String ClientSelect(String ip){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String clientPoint = null;
		
		try {
			String sql = "SELECT POINT FROM CLIENT_POINT WHERE IP='"+ip+"'";
			
			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, ip);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ClientVO rsClient = new ClientVO();
				rsClient.setIp(rs.getString(1));
				clientPoint = rsClient.getIp();
			}
			
		} catch (Exception e) {
			System.out.println("DAO Select Error");
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return clientPoint;
	}
}
