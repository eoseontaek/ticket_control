package ticketServer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class RealTimeSalesDAO extends TicketDAO<RealTimeSales>{

	@Override
	public ArrayList<RealTimeSales> getAll() {
		
		return null;
	}

	@Override
	public RealTimeSales getData(int index) {
		
		return null;
	}

	@Override
	public boolean insertData(RealTimeSales t) {
		connectDB();
		
		sql = "insert into real_time_sales(purchaser_data,purchaser_time,purchase_amount) values(?,?,?)";
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
		return false;
	}

	@Override
	public boolean delAll() {
		return false;
	}

	@Override
	public boolean updateData(RealTimeSales t) {
		return false;
	}

}
