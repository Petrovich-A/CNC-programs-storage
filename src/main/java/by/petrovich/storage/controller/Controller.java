package by.petrovich.storage.controller;

import by.petrovich.storage.dao.ConnectionPool;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("select * from users_roles");
			System.out.println("Tables from users");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id"));
				System.out.println(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
