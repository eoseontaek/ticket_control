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
	
	public int ClientInsert(){
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "";
			pstmt = con.prepareStatement(sql);
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
			String sql = "UPDATE CLIENT_POINT SET POINT=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cvo.getPoint());
			
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
	
	public int ClientSelect(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int clientPoint=0;
		
		try {
			String sql = "SELECT POINT "+"FROM CLIENT_POINT";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			ClientVO rsClient = new ClientVO();
			rsClient.setPoint(rs.getInt(1));
			clientPoint = rsClient.getPoint();
			
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
