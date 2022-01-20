package by.petrovich.storage.service;

import java.util.List;

import by.petrovich.storage.entity.CncProgram;

public interface CncProgramService {
	CncProgram read(int id) throws ServiceException;

	CncProgram find(int loginPersonnelNumber) throws ServiceException;

	List<CncProgram> readAll() throws ServiceException;

	void delete(int loginPersonnelNumber) throws ServiceException;

	void update(CncProgram ñncProgram, int id) throws ServiceException;

	boolean validate(CncProgram cncProgram) throws ServiceException;

}
