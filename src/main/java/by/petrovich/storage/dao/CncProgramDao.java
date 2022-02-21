package by.petrovich.storage.dao;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

import java.util.List;

public interface CncProgramDao {
	List<CncProgram> findAll() throws DaoException;

	void create(CncProgram cncProgramFromMainForm, int loginPersonnelNumber) throws DaoException;

	CncProgram read(int id) throws DaoException;

	void update(CncProgram cncProgram, int id) throws DaoException;

	void delete(int id) throws DaoException;

	CncProgram find(String name) throws DaoException;

	void createDetail(Detail detail) throws DaoException;

	void createCncMachine(CncMachine cncMachine) throws DaoException;

	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException;

	Detail readDetail(int id) throws DaoException;

	CncMachine readCncMachine(int id) throws DaoException;

}
