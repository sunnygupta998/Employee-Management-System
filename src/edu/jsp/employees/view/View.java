package edu.jsp.employees.view;

import java.util.Scanner;

import edu.jsp.employees.model.*;
import edu.jsp.employees.controller.Controller;

public class View {

	boolean loop = true;
	Scanner scanner = new Scanner(System.in);
	Controller controller = new Controller();

	public static void main(String args[]) {

		View view = new View();
		while (view.loop) {
			view.mainView();
		}

	}

	public void mainView() {

		System.out.println("*******WELCOME*******\n"
		+"1. Save Employee \n" 
		+ "2. View Employees byId\n" 
		+ "3. View All Employee \n"
		+ "4. Update Employee Salary\n"
		+ "5. Delete Employee\n" 
		+ "6. Exit\n");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		System.out.println("------------------------------------------------------------------");
		switch (choice) {
		case 1:
			saveEmployee();
			break;

		case 2:
			getEmployee();
			break;

		case 3:
			getAllEmployee();
			break;

		case 4:
			updateEmployee();
			break;

		case 5:
			deleteEmployee();
			break;

		case 6:
			this.loop = false;
			System.out.println("Thank You Come Again");
			break;
		default:
			System.out.println("Invalid choice. Please enter a valid option.");
		}
	}

	public void saveEmployee() {

		System.out.println("Enter employee ID:");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume the newline

		System.out.println("Enter employee Name:");
		String name = scanner.nextLine();

		System.out.println("Enter employee Salary:");
		int sal = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter employee Contect:");
		long contact = scanner.nextLong();
		scanner.nextLine();

		Employee employee = new Employee(id, name, sal, contact);

		if (controller.saveEmployee(employee) != null) {

			System.out.println("Employee saved \n");
		} else {
			System.out.println("Somethink went worng \n");
		}
		System.out.println("--------------------------------------------------------------");
	}

	public void getEmployee() {
		System.out.print("Enter employee ID:");
		int id = scanner.nextInt();
		scanner.nextLine();

		Employee employee;
		if ((employee = (controller.getEmployee(id))) != null) {

			System.out.print("Employee id :" + employee.getId()+"\n");
			System.out.print("Employee name :" + employee.getName()+"\n");
			System.out.print("Employee Salary :" + employee.getSalary()+"\n");
			System.out.print("Employee contect :" + employee.getContact()+"\n");
			System.out.println("------------------------------------------------------------------");
		}

	}

	public void getAllEmployee() {

		if (controller.getAllEmployee().isEmpty()) {
			System.out.println("No Employee Data Present \n");
			return;
		}
		sortEmployee();
		for (Employee employee : controller.getAllEmployee()) {
			System.out.print("Employee id :" + employee.getId()+"\n");
			System.out.print("Employee name :" + employee.getName()+"\n");
			System.out.print("Employee Salary :" + employee.getSalary()+"\n");
			System.out.print("Employee contect :" + employee.getContact()+"\n");
			System.out.println("------------------------------------------------------------------");
		}
	}

	public void deleteEmployee() {
		getAllEmployee();

		System.out.println("Enter Employee id:\n");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter Employee Name:\n");
		String name = scanner.next();
		scanner.nextLine();

		System.out.println("Enter Employee Salary:\n");
		int salary = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter Employee Contact:\n");
		long contact = scanner.nextLong();
		scanner.nextLine();

		Employee employee = new Employee(id, name, salary, contact);

		for (Employee employee1 : controller.getAllEmployee()) {
			if (employee1.getId() == employee.getId()) {
				if (employee1.getName().equalsIgnoreCase(employee.getName())) {
					if (employee1.getSalary() == employee.getSalary()) {
						if (employee1.getContact() == employee.getContact()) {
							controller.deleteEmployee(employee);
							System.out.println("Employee Deleted");
							return;
						}
					}
				}
			} else {
				System.out.println("Employee Not Found");
				return;
			}
		}
	}

	public void updateEmployee() {
		getAllEmployee();

		System.out.println("Enter the employee id you want to update\n");
		int id = scanner.nextInt();
		scanner.nextLine();
		for (Employee employee : controller.getAllEmployee()) {
			if (id == employee.getId()) {
				Employee employee1 = employee;
				System.out.println("1.update id \n" 
						+ "2.Update Name \n" 
						+ "3.Update Salary \n" 
						+ "4.Update Contact \n"
						+ "5 Update All Data \n");
				int choies = scanner.nextInt();
				switch (choies) {
				case 1: {
						
					System.out.println("Enter Employee id:\n");
					int id1 = scanner.nextInt();
					scanner.nextLine();
					
					employee1.setId(id1);
					break;
				}
				case 2: {
					
					System.out.println("Enter Employee Name:\n");
					String name = scanner.next();
					scanner.nextLine();
					
					employee1.setName(name);
					break;
				}
				case 3: {

					System.out.println("Enter Employee Salary:\n");
					int salary = scanner.nextInt();
					scanner.nextLine();
					employee1.setSalary(salary);
					break;
				}
				case 4: {
					
					System.out.println("Enter Employee Contact:\n");
					Long contact = scanner.nextLong();
					scanner.nextLine();
					employee1.setContact(contact);
					break;

				}
				case 5: {
					System.out.println("Enter Employee id:\n");
					int id1 = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Employee Name:\n");
					String name = scanner.next();
					scanner.nextLine();
					System.out.println("Enter Employee Salary:\n");
					int salary = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Employee Contact:\n");
					Long contact = scanner.nextLong();
					scanner.nextLine();

					employee1.setId(id1);
					employee1.setName(name);
					employee1.setSalary(salary);
					employee1.setContact(contact);
					System.out.println("Employee Details Updated");

					break;
				}
				default:

				}
			}

		}

	}
	public void sortEmployee() {
		
		System.out.println("Enter the sorting based on\n"
		+"1.Sort on id\n"
		+"2.sort on name\n"
		+"3.sort on Salary\n"
		+"4.sort on Contact\n"
		+"5.Don't sort");
	int sortedChoice= scanner.nextInt();
	scanner.nextLine();
	
	switch (sortedChoice) {
	case 1: {
		controller.sortedEmployee(new SortById());
		break;
	}
	case 2: {
		controller.sortedEmployee(new SortByName());
		break;
	}
	case 3: {
		
		controller.sortedEmployee(new SortBySalary());
		break;
	}
	case 4: {
		
		controller.sortedEmployee(new SortByContact());
		break;
	}
	default:System.out.println("Invalid");
	}
	}
}
