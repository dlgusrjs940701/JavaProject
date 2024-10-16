package CafeDAO;

public class orderDAO extends DB {

	public static orderDAO orderdao = null;

	public static orderDAO getinstance() {
		if (orderdao == null) {
			orderdao = new orderDAO();
		}
		return orderdao;
	}
}
