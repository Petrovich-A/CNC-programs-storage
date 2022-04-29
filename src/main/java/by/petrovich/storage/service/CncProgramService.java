package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramService {
	void createCncProgram(CncProgram cncProgram) throws ServiceException;

	CncProgram receiveCncProgramById(int id) throws ServiceException;

	void update(CncProgram ñncProgram, int id) throws ServiceException;

	void updateDetail(Detail detail, int id) throws ServiceException;

	void updateCncMachine(CncMachine cncMachine, int id) throws ServiceException;

	boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException;

	List<CncProgram> receiveBatch(int offset, int numberOfRecords) throws ServiceException;

	List<CncProgram> receiveBatchByName() throws ServiceException;

	List<CncProgram> receiveBatchByDetailName(String name) throws ServiceException;

	List<CncProgram> receiveBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws ServiceException;

	int receiveNumberOfRecords() throws ServiceException;

	CncProgram receiveCncProgram(String name) throws ServiceException;

	List<CncMachine> receiveCncMachine() throws ServiceException;

	List<Detail> receiveDetail() throws ServiceException;

	Detail receiveDetail(int id) throws ServiceException;

	CncMachine receiveCncMachine(int id) throws ServiceException;

}
