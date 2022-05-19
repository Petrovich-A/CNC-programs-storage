package by.petrovich.storage.dao;

import java.util.List;
import java.util.Optional;

import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

/**
 * @author Petrovich A.V.
 *
 */
public interface CncProgramDao {
	List<CncMachine> readAllCncMachines() throws DaoException;

	/**
	 * @param offset
	 * @param numberOfRecords
	 * @return
	 * @throws DaoException
	 */
	List<CncProgram> readBatch(int offset, int numberOfRecords) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	List<CncProgram> readBatchByDate() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	List<Detail> readDetail() throws DaoException;

	/**
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	List<CncProgram> readBatchByDetailName(String name) throws DaoException;

	/**
	 * @param personnelNumber
	 * @return
	 * @throws DaoException
	 */
	List<CncProgram> readBatchByPersonnelNumber(int personnelNumber) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	Optional<CncProgram> readCncProgramById(int id) throws DaoException;

	/**
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	CncProgram readCncProgramByName(String name) throws DaoException;

	/**
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	Detail readDetailByName(String name) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	Detail readDetailById(int id) throws DaoException;

	/**
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	CncMachine readCncMachineByModel(String model) throws DaoException;

	/**
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	CncMachine readCncMachineById(int id) throws DaoException;

	/**
	 * @param startRow
	 * @param amountOfRows
	 * @return
	 * @throws DaoException
	 */
	List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	int receiveNumberOfRecords() throws DaoException;

	/**
	 * @param cncProgramFromMainForm
	 * @throws DaoException
	 */
	void createCncProgram(CncProgram cncProgramFromMainForm) throws DaoException;

	/**
	 * @param detail
	 * @return
	 * @throws DaoException
	 */
	int createDetail(Detail detail) throws DaoException;

	/**
	 * @param cncMachine
	 * @return
	 * @throws DaoException
	 */
	int createCncMachine(CncMachine cncMachine) throws DaoException;

	/**
	 * @param cncProgram
	 * @param id
	 * @throws DaoException
	 */
	void updateCncProgram(CncProgram cncProgram, int id) throws DaoException;

	/**
	 * @param detail
	 * @param id
	 * @throws DaoException
	 */
	void updateDetail(Detail detail, int id) throws DaoException;

	/**
	 * @param cncMachine
	 * @param id
	 * @throws DaoException
	 */
	void updateCncMachine(CncMachine cncMachine, int id) throws DaoException;

}
