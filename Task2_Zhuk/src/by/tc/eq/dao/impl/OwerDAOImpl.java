package by.tc.eq.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.bean.entity.Ower;
import by.tc.eq.bean.entity.User;
import by.tc.eq.dao.DataBaseConnector;
import by.tc.eq.dao.OwerDAO;
import by.tc.eq.dao.exception.DAOException;

public class OwerDAOImpl implements OwerDAO {
	private Connection con;// вынеся сюда это поле ты сделал класс непригодным для использования в многопоточных приложениях

	@Override
	public void addOwer(Ower ower) throws DAOException {
		try {
			con = DataBaseConnector.getConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			Iterator<Equipment> itr = ower.getEquipments().iterator();
			while (itr.hasNext()) {
				Equipment eq = itr.next();
				st.executeUpdate("INSERT INTO `shop`.`rented_units` (`r_id`, `client_id`,`eq_id`) VALUES (DEFAULT, "
						+ ower.getUser().getId() + "," + eq.getId() + ")");// мы PreparedStatement что, не учили?
				// или кто-то не помнит, когда следует использовать statement, а когда preparedstatement
				st.executeUpdate("UPDATE `shop`.`equipments` SET `e_amount` = `e_amount`-1 where `e_id`=" + eq.getId());
				con.commit();
			}
			st.close();// все close - всегда в finally
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e);
			}
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new DAOException("Could not close connection", e);// сам же finalle крайне редко сам выбрасывает исключения
					// лог здесь нужен, а не выброс исключения
				}
		}
	}

	@Override
	public Ower getOwer(User user) throws DAOException {
		Ower ower = null;
		List<Equipment> equipments = new ArrayList<Equipment>();
		Equipment eq = new Equipment();

		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(
					"SELECT `e_id`, `category_id`, `e_title`, `e_price` FROM `shop`.`equipments` AS `e` INNER JOIN `shop`.`rented_units` AS `r` ON `e`.`e_id` = `r`.`eq_id` WHERE `r`.`client_id` ="
							+ user.getId());
			while (rs.next()) {
				eq.setId(rs.getInt(1));
				eq.setCategory(rs.getString(2));
				eq.setTitle(rs.getString(3));
				eq.setPrice(rs.getDouble(4));
				equipments.add(eq);
			}
			if (!equipments.isEmpty()) {
				ower = new Ower();
				ower.setUser(user);
				ower.setEquipments(equipments);
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
		return ower;
	}

	@Override
	public void deleteOwer(Ower ower) throws DAOException {
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM `shop`.`rented_units` WHERE `c_id`=" + ower.getUser().getId());
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

}
