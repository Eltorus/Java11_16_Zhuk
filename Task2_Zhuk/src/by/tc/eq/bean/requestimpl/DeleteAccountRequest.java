package by.tc.eq.bean.requestimpl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.entity.User;

public class DeleteAccountRequest extends Request {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
