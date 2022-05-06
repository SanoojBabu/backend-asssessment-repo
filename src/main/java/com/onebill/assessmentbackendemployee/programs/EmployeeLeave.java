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
@Table(name = "employee_leave")
public class EmployeeLeave {

	

	public EmployeeLeave(String date, int employeeId) {
		super();
		this.date = date;
		this.employeeId = employeeId;
	}

	@Id
	@Column(name = "leave_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;

	@Column(name = "leave_date")
	private String date;

	@Column(name = "leave_status")
	private String leaveStatus = "Pending";

	@Column(name = "employee_id")
	private int employeeId;

}