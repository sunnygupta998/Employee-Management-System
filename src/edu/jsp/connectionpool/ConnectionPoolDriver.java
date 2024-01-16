package edu.jsp.connectionpool;

public class ConnectionPoolDriver {
	
	
	public static void main(String[] args) {
		
		
		ConnectionPool connectionPool=new ConnectionPool();
		System.out.println();
		
		System.out.println(connectionPool.getConnection()); 
		System.out.println(connectionPool.getConnection());
	}
}
