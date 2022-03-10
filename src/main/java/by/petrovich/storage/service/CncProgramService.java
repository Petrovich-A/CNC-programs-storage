package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.CncProgram;

public interface CncProgramService {
	void create(CncProgram cncProgram) throws ServiceException;

	CncProgram read(int id) throws ServiceException;

	void delete(int id) throws ServiceException;

	void update(CncProgram ñncProgram, int id) throws ServiceException;

	boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException;

	List<CncProgram> readAll(int offset, int numberOfRecords) throws ServiceException;

	List<CncProgram> showList() throws ServiceException;

	int getNumberOfRecords() throws ServiceException;

}
