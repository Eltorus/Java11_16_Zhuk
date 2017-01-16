package by.tc.eq.dao.factory;

import by.tc.eq.dao.EquipmentsDAO;
import by.tc.eq.dao.OwerDAO;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.impl.EquipmentsDAOImpl;
import by.tc.eq.dao.impl.OwerDAOImpl;
import by.tc.eq.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();

	private DAOFactory() {
	}

	private UserDAO userDAO = new UserDAOImpl();
	private EquipmentsDAO equipmentsDAO = new EquipmentsDAOImpl();
	private OwerDAO owerDAO = new OwerDAOImpl();

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public EquipmentsDAO getEquipmentsDAO() {
		return equipmentsDAO;
	}

	public OwerDAO getOwerDAO() {
		return owerDAO;
	}

	public static DAOFactory getInstance() {
		return daoFactory;
	}

}
