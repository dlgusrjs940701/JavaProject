package CafeDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import CafeDTO.userDTO;

public class userDAO extends DB {

	public static userDAO userdao = null;

	public static userDAO getinstance() {
		if (userdao == null) {
			userdao = new userDAO();
		}
		return userdao;

	}

	public void add(userDTO udto) {
		// TODO Auto-generated method stub
//		ArrayList<userDTO> users = new ArrayList<>();
		if (conn()) {
			try {
				String sql = "insert into Client values(Client_seq.nextval, ?)";
				PreparedStatement psmt1 = conn.prepareStatement(sql);
//				psmt1.setString(1, udto.getUser_id());
				psmt1.setString(1, udto.getUser_tel());

				int resultInt = psmt1.executeUpdate();
				if (resultInt > 0) {
					conn.commit();
//					users.add(udto);
					System.out.println("유저 등록 완료");
				} else {
					conn.rollback();
					System.out.println("유저 등록 실패");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {

				}
			}
		} else {
			System.out.println("데이터베이스 커넥션 실패");
		}
	}

}
