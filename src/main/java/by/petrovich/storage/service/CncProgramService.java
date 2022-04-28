package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramService {
	void create(CncProgram cncProgram) throws ServiceException;

	CncProgram read(int id) throws ServiceException;

	void update(CncProgram �ncProgram, int id) throws ServiceException;

	void updateDetail(Detail detail, int id) throws ServiceException;

	void updateCncMachine(CncMachine cncMachine, int id) throws ServiceException;

	boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException;

	List<CncProgram> recieveBatch(int offset, int numberOfRecords) throws ServiceException;

	List<CncProgram> recieveBatchByName() throws ServiceException;

	List<CncProgram> recieveBatchByDetailName(String name) throws ServiceException;

	List<CncProgram> recieveBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws ServiceException;

	int getNumberOfRecords() throws ServiceException;

	CncProgram searchCncProgram(String name) throws ServiceException;

	List<CncMachine> readCncMachine() throws ServiceException;

	List<Detail> readDetail() throws ServiceException;

	Detail readDetailById(int id) throws ServiceException;

	CncMachine readCncMachineById(int id) throws ServiceException;

}
