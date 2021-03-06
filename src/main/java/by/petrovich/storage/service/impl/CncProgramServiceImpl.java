package by.petrovich.storage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.dao.CncProgramDao;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.DaoProvider;
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.validator.impl.CncProgramValidator;

public class CncProgramServiceImpl implements CncProgramService {
	private static final Logger logger = LogManager.getLogger();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final CncProgramDao cncProgramDao = daoProvider.getCncProgramDao();
	private final CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();

	@Override
	public void createCncProgram(CncProgram cncProgramFromMainForm) throws ServiceException {
		boolean isCncProgramValid = false;
		isCncProgramValid = cncProgramValidator.isCncProgramValid(cncProgramFromMainForm);
		if (isCncProgramValid) {
			cncProgramFromMainForm.setActive(true);
			try {
				cncProgramDao.createCncProgram(cncProgramFromMainForm);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "can't create CNC program. cncProgramFromMainForm: {}",
						cncProgramFromMainForm.toString(), e);
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public Optional<CncProgram> readCncProgramById(int id) throws ServiceException {
		Optional<CncProgram> cncProgramOptional = Optional.empty();
		try {
			cncProgramOptional = cncProgramDao.readCncProgramById(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't read CNC program by id: {}", id, e);
			throw new ServiceException(e);
		}
		return cncProgramOptional;
	}

	@Override
	public void updateCncProgram(CncProgram ?ncProgram, int id) throws ServiceException {
		try {
			cncProgramDao.updateCncProgram(?ncProgram, id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't update CNC program with id: {}, where" + " CNC program: {}", cncProgramDao,
					id, ?ncProgram.toString(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isCncProgramValid(CncProgram cncProgram) throws ServiceException {
		CncProgramValidator cncProgramValidator = CncProgramValidator.getInstance();
		if (!cncProgramValidator.isCncProgramValid(cncProgram)) {
			logger.log(Level.ERROR, "CNC program from main form isn't valid. cncProgram: {} ", cncProgram.toString());
		}
		return false;
	}

	@Override
	public List<CncProgram> receiveBatch(int offset, int numberOfRecords) throws ServiceException {
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
	public List<CncProgram> receiveBatchByDate() throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatchByDate();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find all CNC programs.", e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

	@Override
	public List<CncProgram> receiveBatchByDetailName(String name) throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatchByDetailName(name);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find CNC programs by detail name.", e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

	@Override
	public int receiveNumberOfRecords() throws ServiceException {
		int numberOfRecords = 0;
		try {
			numberOfRecords = cncProgramDao.receiveNumberOfRecords();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't getNumberOfRecords programs in BD", e);
			throw new ServiceException(e);
		}
		return numberOfRecords;
	}

	@Override
	public CncProgram readCncProgramByName(String name) throws ServiceException {
		CncProgram cncProgram;
		try {
			cncProgram = cncProgramDao.readCncProgramByName(name);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't find CNC program", e);
			throw new ServiceException(e);
		}
		return cncProgram;
	}

	@Override
	public List<CncProgram> receiveBatchByPersonnelNumber(int personnelNumber) throws ServiceException {
		List<CncProgram> cncPrograms = null;
		try {
			cncPrograms = cncProgramDao.readBatchByPersonnelNumber(personnelNumber);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't recieve CNC program by personnel number: {}", personnelNumber, e);
			throw new ServiceException(e);
		}
		return cncPrograms;
	}

	@Override
	public List<CncMachine> readAllCncMachines() throws ServiceException {
		List<CncMachine> cncMachines = new ArrayList<>();
		try {
			cncMachines = cncProgramDao.readAllCncMachines();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't recieve CNC machines", e);
			throw new ServiceException(e);
		}
		return cncMachines;
	}

	@Override
	public List<Detail> readDetail() throws ServiceException {
		List<Detail> details = new ArrayList<>();
		try {
			details = cncProgramDao.readDetail();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't recieve details", e);
			throw new ServiceException(e);
		}
		return details;
	}

	@Override
	public Detail readDetailById(int id) throws ServiceException {
		Detail detail = new Detail();
		try {
			detail = cncProgramDao.readDetailById(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't read detail", e);
			throw new ServiceException(e);
		}
		return detail;
	}

	@Override
	public CncMachine readCncMachine(int id) throws ServiceException {
		CncMachine cncMachine = new CncMachine();
		try {
			cncMachine = cncProgramDao.readCncMachineById(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't read CNC machine", e);
			throw new ServiceException(e);
		}
		return cncMachine;
	}

	@Override
	public void updateDetail(Detail detail, int id) throws ServiceException {
		try {
			cncProgramDao.updateDetail(detail, id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't update detail with id: {}, detail: {}.", id, detail.toString(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateCncMachine(CncMachine cncMachine, int id) throws ServiceException {
		try {
			cncProgramDao.updateCncMachine(cncMachine, id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "Can't update CNC machine with id: {}, CNC machine: {},", id, cncMachine.toString(),
					e);
			throw new ServiceException(e);
		}
	}

}
