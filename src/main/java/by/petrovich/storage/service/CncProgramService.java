package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.CncProgram;

public interface CncProgramService {
	void create(CncProgram cncProgram) throws ServiceException;

	CncProgram read(int id) throws ServiceException;

	void update(CncProgram ñncProgram, int id) throws ServiceException;

	boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException;

	List<CncProgram> recieveBatch(int offset, int numberOfRecords) throws ServiceException;

	List<CncProgram> recieveBatchByName() throws ServiceException;

	List<CncProgram> recieveBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws ServiceException;

	int getNumberOfRecords() throws ServiceException;

	CncProgram searchCncProgram(String name) throws ServiceException;

}
