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
	private final CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();

	@Override
	public void create(CncProgram cncProgramFromMainForm) throws ServiceException {
		boolean isCncProgramValid = false;
		isCncProgramValid = cncProgramValidator.isCncProgramValid(cncProgramFromMainForm);
		if (isCncProgramValid) {
			cncProgramFromMainForm.setActive(true);
			try {
				cncProgramDao.create(cncProgramFromMainForm);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "can't create CNC program. cncProgramFromMainForm: {}",
						cncProgramFromMainForm.toString(), e);
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public CncProgram read(int id) throws ServiceException {
		CncProgram cncProgram = new CncProgram();
		try {
			cncProgram = cncProgramDao.read(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't read CNC program by id: {}", id, e);
			throw new ServiceException(e);
		}
		return cncProgram;
	}

	@Override
	public void update(CncProgram ñncProgram, int id) throws ServiceException {
		try {
			cncProgramDao.update(ñncProgram, id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't update CNC program with id: {}, where" + " CNC program: {}", cncProgramDao,
					id, ñncProgram.toString(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean cncProgramValidate(CncProgram cncProgram) throws ServiceException {
		CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();
		if (!cncProgramValidator.isCncProgramValid(cncProgram)) {
			logger.log(Level.ERROR, "CNC program from main form isn't valid. cncProgram: {} ", cncProgram.toString());
		}
		return false;
	}

	@Override
	public List<CncProgram> recieveBatch(int offset, int numberOfRecords) throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatch(offset, numberOfRecords);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find all CNC programs in BD", e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

	@Override
	public List<CncProgram> recieveBatchByName() throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatchByDate();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find all CNC programs in BD", e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

	@Override
	public int getNumberOfRecords() throws ServiceException {
		int numberOfRecords = 0;
		try {
			numberOfRecords = cncProgramDao.getNumberOfRecords();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't getNumberOfRecords programs in BD", e);
			throw new ServiceException(e);
		}
		return numberOfRecords;
	}

	@Override
	public CncProgram searchCncProgram(String name) throws ServiceException {
		CncProgram cncProgram = null;
		try {
			cncProgram = cncProgramDao.readBatchByProgramName(name);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find CNC program", e);
			throw new ServiceException(e);
		}
		return cncProgram;
	}

	@Override
	public List<CncProgram> recieveBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatchByLoginPersonnelNumber(loginPersonnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't recieve CNC program by login personnel number: {}", loginPersonnelNumber, e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

}
