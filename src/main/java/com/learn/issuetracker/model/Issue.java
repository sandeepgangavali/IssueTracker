package com.learn.issuetracker.model;

import java.time.LocalDate;

/*
 * Model class for storing Issue Object
*/

public class Issue {

	private String issueId;
	private String summary;
	private LocalDate createdOn;
	private LocalDate expectedResolutionOn;
	private String priority;
	private String status;
	private Employee assignedTo;

	public Issue() {
		//Default Constructor
	}

	/*
	 * Complete the parameterized Constructor
	 */
public Issue(String issueId, String summary, LocalDate createdOn, LocalDate expectedResolutionOn, String priority,
			String status, Employee assignedTo) {
		super();
		this.issueId = issueId;
		this.summary = summary;
		this.createdOn = createdOn;
		this.expectedResolutionOn = expectedResolutionOn;
		this.priority = priority;
		this.status = status;
		this.assignedTo = assignedTo;
	}

   

	public String getIssueId() {
	return issueId;
}

public void setIssueId(String issueId) {
	this.issueId = issueId;
}

public String getSummary() {
	return summary;
}

public void setSummary(String summary) {
	this.summary = summary;
}

public LocalDate getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(LocalDate createdOn) {
	this.createdOn = createdOn;
}

public LocalDate getExpectedResolutionOn() {
	return expectedResolutionOn;
}

public void setExpectedResolutionOn(LocalDate expectedResolutionOn) {
	this.expectedResolutionOn = expectedResolutionOn;
}

public String getPriority() {
	return priority;
}

public void setPriority(String priority) {
	this.priority = priority;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Employee getAssignedTo() {
	return assignedTo;
}

public void setAssignedTo(Employee assignedTo) {
	this.assignedTo = assignedTo;
}

	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", summary=" + summary + ", createdOn=" + createdOn
				+ ", expectedResolutionOn=" + expectedResolutionOn + ", priority=" + priority + ", status=" + status
				+ ", assignedTo=" + assignedTo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedTo == null) ? 0 : assignedTo.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((expectedResolutionOn == null) ? 0 : expectedResolutionOn.hashCode());
		result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		Issue other = (Issue) obj;
		if (assignedTo == null) {
			if (other.assignedTo != null)
				return false;
		} else if (!assignedTo.equals(other.assignedTo))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (expectedResolutionOn == null) {
			if (other.expectedResolutionOn != null)
				return false;
		} else if (!expectedResolutionOn.equals(other.expectedResolutionOn))
			return false;
		if (issueId == null) {
			if (other.issueId != null)
				return false;
		} else if (!issueId.equals(other.issueId))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}

	
	
	
}