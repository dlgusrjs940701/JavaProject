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
		addMenu(new menuDTO(4, "포도에이드", 3500, "에이드"));
		addMenu(new menuDTO(5, "초코케이크", 4000, "디저트"));
		addMenu(new menuDTO(6, "에스프레소", 3000, "커피"));
		addMenu(new menuDTO(7, "카페라떼", 3500, "커피"));
		addMenu(new menuDTO(8, "카푸치노", 2500, "커피"));
		addMenu(new menuDTO(9, "카라멜", 4000, "논커피"));
		addMenu(new menuDTO(10, "달고나라떼", 4000, "논커피"));
		addMenu(new menuDTO(11, "옥수수라떼", 4000, "논커피"));
		addMenu(new menuDTO(12, "자몽에이드", 3000, "에이드"));
		addMenu(new menuDTO(13, "파인에이드", 3500, "에이드"));
		addMenu(new menuDTO(14, "아이스티", 2500, "에이드"));
		addMenu(new menuDTO(15, "딸기케이크", 4000, "디저트"));
		addMenu(new menuDTO(16, "치즈케이크", 4000, "디저트"));
		addMenu(new menuDTO(17, "카페모카", 3000, "커피"));
		addMenu(new menuDTO(18, "헤이즐넛", 3500, "커피"));
		addMenu(new menuDTO(19, "초코라떼", 3000, "논커피"));
		addMenu(new menuDTO(20, "녹차라떼", 4000, "논커피"));
		addMenu(new menuDTO(21, "고구마라떼", 4000, "논커피"));
		addMenu(new menuDTO(22, "오곡라떼", 4000, "논커피"));
		addMenu(new menuDTO(23, "딸기에이드", 4000, "에이드"));
		addMenu(new menuDTO(24, "그린티라떼", 4000, "논커피"));
		addMenu(new menuDTO(25, "핫초코", 3000, "논커피"));
		addMenu(new menuDTO(26, "레몬티", 3000, "에이드"));
		addMenu(new menuDTO(27, "유자티", 3000, "에이드"));
		addMenu(new menuDTO(28, "청귤티", 3000, "에이드"));
		addMenu(new menuDTO(29, "과일샐러드", 4000, "디저트"));
		addMenu(new menuDTO(30, "유자에이드", 4000, "에이드"));
		addMenu(new menuDTO(31, "사과에이드", 3000, "에이드"));
		addMenu(new menuDTO(32, "메론에이드", 3000, "에이드"));
		addMenu(new menuDTO(33, "키위에이드", 3000, "에이드"));
		addMenu(new menuDTO(34, "시폰케이크", 4000, "디저트"));
		addMenu(new menuDTO(35, "티라미수", 2000, "디저트"));
		addMenu(new menuDTO(36, "무스케이크", 3000, "디저트"));
		addMenu(new menuDTO(37, "버터케이크", 3000, "디저트"));
		addMenu(new menuDTO(38, "휘낭시에", 6000, "디저트"));
		addMenu(new menuDTO(39, "마들렌", 5000, "디저트"));
		addMenu(new menuDTO(40, "소금빵", 2000, "디저트"));

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
