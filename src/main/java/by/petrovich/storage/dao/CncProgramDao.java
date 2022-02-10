package by.petrovich.storage.dao;

import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.FileExtensions;

import java.util.List;

public interface CncProgramDao {
	List<CncProgram> findAll() throws DaoException;

	void create(CncProgram cncProgramFromMainForm) throws DaoException;

	CncProgram read(int id) throws DaoException;

	void update(CncProgram cncProgram, int id) throws DaoException;

	void delete(int id) throws DaoException;

	CncProgram find(String name) throws DaoException;

	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException;

	List<FileExtensions> findFileExtensionNames() throws DaoException;

}
