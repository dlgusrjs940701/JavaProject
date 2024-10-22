package CafeDTO;

public class menuDTO {

	private int menu_id = 0;
	private String menu_name = null;
	private int menu_price = 0;
	private String menu_category = null;
	private String image = null;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public menuDTO(int menu_id, String menu_name, int menu_price, String menu_category, String image) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.menu_price = menu_price;
		this.menu_category = menu_category;
		this.image = image;
	}

	public menuDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int string) {
		this.menu_id = string;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}

	public String getMenu_category() {
		return menu_category;
	}

	public void setMenu_category(String menu_category) {
		this.menu_category = menu_category;
	}

}
