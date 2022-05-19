package by.petrovich.storage.service;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author Petrovich A.V.
 *
 */
public interface CncProgramService {
	/**
	 * @return
	 * @throws ServiceException
	 */
	List<Detail> readDetail() throws ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Optional<CncProgram> readCncProgramById(int id) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	List<CncMachine> readAllCncMachines() throws ServiceException;

	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	CncProgram readCncProgramByName(String name) throws ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Detail readDetailById(int id) throws ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	CncMachine readCncMachine(int id) throws ServiceException;

	/**
	 * @param offset
	 * @param numberOfRecords
	 * @return
	 * @throws ServiceException
	 */
	List<CncProgram> receiveBatch(int offset, int numberOfRecords) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	List<CncProgram> receiveBatchByDate() throws ServiceException;

	/**
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	List<CncProgram> receiveBatchByDetailName(String name) throws ServiceException;

	/**
	 * @param personnelNumber
	 * @return
	 * @throws ServiceException
	 */
	List<CncProgram> receiveBatchByPersonnelNumber(int personnelNumber) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	int receiveNumberOfRecords() throws ServiceException;

	/**
	 * @param cncProgram
	 * @throws ServiceException
	 */
	void createCncProgram(CncProgram cncProgram) throws ServiceException;

	/**
	 * @param ñncProgram
	 * @param id
	 * @throws ServiceException
	 */
	void updateCncProgram(CncProgram ñncProgram, int id) throws ServiceException;

	/**
	 * @param detail
	 * @param id
	 * @throws ServiceException
	 */
	void updateDetail(Detail detail, int id) throws ServiceException;

	/**
	 * @param cncMachine
	 * @param id
	 * @throws ServiceException
	 */
	void updateCncMachine(CncMachine cncMachine, int id) throws ServiceException;

	/**
	 * @param cncProgram
	 * @return
	 * @throws ServiceException
	 */
	boolean isCncProgramValid(CncProgram cncProgram) throws ServiceException;

}
