package by.tc.eq.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.dao.DataBaseConnector;
import by.tc.eq.dao.EquipmentsDAO;
import by.tc.eq.dao.exception.DAOException;

public class EquipmentsDAOImpl implements EquipmentsDAO {

	private Connection con;

	@Override
	public void addEquipment(Equipment eq) throws DAOException {
		try {
			int categoryID = 0;
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT `ct_id` FROM `shop`.`category` WHERE `ct_name`='" + eq.getCategory() + "'");
			while (rs.next()) {
				categoryID = rs.getInt(1);
			}
			st.executeUpdate(
					"INSERT INTO `shop`.`equipments` (`e_id`, `category_id`, `e_title`, `e_price`,`e_amount`) VALUES (DEFAULT,'"
							+ categoryID + "', '" + eq.getTitle() + "'," + eq.getPrice() + "," + eq.getAmount() + ")");
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
	}

	@Override
	public Equipment getEquipment(String title) throws DAOException {
		Equipment eq = null;
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(
					"SELECT `e_id`,`ct_name`, `e_title`, `e_price` FROM `shop`.`equipments` LEFT JOIN `shop`.`category` ON `ct_id`=`category_id` WHERE `e_title`='"
							+ title + "'");
			while (rs.next()) {
				eq = new Equipment();
				eq.setId(rs.getInt(1));
				eq.setCategory(rs.getString(2));
				eq.setTitle(rs.getString(3));
				eq.setPrice(rs.getDouble(4));
				eq.setAmount(1);
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
		return eq;
	}

	@Override
	public void deleteEquipment(Equipment equipment) throws DAOException {
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM `shop`.`equipments` WHERE `e_title`='" + equipment.getTitle() + "'");
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
	public List<Equipment> getRentedEquipment() throws DAOException {
		List<Equipment> equipments = new ArrayList<Equipment>();
		Equipment eq = new Equipment();
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(
					"SELECT count(e_id),`e_id`, `category_id`, `e_title`, `e_price` FROM `shop`.`equipments` AS `e` INNER JOIN `shop`.`rented_units` AS `r` ON `e`.`e_id` = `r`.`eq_id` GROUP BY `e_id`;");
			while (rs.next()) {
				eq.setAmount(rs.getInt(1));
				eq.setId(rs.getInt(2));
				eq.setCategory(rs.getString(3));
				eq.setTitle(rs.getString(4));
				eq.setPrice(rs.getDouble(5));
				equipments.add(eq);
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
		return equipments;
	}

	@Override
	public List<Equipment> getShopEquipment() throws DAOException {
		List<Equipment> equipments = new ArrayList<Equipment>();
		Equipment eq = new Equipment();
		try {
			con = DataBaseConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(
					"SELECT `e_id`,`ct_name`, `e_title`, `e_price`, `e_amount` FROM `shop`.`equipments` LEFT JOIN `shop`.`category` ON `ct_id`=`category_id`");
			while (rs.next()) {
				eq.setId(rs.getInt(1));
				eq.setCategory(rs.getString(2));
				eq.setTitle(rs.getString(3));
				eq.setPrice(rs.getDouble(4));
				eq.setAmount(rs.getInt(5));
				equipments.add(eq);
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
		return equipments;
	}

}
