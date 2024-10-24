package CafeDTO;

public class paymentDTO {

	private String payment_id = null;
	private String payment_method = null;
	private String amount = null;
	private String payment_time = null;
	private String status = null;
	private String user_id = null;
	private String user_tel = null;
	

//	public paymentDTO(String Payment_method, String Amount, String Status) {
//		this.payment_method = Payment_method;
//		this.amount = Amount;
//		this.status = Status;
//	}


	
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_tel() {
		// TODO Auto-generated method stub
		return user_tel;
	}

}
