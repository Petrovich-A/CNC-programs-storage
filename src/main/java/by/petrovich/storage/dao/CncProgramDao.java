package by.petrovich.storage.dao;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramDao {
	List<CncMachine> readAllCncMachines() throws DaoException;

	List<CncProgram> readBatch(int offset, int numberOfRecords) throws DaoException;

	List<CncProgram> readBatchByDate() throws DaoException;

	List<Detail> readDetail() throws DaoException;

	List<CncProgram> readBatchByDetailName(String name) throws DaoException;

	List<CncProgram> readBatchByPersonnelNumber(int personnelNumber) throws DaoException;

	Optional<CncProgram> readCncProgramById(int id) throws DaoException;

	CncProgram readCncProgramByName(String name) throws DaoException;

	Detail readDetailByName(String name) throws DaoException;

	Detail readDetailById(int id) throws DaoException;

	CncMachine readCncMachineByModel(String model) throws DaoException;

	CncMachine readCncMachineById(int id) throws DaoException;

	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException;

	int receiveNumberOfRecords() throws DaoException;

	void createCncProgram(CncProgram cncProgramFromMainForm) throws DaoException;

	int createDetail(Detail detail) throws DaoException;

	int createCncMachine(CncMachine cncMachine) throws DaoException;

	void updateCncProgram(CncProgram cncProgram, int id) throws DaoException;

	void updateDetail(Detail detail, int id) throws DaoException;

	void updateCncMachine(CncMachine cncMachine, int id) throws DaoException;

}
