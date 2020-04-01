package com.learn.issuetracker.model;

/*
 * Model class for storing Employee details. Complete the code as per the comments given below
*/
public class Employee {

	private int emplId;
	private String name;
	private String location;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + emplId;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (emplId != other.emplId)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Employee() {
		// Default Constructor
	}

	/*
	 * Complete the parameterized Constructor
	 */
	public Employee(int emplId, String name, String location) {
		this.emplId=emplId;
		this.name=name;
		this.location=location;

	}

	/*
	 * Override toString() here . The toString() should return the employee details
	 * in the below format
	 * 
	 * Employee : {Employee Id : xxx; Name : xxxx; Location : xxxxx}
	 */

	@Override
	public String toString() {
		return "Employee :{Employee Id : " + emplId + "; Name=" + name + "; Location=" + location + "]";
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/*
	 * Complete the Getter and Setters
	 */
	

}