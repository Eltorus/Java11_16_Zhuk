package by.tc.eq.bean.requestimpl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.entity.Good;
import by.tc.eq.bean.entity.User;

public class RentEquipmentRequest extends Request {
	private Good good;
	private User user;

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
