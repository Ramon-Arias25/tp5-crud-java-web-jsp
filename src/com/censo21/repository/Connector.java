package com.censo21.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 */
public class Connector {

	private static Connection connection = null;
	/**
	 * Clase que genera una conecion solo si no existe
	 * los parametros url - username - password deben ser adaptados al entorno.
	 */
	public static Connection getConnection() {
		if (connection == null) {
			try {
				String dbUrl="jdbc:mysql://localhost:3306/tp5";
				String dbUserName = "neoris";
				String dbPassword = "1234";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);			
				System.out.println("DB is connected from jdbc.java to: " + dbUrl);
				System.out.println(connection.toString());
				
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
			}
		}
		return connection;
	}


}
