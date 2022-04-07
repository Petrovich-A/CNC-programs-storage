package by.petrovich.storage.dao;

import java.util.List;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramDao {
	List<CncProgram> readBatch(int offset, int numberOfRecords) throws DaoException;

	List<CncProgram> readBatchByDate() throws DaoException;

	List<CncProgram> readBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws DaoException;

	public int getNumberOfRecords() throws DaoException;

	void create(CncProgram cncProgramFromMainForm) throws DaoException;

	CncProgram read(int id) throws DaoException;

	List<CncMachine> readCncMachine() throws DaoException;

	List<Detail> readDetail() throws DaoException;

	void update(CncProgram cncProgram, int id) throws DaoException;

	void updateDetail(Detail detail, int id) throws DaoException;

	void updateCncMachine(CncMachine cncMachine, int id) throws DaoException;

	CncProgram readBatchByProgramName(String name) throws DaoException;

	void createDetail(Detail detail) throws DaoException;

	void createCncMachine(CncMachine cncMachine) throws DaoException;

	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException;

	Detail readDetailByName(String name) throws DaoException;

	CncMachine readCncMachineByModel(String model) throws DaoException;

	Detail readDetailById(int id) throws DaoException;

	CncMachine readCncMachineById(int id) throws DaoException;

}
