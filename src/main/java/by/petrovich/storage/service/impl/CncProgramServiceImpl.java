package by.petrovich.storage.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.dao.CncProgramDao;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.validator.impl.CncProgramValidator;

public class CncProgramServiceImpl implements CncProgramService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final CncProgramDao cncProgramDao = daoProvider.getCncProgramDao();
	private final CncProgramValidator cncProgramValidator = new CncProgramValidator();

	@Override
	public CncProgram read(int id) throws ServiceException {
		CncProgram cncProgramFromDao = null;
		try {
			cncProgramFromDao = cncProgramDao.read(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't find CNC program in BD by id: {}", id, e);
		}
		return cncProgramFromDao;
	}

	@Override
	public List<CncProgram> readAll() throws ServiceException {
		List<CncProgram> allCncPrograms = null;
		try {
			allCncPrograms = cncProgramDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't find all CNC programs in BD", e);
		}
		return allCncPrograms;
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			cncProgramDao.delete(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't delete CNC program with id: {}", id, e);
		}
	}

	@Override
	public void update(CncProgram �ncProgram, int id) throws ServiceException {
		try {
			cncProgramDao.update(�ncProgram, id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "can't update CNC program with id: {}, where" + " CNC program: {}", cncProgramDao,
					id, e);
		}
	}

	@Override
	public boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException {
		if (!cncProgramValidator.isCncProgramValid(cncProgram)) {
			logger.log(Level.ERROR, "CNC program from main form isn't valid. cncProgram: {} ", cncProgram.toString());
		}
		return false;
	}

	@Override
	public List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(CncProgram cncProgramFromMainForm) throws ServiceException {
		// TODO validate
		try {
			cncProgramDao.create(cncProgramFromMainForm);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
