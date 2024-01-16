package edu.jsp.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private static final int POOL_SIZE = 5;
	
	private static List<Connection>connection= new ArrayList<>();
	private static String user="postgres";
	private static String password="root";
	private static String dburl="jdbc:postgresql://localhost:5432/jdbc";
	static Connection connection1;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			
			for (int i = 0; i < POOL_SIZE; i++) {
				
				connection.add(createConnection());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection() {
		
			try { 
				connection1= DriverManager.getConnection(dburl, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return connection1;
	}
	
	public static Connection getConnection() {
		
		if(!connection.isEmpty()) {
			return connection.remove(0);
		}else {
			return createConnection();
		}
	}
	
	public static void recieveConnection(Connection connections) {
		
		if (connection.size()<=POOL_SIZE) {
			connection.add(connections);
		}else {
			try {
				connections.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
