package CafeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CafeDTO.paymentDTO;

public class paymentDAO extends DB {

	public static paymentDAO paymentdao = null;

	public static paymentDAO getinstance() {
		if (paymentdao == null) {
			paymentdao = new paymentDAO();
		}
		return paymentdao;

	}

	// 매출저장
	public void addTotalPay(paymentDTO pdto) {
		paymentDTO pay1 = new paymentDTO();
		if (conn()) {
			try {
				String sql = "insert into payment values(Payment_seq.nextval,?,?,CURRENT_TIMESTAMP,?)";
				PreparedStatement psmt1 = conn.prepareStatement(sql);
				psmt1.setString(1, pdto.getPayment_method());
				psmt1.setString(2, pdto.getAmount());
				psmt1.setString(3, pdto.getStatus());
				int resultInt = psmt1.executeUpdate();
				if (resultInt > 0) {
					conn.commit();
					System.out.println("등록 완료");
				} else {
					conn.rollback();
					System.out.println("등록 실패");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconn();
			}
		}

	}

	public ArrayList<paymentDTO> loadPay() {
		ArrayList<paymentDTO> pay = new ArrayList<>();
		// TODO Auto-generated method stub
		if (conn()) {
			try {
				String sql = "SELECT * from payment ";

				PreparedStatement psmt1 = conn.prepareStatement(sql);
				ResultSet rs = psmt1.executeQuery();
				while (rs.next()) {
					paymentDTO pay1 = new paymentDTO();
					pay1.setPayment_id(rs.getString("payment_id"));
					pay1.setPayment_method(rs.getString("payment_method"));
					pay1.setAmount(rs.getString("amount"));
					pay1.setPayment_time(rs.getString("payment_time"));
					pay1.setStatus(rs.getString("status"));
					pay.add(pay1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconn();
			}
		}
		return pay;
	}
}
