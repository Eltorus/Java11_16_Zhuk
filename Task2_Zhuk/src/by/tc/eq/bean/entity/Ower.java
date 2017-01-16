package by.tc.eq.bean.entity;

import java.util.ArrayList;
import java.util.List;

public class Ower {
	private User user;
	private List<Equipment> equipments = new ArrayList<Equipment>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public void setEquipment(Equipment equipment) {
		this.equipments.add(equipment);
	}
}
