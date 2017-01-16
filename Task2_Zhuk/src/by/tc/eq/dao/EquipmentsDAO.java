package by.tc.eq.dao;

import java.util.List;

import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.dao.exception.DAOException;

public interface EquipmentsDAO {
	void addEquipment(Equipment equipment) throws DAOException;

	void deleteEquipment(Equipment equipment) throws DAOException;

	List<Equipment> getRentedEquipment() throws DAOException;

	List<Equipment> getShopEquipment() throws DAOException;

	Equipment getEquipment(String title) throws DAOException;
}