package by.tr.xml.DAO.factory;

import by.tr.xml.DAO.DAOFile;
import by.tr.xml.DAO.impl.DAOFileImpl;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final DAOFile daofile = new DAOFileImpl();
	
	private DAOFactory(){}

	public static DAOFactory getInstance() {
		return instance;
	}

	public DAOFile getDaofile() {
		return daofile;
	}
}
