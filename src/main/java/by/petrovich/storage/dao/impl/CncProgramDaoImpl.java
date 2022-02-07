package by.petrovich.storage.dao.impl;

import by.petrovich.storage.dao.CncProgramDao;
import by.petrovich.storage.dao.ColumnName;
import by.petrovich.storage.dao.DaoException;
import by.petrovich.storage.dao.connection.ConnectionPool;
import by.petrovich.storage.entity.CncProgram;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CncProgramDaoImpl implements CncProgramDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_FIND_ALL = "SELECT program_id, program_text, program_name, create_time, operation_number,"
			+ " program_file_extension, comment, active, detail_id FROM cnc_programs";
	private static final String SQL_CREATE = "INSERT INTO cnc_programs(program_id, program_text, program_name, create_time,"
			+ " operation_number, program_file_extension, comment, active, detail_id) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_READ = "SELECT program_id, program_text, program_name, create_time, operation_number, "
			+ "program_file_extension, comment, active, detail_id FROM cnc_programs WHERE program_id";
	private static final String SQL_UPDATE = "UPDATE cnc_programs SET program_id, program_text, program_name, create_time, "
			+ "operation_number, program_file_extension, comment, active, detail_id WHERE program_id = ?";
	private static final String SQL_DELETE = "DELETE FROM  program_id, program_text, program_name, create_time, operation_number, "
			+ "program_file_extension, comment, active, detail_id FROM cnc_programs WHERE program_id";
	private static final String SQL_FIND_BY_NAME = "SELECT program_id, program_text, program_name, create_time, operation_number, "
			+ "program_file_extension, comment, active, detail_id FROM cnc_programs WHERE name";

	@Override
	public List<CncProgram> findAll() throws DaoException {
		List<CncProgram> allCncPrograms = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				allCncPrograms.add(buildCncProgram(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return allCncPrograms;
	}

	@Override
	public void create(CncProgram cncProgram) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
			preparedStatement.setString(1, cncProgram.getProgramText());
			preparedStatement.setString(2, cncProgram.getName());
			preparedStatement.setInt(3, cncProgram.getOperationNumber());
			preparedStatement.setString(4, cncProgram.getFileExtension());
			preparedStatement.setString(5, cncProgram.getComment());
			preparedStatement.setBoolean(6, cncProgram.isActive());
			preparedStatement.setTimestamp(7, (Timestamp) cncProgram.getDate());
			logger.log(Level.DEBUG, "create cnc program have done sucesfull", cncProgram.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public CncProgram read(int id) throws DaoException {
		CncProgram cncProgram = new CncProgram();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncProgram.setId(resultSet.getInt(1));
				cncProgram.setProgramText(resultSet.getString(2));
				cncProgram.setName(resultSet.getString(3));
				cncProgram.setOperationNumber(resultSet.getInt(4));
				cncProgram.setFileExtension(resultSet.getString(5));
				cncProgram.setComment(resultSet.getString(6));
				cncProgram.setActive(resultSet.getBoolean(7));
				cncProgram.setDate(resultSet.getDate(8));
				logger.log(Level.DEBUG, "cnc program is read", cncProgram.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return cncProgram;
	}

	@Override
	public void update(CncProgram cncProgram, int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setInt(1, cncProgram.getId());
			preparedStatement.setString(2, cncProgram.getProgramText());
			preparedStatement.setString(3, cncProgram.getName());
			preparedStatement.setInt(4, cncProgram.getOperationNumber());
			preparedStatement.setString(5, cncProgram.getFileExtension());
			preparedStatement.setString(6, cncProgram.getComment());
			preparedStatement.setBoolean(7, cncProgram.isActive());
			preparedStatement.setTimestamp(8, (Timestamp) cncProgram.getDate());
			logger.log(Level.DEBUG, "cnc program is updated", cncProgram.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.setInt(1, id);
			logger.log(Level.DEBUG, "cnc program with id {} is deleted", id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public CncProgram find(String name) throws DaoException {
		CncProgram cncProgram = new CncProgram();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cncProgram.setId(resultSet.getInt(1));
				cncProgram.setProgramText(resultSet.getString(2));
				cncProgram.setName(resultSet.getString(3));
				cncProgram.setOperationNumber(resultSet.getInt(4));
				cncProgram.setFileExtension(resultSet.getString(5));
				cncProgram.setComment(resultSet.getString(6));
				cncProgram.setActive(resultSet.getBoolean(7));
				cncProgram.setDate(resultSet.getDate(8));
				logger.log(Level.DEBUG, "cnc program is read", cncProgram.toString());
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

	private CncProgram buildCncProgram(ResultSet resultSet) throws SQLException {
		CncProgram cncProgram = new CncProgram();
		cncProgram.setId(resultSet.getInt(ColumnName.PROGRAM_ID));
		cncProgram.setProgramText(resultSet.getString(ColumnName.PROGRAM_TEXT));
		cncProgram.setName(resultSet.getString(ColumnName.PROGRAM_NAME));
		cncProgram.setOperationNumber(resultSet.getInt(ColumnName.OPERATION_NUMBER));
		cncProgram.setFileExtension(resultSet.getString(ColumnName.PROGRAM_FILE_EXTENSION));
		cncProgram.setComment(resultSet.getString(ColumnName.COMMENT));
		cncProgram.setActive(resultSet.getBoolean(ColumnName.ACTIVE));
		cncProgram.setDate(new java.util.Date());
		logger.log(Level.DEBUG, "cnc program build successfully, cncProgram: {}", cncProgram.toString());
		return cncProgram;
	}

}
