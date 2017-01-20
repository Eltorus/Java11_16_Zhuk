package by.tr.xml.DAO.factory;

import by.tr.xml.DAO.DAOFile;
import by.tr.xml.DAO.impl.DAOFileImpl;

public class DAOFactory {
	private final static DAOFile instance = new DAOFileImpl();

	public static DAOFile getInstance() {
		return instance;
	}
}
