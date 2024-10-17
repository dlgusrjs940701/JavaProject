package CafeDTO;

public class userDTO {
	private String user_id = null;
	private String user_tel = null;

	public userDTO(String user_tel) {
		this.user_tel = user_tel; // 전화번호만 받도록 수정
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
}
