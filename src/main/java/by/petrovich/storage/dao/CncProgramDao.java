package by.petrovich.storage.dao;

import by.petrovich.storage.entity.CncProgram;

import java.util.List;

public interface CncProgramDao {
	List<CncProgram> findAll() throws DaoException;

	void create(CncProgram cncProgram) throws DaoException;

	CncProgram read(int id) throws DaoException;

	void update(CncProgram cncProgram, int id) throws DaoException;

	void delete(CncProgram cncProgram) throws DaoException;

	CncProgram find(String name) throws DaoException;

}
