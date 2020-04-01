package com.learn.issuetracker.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.learn.issuetracker.model.Employee;
import com.learn.issuetracker.repository.EmployeeRepository;

public class EmployeeRepositoryTest {

	private Employee employee;

	@After
	public void cleanUp() {
		employee = null;
	}

	@Test
	public void testEmployeeBean() {
		new BeanTester().testBean(Employee.class);
	}

	@Test
	public void testinitializeEmployeesFromFile() throws IOException {
		EmployeeRepository.initializeEmployeesFromFile(Paths.get("src", "data", "employees.csv"));

		List<Employee> emplList = EmployeeRepository.getEmployees();
		assertNotNull("This method should assign the list of employees read from the file to employees variable"
				+ " and should not be null", emplList);
		assertFalse("The employees list should contain the data read from the file", emplList.isEmpty());
	}

	@Test
	public void testGetEmployeeSuccess() {
		Optional<Employee> optEmployee = EmployeeRepository.getEmployee(101);
		employee = optEmployee.orElse(null);
		assertNotNull("Check whether the employee is present in the the employees List. "
				+ "This should be should be populated with the file data and should not be null", employee);
	}
}