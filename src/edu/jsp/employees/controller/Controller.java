package edu.jsp.employees.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import edu.jsp.employees.model.*;
import edu.jsp.employees.model.Employee;

public class Controller {

	
	private List<Employee> employees = new ArrayList<>();
	
	
	//add employee
	
	public Employee saveEmployee(Employee employee) {
		 
		if(employee !=null) {
			employees.add(employee);
			return employee;
		}else {
			return null;
		}
	}
	
	// delete employee
	
	public Employee deleteEmployee(Employee emp) {
		
		if(emp != null) {
			for(Employee employee: employees) {
				if(employee.getId() == emp.getId()) {
					employees.remove(employee);
					return employee;
				}
			}
			return null;
		}else {
			return null;
		}
	}
	
	// view employee 
	
	public List<Employee>getAllEmployee(){
		return employees;
		
	}
	
	// view employee by id
	
	public Employee getEmployee(int id){
		
		if(id >0) {
			for(Employee employee:employees) {
				if(employee.getId()== id) {
					return employee;
				}
			}
			return null;
		}else {
			return null;
		}
	}
	
	
	public List<Employee> sortedEmployee(Comparator<Employee>comparator) {
		
		if (comparator instanceof SortById) {
			Collections.sort(employees,comparator);
			
			return employees;
		}
		else if (comparator instanceof SortByName) {
			Collections.sort(employees,comparator);
			
			return employees;
		}
		else if (comparator instanceof SortBySalary) {
			Collections.sort(employees,comparator);
			
			return employees;
		}
		else if (comparator instanceof SortByContact) {
			Collections.sort(employees,comparator);
			
			return employees;
		}else {
			return employees;
		}
	}
}
