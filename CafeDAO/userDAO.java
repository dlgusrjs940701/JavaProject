package CafeDAO;

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

	public void add() {
		// TODO Auto-generated method stub
		ArrayList<userDTO> user = new ArrayList<>();
		String sql="insert into Client values(Clent_seq.next_val, ?)";
	}
}
