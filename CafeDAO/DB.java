package CafeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DB {

	public String username = "gusrjs";
	public String password = "11111111";
	public String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	public String driverName = "oracle.jdbc.driver.OracleDriver";
	public Connection conn = null;

	public DB() {
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			Class.forName(driverName);
			System.out.println("오라클 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected boolean conn() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			System.out.println("커넥션 자원 획득 성공");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	protected void disconn() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("커넥션 자원 해제 성공");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
