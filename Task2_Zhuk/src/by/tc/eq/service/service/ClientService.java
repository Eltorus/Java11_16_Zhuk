package by.tc.eq.service.service;

import by.tc.eq.bean.entity.Good;
import by.tc.eq.bean.entity.User;
import by.tc.eq.service.exception.ServiceException;

public interface ClientService {
	void addNewUser(User user) throws ServiceException;

	void deleteUser(User user) throws ServiceException;

	User signIn(String email, String password) throws ServiceException;

	void rentEquipment(User user, Good good) throws ServiceException;
}
