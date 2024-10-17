package CafeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CafeDTO.menuDTO;

public class menuDAO extends DB {

	public static menuDAO menudao = null;
	private ArrayList<menuDTO> menuList = new ArrayList<>();

	public static menuDAO getinstance() {
		if (menudao == null) {
			menudao = new menuDAO();
		}
		return menudao;
	}

	public void menuDAO() {
		menuList = new ArrayList<>();
		if (isMenuEmpty()) {
			menuData();

		}
	}

	private boolean isMenuEmpty() {
		if (conn()) {
			String sql = "select count(*) from menu";
			try {
				PreparedStatement psmt1 = conn.prepareStatement(sql);
				ResultSet rs = psmt1.executeQuery();
				if (rs.next()) {
					int count = rs.getInt(1);
					return count == 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconn();
			}
		} else {
			System.out.println("오류");
		}
		return false;
	}

	private void menuData() {
		addMenu(new menuDTO(1, "아메리카노", 2000, "커피"));
		addMenu(new menuDTO(2, "미숫가루", 3000, "논커피"));
		addMenu(new menuDTO(3, "레몬에이드", 3500, "에이드"));
		addMenu(new menuDTO(4, "초코케이크", 4000, "커피"));

	}

	private void addMenu(menuDTO menu) {
		if (conn()) {
			try {
				String sql = "insert into menu values (?,?,?,?)";
				PreparedStatement psmt1 = conn.prepareStatement(sql);
				psmt1.setInt(1, menu.getMenu_id());
				psmt1.setString(2, menu.getMenu_name());
				psmt1.setInt(3, menu.getMenu_price());
				psmt1.setString(4, menu.getMenu_category());
				int resultInt = psmt1.executeUpdate();
				if (resultInt > 0) {
					conn.commit();
					System.out.println("메뉴 등록");
				} else {
					conn.rollback();
					System.out.println("메뉴 등록 실패");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconn();
			}
		} else {
			System.out.println("오류");
		}
	}

	public ArrayList<menuDTO> getAllMenu() {
		ArrayList<menuDTO> menus = new ArrayList<>();
		String sql = "SELECT menu_id, menu_name, menu_price, menu_category FROM Menu";

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				menuDTO menudto = new menuDTO(0, sql, 0, sql);
				menudto.setMenu_id(rs.getInt("menu_id"));
				menudto.setMenu_name(rs.getString("menu_name"));
				menudto.setMenu_price(rs.getInt("menu_price"));
				menudto.setMenu_category(rs.getString("menu_category"));
				menus.add(menudto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 자원 해제
		}
		return menus;

	}

//	public List<menuDTO> getMenuCategory() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<menuDTO> getCategoryMenu(menuDTO mdto) {
		ArrayList<menuDTO> menus = new ArrayList<>();

		if (conn()) {
			try {
				String sql = "SELECT menu_id, menu_name, menu_price from menu where menu_category=?";
				PreparedStatement psmt1 = conn.prepareStatement(sql);
				psmt1.setString(1, mdto.getMenu_category());
				ResultSet rs = psmt1.executeQuery();
				while (rs.next()) {
					menuDTO menu = new menuDTO();
	                menu.setMenu_id(rs.getInt("menu_id"));
	                menu.setMenu_name(rs.getString("menu_name"));
	                menu.setMenu_price(rs.getInt("menu_price"));
	                menu.setMenu_category(mdto.getMenu_category());
	                menus.add(menu);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				disconn();
			}

		}
		return menus;

	}

}
