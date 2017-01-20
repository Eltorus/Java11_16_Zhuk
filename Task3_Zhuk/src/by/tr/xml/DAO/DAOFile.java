package by.tr.xml.DAO;

import by.tr.xml.DAO.exception.DAOException;

public interface DAOFile {
	String getLexeme() throws DAOException;

	void setFile(String filename) throws DAOException;

	void close() throws DAOException;
}
