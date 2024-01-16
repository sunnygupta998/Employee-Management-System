package edu.jsp.employees.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.jsp.connectionpool.ConnectionPool;
import edu.jsp.employees.model.Employee;

public class Controller {

	
	PreparedStatement statement;
	//add employee
	Connection connection=ConnectionPool.getConnection();
	
	public Employee saveEmployee(Employee employee) {
		try {
			statement= connection.prepareStatement("INSERT INTO public.\"Employees\"("
					+ "	id, name, salary, contact)"
					+ "	VALUES (?, ?, ?, ?);");
			
			int id= employee.getId();
			String name=employee.getName();
			double salary=employee.getSalary();
			long contact=employee.getContact();
			
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setDouble(3, salary);
			statement.setLong(4, contact);
			statement.executeUpdate();
			statement.close();
			ConnectionPool.recieveConnection(connection);
			return employee;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// delete employee
	
	public int deleteEmployee(Employee emp) {
		
		String sql="DELETE FROM public.\"Employees\""
				+ "	WHERE id=?;"; 
				int result=0;
			try {
			    statement = connection.prepareStatement(sql);
				int id= emp.getId();
				statement.setInt(1, id);
				 result=statement.executeUpdate();
				statement.close();
				ConnectionPool.recieveConnection(connection);
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		if(emp != null) {
//			for(Employee employee: employees) {
//				if(employee.getId() == emp.getId()) {
//					employees.remove(employee);
//					return employee;
//				}
//			}
//			return null;
//		}else {
//			return null;
//		}
			return result;
	}
	
	// view employee 
	
	public List<Employee>getAllEmployee(int choice){
		List<Employee> employees = new ArrayList<>();
		
		
		String sql="SELECT * FROM public.\"Employees\"";
		switch (choice) {
		case 1:
			sql+="ORDER BY id";
			break;
		case 2:
			sql+="ORDER BY name";
			break;
		case 3:
			sql+="ORDER BY salary";
			break;
		case 4:
			sql+="ORDER BY contact";
			break;

		default:
			break;
		}
		try {
			
			statement=connection.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				int id=resultSet.getInt(1);
				String name=resultSet.getString(2);
				double salary=resultSet.getDouble(3);
				long contact = resultSet.getLong(4);
				Employee employee = new Employee(id, name, salary, contact);
		        employees.add(employee);
			}
			statement.close();
			ConnectionPool.recieveConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return employees;
	}
	
	// view employee by id
	
	public Employee getEmployee(int id){
		  Employee employee = null;
		try {
			
			statement=connection.prepareStatement("select * from PUBLIC.\"Employees\"where id=?");
			statement.setInt(1, id);
			ResultSet resultSet=statement.executeQuery();
			if (resultSet.next()) {
				int employeeId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				long contact = resultSet.getLong("contact");
				employee = new Employee(employeeId, name, salary, contact);
				return employee; 
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	 
			return null;
	}
	
	
	public void updateEmployee(Employee employee, int choice, int id) {
	    String sql = "";

	    switch (choice) {
	        case 1:
	            sql = "UPDATE public.\"Employees\" SET id=? WHERE id=?";
	            break;
	        case 2:
	            sql = "UPDATE public.\"Employees\" SET name=? WHERE id=?";
	            break;
	        case 3:
	            sql = "UPDATE public.\"Employees\" SET salary=? WHERE id=?";
	            break;
	        case 4:
	            sql = "UPDATE public.\"Employees\" SET contact=? WHERE id=?";
	            break;
	        default:
	            break;
	    }

	    try {
	        statement = connection.prepareStatement(sql);

	        // Set parameters based on the choice
	        switch (choice) {
	            case 1:
	                statement.setInt(1, employee.getId());
	                break;
	            case 2:
	                statement.setString(1, employee.getName());
	                break;
	            case 3:
	                statement.setDouble(1, employee.getSalary());
	                break;
	            case 4:
	                statement.setLong(1, employee.getContact());
	                break;
	            default:
	                break;
	        }

	        // Set the id parameter
	        statement.setInt(2, id);

	        // Execute the update statement
	        statement.executeUpdate();
	        statement.close();
	        ConnectionPool.recieveConnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
