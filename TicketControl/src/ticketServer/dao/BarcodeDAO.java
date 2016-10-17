package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BarcodeDAO extends TicketDAO<Barcode>{

	public boolean insertBarcode(Barcode t) {
		connectDB();
		sql = "insert into barcode_data(barcode,use_ox) values(?,?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, t.getBarcode());
			pstmt.setInt(2, t.getUse_ox());
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true;
	}
	
	public boolean delBarcode(String barcode){
		connectDB();
		sql = "delete from barcode_data where barcode=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, barcode);
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
		return true;
	}
	
	public boolean delAllBarcode(){
		connectDB();
		sql = "delete from barcode_data;";
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

	
	public int getUseOX(String barcode){
		connectDB();
		
		sql = "select * from barcode_data where barcode=?;";
		int ox = -1;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, barcode);
			rs = pstmt.executeQuery();
			
			rs.next();
			ox = rs.getInt("use_ox");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			closeDB();
		}
		return ox;
	}
	
	public Map<String,Integer> getAllBarcode(){
		connectDB();
		sql = "select * from barcode_data;";
		Map<String, Integer> map = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			map = new ConcurrentHashMap<>();
			
			while(rs.next()){
				String barcode = rs.getString("barcode");
				int use_ox = rs.getInt("use_ox");
				map.put(barcode, use_ox);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return map;
	}

	@Override
	public ArrayList<Barcode> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Barcode getData(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertData(Barcode t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Barcode t) {
		// TODO Auto-generated method stub
		return false;
	}
}
