package by.petrovich.storage.service;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

public interface CncProgramService {
	List<Detail> readDetail() throws ServiceException;

	Optional<CncProgram> readCncProgramById(int id) throws ServiceException;

	List<CncMachine> readAllCncMachines() throws ServiceException;

	CncProgram readCncProgramByName(String name) throws ServiceException;

	Detail readDetailById(int id) throws ServiceException;

	CncMachine readCncMachine(int id) throws ServiceException;

	List<CncProgram> receiveBatch(int offset, int numberOfRecords) throws ServiceException;

	List<CncProgram> receiveBatchByDate() throws ServiceException;

	List<CncProgram> receiveBatchByDetailName(String name) throws ServiceException;

	List<CncProgram> receiveBatchByPersonnelNumber(int personnelNumber) throws ServiceException;

	int receiveNumberOfRecords() throws ServiceException;

	void createCncProgram(CncProgram cncProgram) throws ServiceException;

	void updateCncProgram(CncProgram �ncProgram, int id) throws ServiceException;

	void updateDetail(Detail detail, int id) throws ServiceException;

	void updateCncMachine(CncMachine cncMachine, int id) throws ServiceException;

	boolean isCncProgramValid(CncProgram cncProgram) throws ServiceException;

}
