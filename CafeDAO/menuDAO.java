package CafeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CafeDTO.menuDTO;

public class menuDAO extends DB {

	public static menuDAO menudao = null;

	public static menuDAO getinstance() {
		if (menudao == null) {
			menudao = new menuDAO();
		}
		return menudao;

	}
	
	public ArrayList<menuDTO> getAllMenu(){
		ArrayList<menuDTO> menus = new ArrayList<>();
        String sql = "SELECT menu_id, menu_name, menu_price, menu_category FROM Menu";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                menuDTO menu = new menuDTO();
                menu.setMenu_id(rs.getString("menu_id"));
                menu.setMenu_name(rs.getString("menu_name"));
                menu.setMenu_price(rs.getInt("menu_price"));
                menu.setMenu_category(rs.getString("menu_category"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconn(); // 자원 해제
        }
        return menus;
		
	}
	
	public ArrayList<menuDTO> getCategoryMenu(){
		return null;
		
	}

}
