package com.onebill.assessmentbackendemployee.programs;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AppConsole {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PUEMP01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Scanner scanner = new Scanner(System.in);

		int i = 0;
		while (i != 1) {
			System.out.println("EMPLOYEE FORUM");
			System.out.println("--------------");
			System.out.println();
			System.out.println("1.Press 1 to enter a new Employee Details");
			System.out.println("2.Press 2 to Login");
			System.out.println("3.Press any other key to exit");
			int option1 = scanner.nextInt();
			switch (option1) {
			case 1: {
				System.out.println("Enter the Employee name:");
				String name = scanner.next();
				scanner.nextLine();
				System.out.println("Enter the Employee type:");
				String type = scanner.nextLine();
				scanner.nextLine();
				System.out.println("Enter the email-id:");
				String email = scanner.nextLine();
				scanner.nextLine();
				System.out.println("Enter the password:");
				String password = scanner.nextLine();
				scanner.nextLine();
				Employee employee = new Employee(name, type, email, password);
				transaction.begin();
				manager.persist(employee);
				transaction.commit();
			}
				break;
			case 2: {
				System.out.println("Enter the Employee id:");
				int id = scanner.nextInt();
				String check = "from Employee where employeeId=:id";
				Query query = manager.createQuery(check);
				query.setParameter("id", id);
				Employee employee = (Employee) query.getSingleResult();
				String uPassword = employee.getPassword();
				int k = 0;
				while (k != 1) {
					System.out.println("Enter the password");
					String password = scanner.next();
					if (password.equals(uPassword))
						k = 1;
					else
						System.out.println("Enter the correct password");
				}
				String typecheck = "select employeeType from Employee where employeeId=:id";
				Query query1 = manager.createQuery(typecheck);
				query1.setParameter("id", id);
				String type = (String) query1.getSingleResult();
				String typelow = type.toLowerCase();
				int j = 0;
				while (j != 1) {
					switch (typelow) {
					case "manager": {
						System.out.println("1.Press 1 to show all leave requests");
						System.out.println("2.press 2 to Approve/Reject the leave request");
						System.out.println("3.Press 3 to exit to main menu");
						int option2 = scanner.nextInt();
						switch (option2) {
						case 1: {
							String show = "from EmployeeLeave";
							Query listQuery = manager.createQuery(show);
							List list = listQuery.getResultList();
							for (Object object : list) {
								EmployeeLeave employeeleave = (EmployeeLeave) object;
								System.out.println("=> " + employeeleave);
							}
						}

							break;
						case 2: {
							System.out.println("Enter the leave id");
							int leaveId = scanner.nextInt();
							String leave = "select leaveStatus from EmployeeLeave where leaveId=:id";
							Query leaveQuery = manager.createQuery(leave);
							leaveQuery.setParameter("id", leaveId);
							System.out.println("to Approve Press 1");
							System.out.println("to Reject Press 2");
							int leaveStatus = scanner.nextInt();
							String status = "";
							String update = "";
							if (leaveStatus == 1) {
								update = "Approved";
								status = "update EmployeeLeave set leaveStatus=:n where leaveId=:id";
							} else if (leaveStatus == 2) {
								update = "Rejected";
								status = "update EmployeeLeave set leaveStatus=:n where leaveId=:id";
							} else
								update = "Pending";
							Query permitQuery = manager.createQuery(status);
							permitQuery.setParameter("id", leaveId);
							permitQuery.setParameter("n", update);
							transaction.begin();
							permitQuery.executeUpdate();
							transaction.commit();
						}

							break;
						case 3:
							j = 1;
							break;
						default:
							System.out.println("Invalid Input! Enter Again");
							break;
						}
					}
						break;
					case "employee": {
						System.out.println("1.Press 1 to show status of applied leave requests");
						System.out.println("2.Press 2 to apply new leave request");
						System.out.println("3.Press 3 to exit to main menu");
						int leaveReq = scanner.nextInt();
						switch (leaveReq) {
						case 1: {
							String leaveList = "from EmployeeLeave where employeeId=:id";
							Query leaveQuery = manager.createQuery(leaveList);
							leaveQuery.setParameter("id", id);
							List listLeave = leaveQuery.getResultList();
							for (Object object : listLeave) {
								EmployeeLeave leave = (EmployeeLeave) object;
								System.out.println("=> " + leave);
							}
						}
							break;
						case 2: {
							System.out.println("Enter the date of leave");
							String date = scanner.next();
							EmployeeLeave leave = new EmployeeLeave(date, id);
							transaction.begin();
							manager.persist(leave);
							transaction.commit();
						}
							break;
						case 3:
							j = 1;
							break;
						default:
							System.out.println("Invalid Input!");
							break;
						}
					}
						break;
					default:
						break;
					}
				}
			}
				break;
			default:
				i = 1;
				break;
			}
		}
	}
}
