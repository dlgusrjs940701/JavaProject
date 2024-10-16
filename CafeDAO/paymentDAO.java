package CafeDAO;

public class paymentDAO extends DB {

	public static paymentDAO paymentdao = null;

	public static paymentDAO getinstance() {
		if (paymentdao == null) {
			paymentdao = new paymentDAO();
		}
		return paymentdao;

	}
}
