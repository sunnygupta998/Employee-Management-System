package edu.jsp.employees.model;

import java.util.Comparator;

public class SortByContact implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return (int)(o1.getContact()-o2.getContact());
	}

}
