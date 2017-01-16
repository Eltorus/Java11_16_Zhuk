package by.tc.eq.dao;

import by.tc.eq.bean.entity.Ower;
import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.exception.DAOException;

public interface OwerDAO {
	void addOwer(Ower ower) throws DAOException;

	Ower getOwer(User user) throws DAOException;

	void deleteOwer(Ower ower) throws DAOException;
}
