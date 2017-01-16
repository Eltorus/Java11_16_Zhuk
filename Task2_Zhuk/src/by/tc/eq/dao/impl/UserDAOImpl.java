package by.tc.eq.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.DataBaseConnector;
import by.tc.eq.dao.UserDAO;
import by.tc.eq.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
	private Connection con;

	@Override
	public void addUser(User user) throws DAOException {
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(
					"INSERT INTO `shop`.`clients` (`c_id`, `c_name`, `c_surname`, `c_email`, `c_password`) VALUES (DEFAULT, '"
							+ user.getName() + "', '" + user.getSurname() + "', '" + user.getEmail() + "', '"
							+ user.getPassword() + "')");
			st.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new DAOException("Could not close connection", e);
				}
		}
	}

	@Override
	public User getUser(User user) throws DAOException {
		User resultUser = null;
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT `c_id`, `c_name`, `c_surname` FROM `shop`.`clients` WHERE `c_email`='"
					+ user.getEmail() + "' && `c_password`='" + user.getPassword() + "'");
			while (rs.next()) {
				resultUser = new User();
				resultUser.setId(rs.getInt(1));
				resultUser.setName(rs.getString(2));
				resultUser.setSurname(rs.getString(3));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new DAOException("Could not close connection", e);
				}
		}
		return resultUser;
	}

	@Override
	public void deleteUser(User user) throws DAOException {
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM `shop`.`clients` WHERE `c_email`='" + user.getEmail() + "' && `c_password`='"
					+ user.getPassword() + "'");
			st.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new DAOException("Could not close connection", e);
				}
		}
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		User user = new User();
		List<User> users = new ArrayList<User>();
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT `c_id`, `c_name`, `c_surname`,`c_email` FROM `shop`.`clients`");
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setEmail(rs.getString(4));
				users.add(user);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new DAOException("Could not close connection", e);
				}
		}
		return users;
	}
}
