package emlakburada.dto;

public class EmailMessage {

	private String email;
	private int userId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "EmailMessage [email=" + email + " userId=" + userId +"]";
	}

}
