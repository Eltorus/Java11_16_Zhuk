package by.tc.eq.bean.requestimpl;

import by.tc.eq.bean.Request;
import by.tc.eq.bean.entity.Equipment;

public class DeleteEquipmentRequest extends Request {
	private Equipment equipment;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
