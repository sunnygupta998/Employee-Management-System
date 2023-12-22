package edu.jsp.employees.model;

public class Employee {

	 private int id;
	  private String name;
	  private double salary;
	  private long contact;
	  
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public long getContact() {
		return contact;
	}
	  
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public Employee(int id, String name, double salary,Long contact) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.contact = contact;
		
	}
	public Employee() {
		super();
	}

	
}
