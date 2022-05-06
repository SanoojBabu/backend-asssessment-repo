package com.onebill.assessmentbackendemployee.programs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_info")
public class Employee {

	public Employee(String employeeName, String employeeType, String email, String password) {
		super();
		this.employeeName = employeeName;
		this.employeeType = employeeType;
		this.email = email;
		this.password = password;
	}

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_type")
	private String employeeType;

	@Column(name = "email_id")
	private String email;

	@Column(name = "password")
	private String password;
}
