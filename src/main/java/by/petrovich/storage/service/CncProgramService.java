package by.petrovich.storage.service;

import java.sql.SQLException;
import java.util.List;

import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.User;

public interface CncProgramService {
	void create(CncProgram cncProgram, User user) throws ServiceException;

	CncProgram read(int id) throws ServiceException;

	List<CncProgram> readAll() throws ServiceException;

	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws ServiceException;

	void delete(int id) throws ServiceException;

	void update(CncProgram ñncProgram, int id) throws ServiceException;

	boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException;

}
