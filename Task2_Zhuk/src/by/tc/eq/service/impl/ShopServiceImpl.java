package by.tc.eq.service.impl;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.bean.entity.Ower;
import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.EquipmentsDAO;
import by.tc.eq.dao.OwerDAO;
import by.tc.eq.dao.exception.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.exception.ServiceExceptionEmptyFields;
import by.tc.eq.service.service.ShopService;
import static by.tc.eq.service.validation.Validation.lineisProper;
import static by.tc.eq.service.validation.Validation.intNumisProper;

public class ShopServiceImpl implements ShopService {
	private DAOFactory factory = DAOFactory.getInstance();

	@Override
	public void addEquipment(Equipment equipment) throws ServiceException {
		if (equipment == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!lineisProper(equipment.getTitle()) || !lineisProper(equipment.getCategory()) || equipment.getPrice() == 0
				|| !intNumisProper(equipment.getAmount())) {
			throw new ServiceExceptionEmptyFields("fields are null");
		}

		try {
			EquipmentsDAO equipdao = factory.getEquipmentsDAO();
			Equipment check_if_eq_exist = equipdao.getEquipment(equipment.getTitle());
			if (check_if_eq_exist == null) {
				equipdao.addEquipment(equipment);
			} else {
				throw new ServiceException("This equipment already exists");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteEquipment(Equipment equipment) throws ServiceException {
		if (equipment == null) {
			throw new ServiceException("object must be not equals null");
		}
		if (!lineisProper(equipment.getTitle())) {
			throw new ServiceExceptionEmptyFields("fields are null");
		}

		try {
			EquipmentsDAO equipdao = factory.getEquipmentsDAO();
			equipdao.deleteEquipment(equipment);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void addOwer(Ower ower) throws ServiceException {
		if (ower == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!intNumisProper(ower.getUser().getId()) || ower.getEquipments().isEmpty()) {
			throw new ServiceExceptionEmptyFields("fields are null");
		}

		try {
			OwerDAO owerDAO = factory.getOwerDAO();
			owerDAO.addOwer(ower);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void getOwer(User user) throws ServiceException {
		if (user == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!intNumisProper(user.getId())) {
			throw new ServiceExceptionEmptyFields("fields are null");
		}

		try {
			OwerDAO owerDAO = factory.getOwerDAO();
			Ower ower = owerDAO.getOwer(user);
			if (ower == null) {
				throw new ServiceException("this user has no rented equipments");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteOwer(Ower ower) throws ServiceException {
		if (ower == null) {
			throw new ServiceException("object must be not equal null");
		}
		if (!intNumisProper(ower.getUser().getId())) {
			throw new ServiceExceptionEmptyFields("fields are null");
		}
		try {
			OwerDAO owerDAO = factory.getOwerDAO();
			owerDAO.deleteOwer(ower);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
