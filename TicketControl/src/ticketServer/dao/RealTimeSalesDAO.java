package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RealTimeSalesDAO extends TicketDAO<RealTimeSales>{

	@Override
	public ArrayList<RealTimeSales> getAll() {
		connectDB();
		
		sql = "select * from real_time_sales;";
		
		List<RealTimeSales> rtsList = null;
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rtsList = Collections.synchronizedList(new ArrayList<>());
			
			while(rs.next()){
				int num = rs.getInt("num");
				String purchaser_data = rs.getString("purchaser_data");
				String purchaser_time = rs.getString("purchaser_time");
				int purchase_amount = rs.getInt("purchase_amount");
				
				RealTimeSales rts = new RealTimeSales(num, purchaser_data, purchaser_time, purchase_amount);
				rtsList.add(rts);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		
		return (ArrayList<RealTimeSales>) rtsList;
	}

	@Override
	public RealTimeSales getData(int index) {
		connectDB();

		sql = "select * from real_time_sales where num=?;";
		RealTimeSales rts = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			rs.next();
			rts = new RealTimeSales();
			rts.setNum(rs.getInt("num"));
			rts.setPurchaser_data(rs.getString("purchaser_data"));
			rts.setPurchaser_time(rs.getString("purchaser_time"));
			rts.setPurchase_amount(rs.getInt("purchase_amount"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		
		return rts;
	}

	@Override
	public boolean insertData(RealTimeSales t) {
		connectDB();
		
		sql = "insert into real_time_sales(purchaser_data,purchaser_time,purchase_amount) values(?,?,?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, t.getPurchaser_data());
			pstmt.setString(2, t.getPurchaser_time());
			pstmt.setInt(3, t.getPurchase_amount());
			
			pstmt.executeUpdate();
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
		
		sql = "delete from real_time_sales where num=?;";
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

		sql = "delete from real_time_sales;";
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
	public boolean updateData(RealTimeSales t) {
		connectDB();
		
		sql = "update real_time_sales set num=?,purchaser_data=?,purchaser_time=?,purchase_amount=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getPurchaser_data());
			pstmt.setString(3, t.getPurchaser_time());
			pstmt.setInt(4, t.getPurchase_amount());
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
