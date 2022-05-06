package by.petrovich.storage.dao.impl;

import static by.petrovich.storage.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import by.petrovich.storage.dao.CncProgramDao;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.connection.ConnectionPool;
import by.petrovich.storage.entity.CncMachine;
import by.petrovich.storage.entity.CncProgram;
import by.petrovich.storage.entity.Detail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CncProgramDaoImpl implements CncProgramDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_READ_CNC_PROGRAM_WITH_LIMIT = """
			SELECT
				SQL_CALC_FOUND_ROWS program_id, program_number, operation_number,
				program_text, cnc_programs.create_time, comment, active,
				cnc_programs.login_personnel_number, cnc_programs.detail_id,
				cnc_programs.cnc_machine_id, details.detail_name, cnc_machines.model,
				cnc_machines.code_equipment
					FROM cnc_programs
						LEFT JOIN users
							ON users.login_personnel_number = cnc_programs.login_personnel_number
						LEFT JOIN details
							ON details.detail_id = cnc_programs.detail_id
						LEFT JOIN cnc_machines
							ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id
						LIMIT
			""";
	private static final String SQL_READ_CNC_PROGRAM_BY_ID = """
			SELECT
				program_id, program_number, operation_number, program_text, cnc_programs.create_time,
				comment, active, cnc_programs.login_personnel_number, cnc_programs.detail_id,
				cnc_programs.cnc_machine_id, details.detail_name, cnc_machines.model,
				cnc_machines.code_equipment FROM cnc_programs
					LEFT JOIN users
						ON users.login_personnel_number = cnc_programs.login_personnel_number
					LEFT JOIN details
						ON details.detail_id = cnc_programs.detail_id
					LEFT JOIN cnc_machines
						ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id
						WHERE program_id = ?
			""";
	private static final String SQL_READ_CNC_PROGRAM_BY_DATE = """
			SELECT
				program_id, program_number, operation_number, program_text, cnc_programs.create_time,
				comment, active, cnc_programs.login_personnel_number, cnc_programs.detail_id,
				cnc_programs.cnc_machine_id, details.detail_name, cnc_machines.model,
				cnc_machines.code_equipment
					FROM cnc_programs
					LEFT JOIN users
						ON users.login_personnel_number = cnc_programs.login_personnel_number
					LEFT JOIN details
						ON details.detail_id = cnc_programs.detail_id
					LEFT JOIN cnc_machines
						ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id "
						WHERE active = 1
						ORDER BY cnc_programs.create_time
						DESC
			""";
	private static final String SQL_READ_CNC_PROGRAM_BY_NAME = """
			SELECT
				program_id, program_number, operation_number, program_text, cnc_programs.create_time,
				comment, active, cnc_programs.login_personnel_number, details.detail_id,
				details.detail_name, cnc_machines.cnc_machine_id, cnc_machines.model,
				cnc_machines.code_equipment FROM cnc_programs
					LEFT JOIN users
						ON users.login_personnel_number = cnc_programs.login_personnel_number
					LEFT JOIN details
						ON details.detail_id = cnc_programs.detail_id
					LEFT JOIN cnc_machines
						ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id
							WHERE program_number = ?
			""";
	private static final String SQL_READ_CNC_PROGRAM_BY_DETAIL_NAME = """
			SELECT
				program_id, program_number, operation_number, program_text, create_time, comment,
				active, login_personnel_number, cnc_programs.detail_id, details.detail_name,
				cnc_machines.cnc_machine_id, cnc_machines.model, cnc_machines.code_equipment
					FROM cncprogramsstorage.cnc_programs
					LEFT JOIN details
						ON details.detail_id = cnc_programs.detail_id
					LEFT JOIN cnc_machines
						ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id
							WHERE detail_name=?
			""";
	private static final String SQL_READ_CNC_PROGRAM_BY_LOGIN_PERSONNEL_NUMBER = """
			SELECT
				program_id, program_number, operation_number, program_text, create_time, comment,
				active, login_personnel_number, cnc_programs.detail_id, details.detail_name,
				cnc_programs.cnc_machine_id, cnc_machines.model, cnc_machines.code_equipment
					FROM cncprogramsstorage.cnc_programs "
						LEFT JOIN details
							ON details.detail_id = cnc_programs.detail_id
						LEFT JOIN cnc_machines
							ON cnc_machines.cnc_machine_id = cnc_programs.cnc_machine_id
								WHERE login_personnel_number = ?
								ORDER BY cnc_programs.create_time
								 DESC
			""";
	private static final String SQL_READ_DETAILS = """
			SELECT
				detail_id, detail_name
				FROM details
				ORDER BY detail_name
			""";
	private static final String SQL_READ_CNC_MACHINES = """
			SELECT
				cnc_machine_id, model, code_equipment
					FROM cnc_machines
						ORDER BY model"
			""";
	private static final String SQL_READ_CNC_MACHINE_BY_MODEL = """
			SELECT
				cnc_machine_id, model, code_equipment
				FROM cnc_machines
					WHERE model = ?
			""";
	private static final String SQL_READ_CNC_MACHINE_BY_ID = """
			SELECT
				cnc_machine_id, model, code_equipment
				FROM cnc_machines
					WHERE cnc_machine_id = ?
						ORDER BY model
			""";
	private static final String SQL_READ_DETAIL_BY_NAME = """
			SELECT
				detail_id, detail_name
				FROM details
					WHERE detail_name = ?
			""";
	private static final String SQL_READ_DETAIL_BY_ID = """
			SELECT
				detail_id, detail_name
					FROM details
						WHERE detail_id = ?
			""";
	private static final String SQL_FOUND_ROWS = """
			SELECT
				COUNT(*)
					FROM cnc_programs
			""";
	private static final String SQL_CREATE_CNC_PROGRAMS = """
			INSERT INTO
				cnc_programs(program_number, operation_number, program_text, create_time, comment, active,
				login_personnel_number, detail_id, cnc_machine_id)
					VALUES(?,?,?,?,?,?,?,?,?)
			""";
	private static final String SQL_CREATE_DETAIL = """
			INSERT IGNORE INTO
				details (detail_name)
					VALUES (?)
			""";
	private static final String SQL_CREATE_CNC_MACHINE = """
			INSERT IGNORE INTO
				cnc_machines (model, code_equipment)
					VALUES (?, ?)
			""";
	private static final String SQL_IS_DETAIL_EXIST_BY_NAME = """
			SELECT EXISTS
				(SELECT detail_name
					FROM details
						WHERE detail_name = ?)"
			""";
	private static final String SQL_IS_CNC_MACHINE_EXIST_BY_MODEL = """
			SELECT EXISTS
				(SELECT model
					FROM cnc_machines
						WHERE model = ?)
			""";
	private static final String SQL_UPDATE_CNC_PROGRAM = """
			UPDATE
				cnc_programs SET program_number = ?, operation_number = ?, comment = ?, active = ?
					WHERE program_id = ?
			""";
	private static final String SQL_UPDATE_DETAIL = """
			UPDATE
				details
				SET detail_name = ?
					WHERE detail_id = ?
			""";
	private static final String SQL_UPDATE_CNC_MACHINES = """
			UPDATE
				cnc_machines
				SET model = ?, code_equipment = ?
					WHERE cnc_machine_id = ?
			""";
	private static final String SQL_SET_CNC_PROGRAM_INACTIVE = """
			UPDATE
				cnc_programs
					SET active = 0
						WHERE program_number = ? AND program_id > 0
			""";

	@Override
	public List<CncProgram> readBatch(int offset, int numberOfRecords) throws DaoException {
		List<CncProgram> cncPrograms = new ArrayList<>();
		String sql_query = SQL_READ_CNC_PROGRAM_WITH_LIMIT + " " + offset + ", " + numberOfRecords;
		logger.log(Level.INFO, "Sring query building. sql_query: {}", sql_query);
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				cncPrograms.add(buildCncProgram(resultSet));
			}
			logger.log(Level.INFO, "Reading all CNC programs from BD have done successfully. allCncPrograms: {} ",
					cncPrograms.toString());
		} catch (SQLException e) {
			throw new DaoException(
					String.format("can't read batchOfCncPrograms with offset: {} and numberOfRecords: {} from DB",
							offset, numberOfRecords, e));
		}
		return cncPrograms;
	}

	@Override
	public List<CncProgram> readBatchByLoginPersonnelNumber(int loginPersonnelNumber) throws DaoException {
		List<CncProgram> cncPrograms = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_READ_CNC_PROGRAM_BY_LOGIN_PERSONNEL_NUMBER)) {
			preparedStatement.setInt(1, loginPersonnelNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncPrograms.add(buildCncProgram(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't read CNC program by login personnel number: {}.", loginPersonnelNumber));
		}
		return cncPrograms;
	}

	@Override
	public List<CncProgram> readBatchByDate() throws DaoException {
		List<CncProgram> cncPrograms = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_PROGRAM_BY_DATE);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				cncPrograms.add(buildCncProgram(resultSet));
			}
			logger.log(Level.INFO, "Reading all CNC programs from BD have done successfully. allCncPrograms: {} ",
					cncPrograms.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't read all CNC programs from DB", e));
		}
		return cncPrograms;
	}

	@Override
	public List<CncProgram> readBatchByDetailName(String name) throws DaoException {
		List<CncProgram> cncPrograms = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_READ_CNC_PROGRAM_BY_DETAIL_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncPrograms.add(buildCncProgram(resultSet));
			}
			logger.log(Level.INFO,
					"Reading CNC programs from BD by detail name have done successfully. cncPrograms: {} ",
					cncPrograms.toString());
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't read CNC Programs from DB by detail name. Name: {}.", name, e));
		}
		return cncPrograms;
	}

	@Override
	public int getNumberOfRecords() throws DaoException {
		int numberOfRecords = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOUND_ROWS);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				numberOfRecords = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("can't get number of records from DB, numberOfRecords: %s", numberOfRecords, e));
		}
		return numberOfRecords;
	}

	@Override
	public void create(CncProgram cncProgram) throws DaoException {
		int detailId = 0;
		int cncMachineId = 0;
		Connection connection = ConnectionPool.getInstance().getConnection();
		detailId = createDetail(cncProgram.getDetail());
		cncMachineId = createCncMachine(cncProgram.getCncMachine());
		try (PreparedStatement preparedStatementCncProgram = connection.prepareStatement(SQL_CREATE_CNC_PROGRAMS);
				PreparedStatement prepareStatementSetInactive = connection
						.prepareStatement(SQL_SET_CNC_PROGRAM_INACTIVE)) {
			connection.setAutoCommit(false);
			prepareStatementSetInactive.setString(1, cncProgram.getNumber());
			prepareStatementSetInactive.executeUpdate();
			logger.log(Level.INFO, "CNC program with number: {} is inactivated", cncProgram.getNumber());
			preparedStatementCncProgram.setString(1, cncProgram.getNumber());
			preparedStatementCncProgram.setInt(2, cncProgram.getOperationNumber());
			preparedStatementCncProgram.setString(3, cncProgram.getProgramText());
			preparedStatementCncProgram.setTimestamp(4, cncProgram.getCreationDate());
			preparedStatementCncProgram.setString(5, cncProgram.getComment());
			preparedStatementCncProgram.setBoolean(6, cncProgram.isActive());
			preparedStatementCncProgram.setInt(7, cncProgram.getLoginPersonnelNumber());
			preparedStatementCncProgram.setInt(8, detailId);
			preparedStatementCncProgram.setInt(9, cncMachineId);
			preparedStatementCncProgram.executeUpdate();
			logger.log(Level.INFO, "CNC program creating have done successfully", cncProgram.toString());
			connection.commit();
		} catch (SQLException e) {
			rollBackCOnnection(connection);
			throw new DaoException(
					String.format("Transaction is failed. Can't create CNC program in DB. cncProgram: %s.",
							cncProgram.toString()),
					e);
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Connection hasn't been closed", cncProgram.toString());
				e.printStackTrace();
			}
		}
	}

	@Override
	public int createDetail(Detail detail) throws DaoException {
		Detail detailFromDateBase = new Detail();
		int detailId = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_DETAIL,
						RETURN_GENERATED_KEYS)) {
			if (!isDetailExist(detail.getName())) {
				preparedStatement.setString(1, detail.getName());
				preparedStatement.executeUpdate();
				logger.log(Level.INFO, "Detail creating has done sucesfully. detail: {}", detail.toString());
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					if (resultSet.next()) {
						detailId = resultSet.getInt(1);
					}
				}
			} else {
				detailFromDateBase = readDetailByName(detail.getName());
				detailId = detailFromDateBase.getId();
			}
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't create datail in DB. detail: %s", detail.toString(), e));
		}
		return detailId;
	}

	@Override
	public int createCncMachine(CncMachine cncMachine) throws DaoException {
		CncMachine cncMachineFromDateBase = null;
		int cncMachineId = 0;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CNC_MACHINE,
						RETURN_GENERATED_KEYS)) {
			if (!isCncMachineExist(cncMachine.getModel())) {
				preparedStatement.setString(1, cncMachine.getModel());
				preparedStatement.setInt(2, cncMachine.getCodeEquipment());
				preparedStatement.executeUpdate();
				logger.log(Level.INFO, "CNC machine creating has done sucesfully. CNC machine: {}",
						cncMachine.toString());
				try (ResultSet resultSetCncMachine = preparedStatement.getGeneratedKeys()) {
					if (resultSetCncMachine.next()) {
						cncMachineId = resultSetCncMachine.getInt(1);
					}
				}
			} else {
				cncMachineFromDateBase = readCncMachineByModel(cncMachine.getModel());
				cncMachineId = cncMachineFromDateBase.getId();
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't create CNC machine in DB. CNC machine: %s", cncMachine.toString(), e));
		}
		return cncMachineId;
	}

	@Override
	public Optional<CncProgram> readCncProgramById(int id) throws DaoException {
		Optional<CncProgram> cncProgramOptional = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_PROGRAM_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CncProgram cncProgram = buildCncProgram(resultSet);
				cncProgramOptional = Optional.of(cncProgram);
				logger.log(Level.INFO, "CNC program is read", cncProgram.toString());
			}
		} catch (SQLException e) {
			throw new DaoException(String.format("Ñan't read CNC program by id: %s from DB", id, e));
		}
		return cncProgramOptional;
	}

	@Override
	public void update(CncProgram cncProgram, int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CNC_PROGRAM)) {
			preparedStatement.setString(1, cncProgram.getNumber());
			preparedStatement.setInt(2, cncProgram.getOperationNumber());
			preparedStatement.setString(3, cncProgram.getComment());
			preparedStatement.setBoolean(4, cncProgram.isActive());
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Updating CNC program succesfull with program id: {}", id);
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't update CNC program with id: %s, CNC program: %s.", id,
					cncProgram.toString(), e));
		}
	}

	@Override
	public CncProgram readCncProgramByName(String name) throws DaoException {
		CncProgram cncProgram = null;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_PROGRAM_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncProgram = buildCncProgram(resultSet);
				logger.log(Level.INFO, "CNC program is found with name: ", name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return cncProgram;
	}

	@Override
	public List<CncProgram> findAmountOfRows(int startRow, int amountOfRows) throws DaoException {
		List<CncProgram> cncPrograms = new ArrayList<>();
		String sqlQueryBuild = "SELECT program_id, program_text, program_name, "
				+ "create_time, operation_number, program_file_extension, comment, "
				+ "active, detail_id FROM cnc_programs LIMIT " + startRow + ", " + amountOfRows;
		ResultSet resultSet;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryBuild)) {
			resultSet = preparedStatement.executeQuery(sqlQueryBuild);
			while (resultSet.next()) {
				cncPrograms.add(buildCncProgram(resultSet));
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException();
		}
		return cncPrograms;
	}

	@Override
	public Detail readDetailById(int id) throws DaoException {
		Detail detail = new Detail();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_DETAIL_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				detail = buildDetail(resultSet);
				logger.log(Level.INFO, "Detail is read", detail.toString());
			}
		} catch (SQLException e) {
			throw new DaoException(String.format("can't save detail in DB. detail: %s", detail.toString(), e));
		}
		return detail;
	}

	@Override
	public Detail readDetailByName(String name) throws DaoException {
		Detail detail = new Detail();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_DETAIL_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				detail = buildDetail(resultSet);
				logger.log(Level.INFO, "Detail is read", detail.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return detail;
	}

	@Override
	public CncMachine readCncMachineByModel(String model) throws DaoException {
		CncMachine cncMachine = new CncMachine();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_MACHINE_BY_MODEL)) {
			preparedStatement.setString(1, model);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncMachine = buildCncMachine(resultSet);
				logger.log(Level.INFO, "CNC machine is read", cncMachine.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return cncMachine;
	}

	@Override
	public List<CncMachine> readCncMachine() throws DaoException {
		List<CncMachine> cncMachines = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_MACHINES);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				cncMachines.add(buildCncMachine(resultSet));
			}
			logger.log(Level.INFO, "Reading all CNC machines from BD have done successfully.");
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't read CNC machines from DB", e));
		}
		return cncMachines;
	}

	@Override
	public List<Detail> readDetail() throws DaoException {
		List<Detail> details = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_DETAILS);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				details.add(buildDetail(resultSet));
			}
			logger.log(Level.INFO, "Reading all details from BD have done successfully.");
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't read details from DB", e));
		}
		return details;
	}

	@Override
	public void updateDetail(Detail detail, int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DETAIL)) {
			preparedStatement.setString(1, detail.getName());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Updating detail succesfull with detail id: {}", id);
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't update detail with id: %s, detail: %s.", id, detail.toString(), e));
		}
	}

	@Override
	public void updateCncMachine(CncMachine cncMachine, int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CNC_MACHINES)) {
			preparedStatement.setString(1, cncMachine.getModel());
			preparedStatement.setInt(2, cncMachine.getCodeEquipment());
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Updating CNC machine succesfull with id: {}", id);
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't update CNC machine with id: %s, detail: %s.", id, cncMachine.toString(), e));
		}
	}

	@Override
	public CncMachine readCncMachineById(int id) throws DaoException {
		CncMachine cncMachine = new CncMachine();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_CNC_MACHINE_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncMachine = buildCncMachine(resultSet);
				logger.log(Level.INFO, "CNC machine is read", cncMachine.toString());
			}
		} catch (SQLException e) {
			throw new DaoException(
					String.format("Can't read CNC machine in DB. CNC machine: %s", cncMachine.toString(), e));
		}
		return cncMachine;
	}

	private boolean isDetailExist(String name) throws DaoException {
		boolean isExist = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_DETAIL_EXIST_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isExist = resultSet.getBoolean(1);
			}
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't do sql query: %s", SQL_IS_DETAIL_EXIST_BY_NAME, e));
		}
		logger.log(Level.INFO, "isExist: {}", isExist);
		return isExist;
	}

	private boolean isCncMachineExist(String model) throws DaoException {
		boolean isExist = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_CNC_MACHINE_EXIST_BY_MODEL)) {
			preparedStatement.setString(1, model);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isExist = resultSet.getBoolean(1);
			}
		} catch (SQLException e) {
			throw new DaoException(String.format("Can't do sql query: %s", SQL_IS_DETAIL_EXIST_BY_NAME, e));
		}
		logger.log(Level.INFO, "isExist: {}", isExist);
		return isExist;
	}

	private CncProgram buildCncProgram(ResultSet resultSet) throws SQLException {
		CncProgram cncProgram = new CncProgram();
		cncProgram.setId(resultSet.getInt(PROGRAM_ID));
		cncProgram.setNumber(resultSet.getString(PROGRAM_NUMBER));
		cncProgram.setOperationNumber(resultSet.getInt(OPERATION_NUMBER));
		cncProgram.setProgramText(resultSet.getString(PROGRAM_TEXT));
		cncProgram.setCreationDate(resultSet.getTimestamp(create_time));
		cncProgram.setComment(resultSet.getString(COMMENT));
		cncProgram.setActive(resultSet.getBoolean(ACTIVE));
		cncProgram.setLoginPersonnelNumber(resultSet.getInt(LOGIN_PERSONNEL_NUMBER));
		cncProgram.setDetail(buildDetail(resultSet));
		cncProgram.setCncMachine(buildCncMachine(resultSet));
		cncProgram.getDetail().getId();
		logger.log(Level.INFO, "cnc program is built successfully, cncProgram: {}", cncProgram.toString());
		return cncProgram;
	}

	private Detail buildDetail(ResultSet resultSet) throws SQLException {
		Detail detail = new Detail();
		detail.setId(resultSet.getInt(DETAIL_ID));
		detail.setName(resultSet.getString(DETAIL_NAME));
		logger.log(Level.INFO, "detail build successfully, detail: {}", detail.toString());
		return detail;
	}

	private CncMachine buildCncMachine(ResultSet resultSet) throws SQLException {
		CncMachine cncMachine = new CncMachine();
		cncMachine.setId(resultSet.getInt(CNC_MACHINE_ID));
		cncMachine.setModel(resultSet.getString(MODEL));
		cncMachine.setCodeEquipment(resultSet.getInt(CODE_EQUIPMENT));
		logger.log(Level.INFO, "cncMachine build successfully, cncMachine: {}", cncMachine.toString());
		return cncMachine;
	}

	private void rollBackCOnnection(Connection connection) throws DaoException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new DaoException(String.format("Do rollBack connection", e));
		}
	}

}
