package by.tc.eq.dao;

import java.util.List;

import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.exception.DAOException;

public interface UserDAO {
	void addUser(User user) throws DAOException;

	User getUser(User user) throws DAOException;

	void deleteUser(User user) throws DAOException;

	List<User> getAllUsers() throws DAOException;
}
