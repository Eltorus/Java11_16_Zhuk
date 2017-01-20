package by.tc.eq.service.impl;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.bean.entity.Good;
import by.tc.eq.bean.entity.Ower;
import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.EquipmentsDAO;
import by.tc.eq.dao.OwerDAO;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.service.ClientService;
import static by.tc.eq.service.validation.Validation.lineisProper;

public class ClientServiceImpl implements ClientService {
	private DAOFactory factory = DAOFactory.getInstance();// если объявляешь такое поле, то делай его хотя бы final

	@Override
	public void addNewUser(User user) throws ServiceException {
		if (user == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!lineisProper(user.getName()) || !lineisProper(user.getSurname()) || !lineisProper(user.getEmail())
				|| !lineisProper(user.getPassword())) {
			throw new ServiceExceptionEmptyFields();
		}

		UserDAO userFactory = factory.getUserDAO();
		try {
			User getuser = userFactory.getUser(user);
			if (getuser == null) {
				userFactory.addUser(user);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void rentEquipment(User user, Good good) throws ServiceException {
		UserDAO userFactory = factory.getUserDAO();
		if (!lineisProper(user.getEmail()) || !lineisProper(user.getPassword())) {
			throw new ServiceExceptionEmptyFields();
		}
		try {
			user = userFactory.getUser(user);
			if (user == null) {
				throw new ServiceException("Wrong login or password");
			}

			if (good == null) {
				throw new ServiceException();
			}
			if (!lineisProper(good.getTitle())) {
				throw new ServiceExceptionEmptyFields();
			}

			EquipmentsDAO equipmentsFactory = factory.getEquipmentsDAO();
			Equipment eq = equipmentsFactory.getEquipment(good.getTitle());
			if (eq == null) {
				throw new ServiceException("This good doesn't exist");
			}

			OwerDAO owerdao = factory.getOwerDAO();
			Ower ower = owerdao.getOwer(user);
			if (ower != null && ower.getEquipments().size() == 3) {
				throw new ServiceException("You cannot take more than 3 equipments");
			} else {
				ower = new Ower();
				ower.setUser(user);
				ower.setEquipment(eq);
				owerdao.addOwer(ower);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUser(User user) throws ServiceException {
		if (user == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!lineisProper(user.getEmail()) || !lineisProper(user.getPassword())) {
			throw new ServiceExceptionEmptyFields();
		}

		UserDAO userFactory = factory.getUserDAO();
		try {
			User getuser = userFactory.getUser(user);
			if (getuser != null) {
				userFactory.deleteUser(user);
			} else {
				throw new ServiceException("This account doesn't exist");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User signIn(String email, String password) throws ServiceException {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		if (!lineisProper(user.getEmail()) || !lineisProper(user.getPassword())) {
			throw new ServiceExceptionEmptyFields();
		}

		UserDAO userFactory = factory.getUserDAO();
		try {
			user = userFactory.getUser(user);
			if (user == null) {
				throw new ServiceException("wrong login or password");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}
}
