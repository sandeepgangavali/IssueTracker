package com.learn.issuetracker.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.learn.issuetracker.model.Employee;
import com.learn.issuetracker.model.Issue;


/*
 * This class has methods for parsing the String read from the files in to corresponding Model Objects
*/
public class Utility {
	
	static int index = 0;
	 
	
	private Utility() {
		//Private Constructor to prevent object creation
	}
	
	private static LocalDate getLocalDateTime(String dateToFormat) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dateToFormat, formatter);
		}


	/*
	 * parseEmployee takes a string with employee details as input parameter and parses it in to an Employee Object 
	*/
	public static Employee parseEmployee(String employeeDetail) {
		List<String> empDetails  = Arrays.stream(employeeDetail.split(","))
				.map(String::trim)
				.collect(Collectors.toList());
				Employee e = new Employee();
				e.setEmplId(Integer.parseInt(empDetails.get(0)));
				e.setName(empDetails.get(1));
				e.setLocation(empDetails.get(2));
				return e;
	}

	/*
	 * parseIssue takes a string with issue details and parses it in to an Issue Object. The employee id in the 
	 * Issue details is used to search for an an Employee, using EmployeeRepository class. If the employee is found
	 * then it is set in the Issue object. If Employee is not found, employee is set as null in Issue Object  
	*/

	public static Issue parseIssue(String issueDetail) {
		List<String> issueDetails = Arrays.stream(issueDetail.split(",")).map(String::trim)
				.collect(Collectors.toList());
				Issue issue = new Issue();
				issue.setIssueId(issueDetails.get(0));
				issue.setSummary(issueDetails.get(1));
				issue.setCreatedOn(getLocalDateTime(issueDetails.get(2)));
				issue.setExpectedResolutionOn(getLocalDateTime(issueDetails.get(3)));
				issue.setPriority(issueDetails.get(4));
				issue.setStatus((issueDetails.get(5)));
				EmployeeRepository e = new EmployeeRepository();
				Optional<Employee> employee = e.getEmployee(Integer.parseInt(issueDetails.get(6)));

				if(employee.isPresent()) {
				issue.setAssignedTo(employee.get());
				}else {
				issue.setAssignedTo(null);
				}


				return issue;

				}
	
	}
