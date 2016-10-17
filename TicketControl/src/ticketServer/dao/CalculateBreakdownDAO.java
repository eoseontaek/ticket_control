package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class CalculateBreakdownDAO extends TicketDAO<CalculateBreakdown>{

	@Override
	public ArrayList<CalculateBreakdown> getAll() {
		connectDB();
		
		sql = "select * from calculate_breakdown;";
		ArrayList<CalculateBreakdown> cbList = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			cbList = new ArrayList<>();
			while(rs.next()){
				int num = rs.getInt("num");
				String purchaser_data = rs.getString("purchaser_data");
				String calculate_days = rs.getString("calculate_days");
				int calculate_amount = rs.getInt("calculate_amount");
				
				CalculateBreakdown cb = new CalculateBreakdown(num, purchaser_data, calculate_days, calculate_amount);
				cbList.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return cbList;
	}

	@Override
	public CalculateBreakdown getData(int index) {
		connectDB();
		
		sql = "select * from calculate_breakdown where num=?;";
		
		CalculateBreakdown cb = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			cb = new CalculateBreakdown();
			rs.next();
			cb.setNum(rs.getInt("num"));
			cb.setPurchaser_data(rs.getString("purchaser_data"));
			cb.setCalculate_days(rs.getString("calculate_days"));
			cb.setCalculate_amount(rs.getInt("calculate_amount"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return cb;
	}

	@Override
	public boolean insertData(CalculateBreakdown t) {
		connectDB();
		
		sql = "insert into calculate_breakdown(num,purchaser_data,calculate_days,calculate_amount) values(?,?,?,?);";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getPurchaser_data());
			pstmt.setString(3, t.getCalculate_days());
			pstmt.setInt(4, t.getCalculate_amount());
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
		
		sql = "delete from calculate_breakdown where num=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);;
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

		sql = "delete from calculate_breakdown;";
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
	public boolean updateData(CalculateBreakdown t) {
		connectDB();
		
		sql = "update calculate_breakdown set num=?,purchaser_data=?,calculate_days=?,calculate_amount=?;";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, t.getNum());
			pstmt.setString(2, t.getPurchaser_data());
			pstmt.setString(3, t.getCalculate_days());
			pstmt.setInt(4, t.getCalculate_amount());
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
