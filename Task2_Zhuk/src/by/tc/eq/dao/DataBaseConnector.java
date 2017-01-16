package by.tc.eq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.tc.eq.dao.exception.DAOException;

public class DataBaseConnector {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/shop";
	private static final String login = "root";
	private static final String password = "";
	private static Connection con = null;

	DataBaseConnector() throws DAOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException("Driver is not available", e);
		}
	}

	public static Connection getConnection() throws DAOException {
		try {
			con = DriverManager.getConnection(url, login, password);
			if (con == null) {
				throw new DAOException("Connection is not established");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return con;
	}

}
