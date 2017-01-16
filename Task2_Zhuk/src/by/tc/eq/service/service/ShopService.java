package by.tc.eq.service.service;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.bean.entity.Ower;
import by.tc.eq.bean.entity.User;
import by.tc.eq.service.exception.ServiceException;

public interface ShopService {
	void deleteEquipment(Equipment equipment) throws ServiceException;

	void addOwer(Ower ower) throws ServiceException;

	void getOwer(User user) throws ServiceException;

	void deleteOwer(Ower ower) throws ServiceException;

	void addEquipment(Equipment equipment) throws ServiceException;
}
