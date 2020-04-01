package com.learn.issuetracker.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.learn.issuetracker.exceptions.IssueNotFoundException;
import com.learn.issuetracker.model.Employee;
import com.learn.issuetracker.model.Issue;
import com.learn.issuetracker.repository.IssueRepository;

/*
 * This class contains functionalities for searching and analyzing Issues data Which is stored in a collection
 * Use JAVA8 STREAMS API to do the analysis
 * 
*/
public class IssueTrackerServiceImpl implements IssueTrackerService {

	/*
	 * CURRENT_DATE contains the date which is considered as todays date for this
	 * application Any logic which uses current date in this application, should
	 * consider this date as current date
	 */
	private static final String CURRENT_DATE = "2019-05-01";

	/*
	 * The issueDao should be used to get the List of Issues, populated from the
	 * file
	 */
	private IssueRepository issueDao;
	private LocalDate today;

	/*
	 * Initialize the member variables Variable today should be initialized with the
	 * value in CURRENT_DATE variable
	 */
	public IssueTrackerServiceImpl(IssueRepository issueDao) {
		this.issueDao = issueDao;
		this.today = LocalDate.parse(CURRENT_DATE);

	}

	/*
	 * In all the below methods, the list of issues should be obtained by used
	 * appropriate getter method of issueDao.
	 */
	/*
	 * The below method should return the count of issues which are closed.
	 */
	@Override
	public long getClosedIssueCount() {
		List<Issue> all = issueDao.getIssues();
		long count = 0;
		for (Issue issue : all) {
			if (issue.getStatus().equalsIgnoreCase("closed"))
				count++;
		}
		return count;
	}

	/*
	 * The below method should return the Issue details given a issueId. If the
	 * issue is not found, method should throw IssueNotFoundException
	 */

	@Override
	public Issue getIssueById(String issueId) throws IssueNotFoundException {
		for (Issue temp : issueDao.getIssues()) {
			if (temp.getIssueId().equals(issueId))

				return temp;
		}
		throw new IssueNotFoundException();

	}

	/*
	 * The below method should return the Employee Assigned to the issue given a
	 * issueId. It should return the employee in an Optional. If the issue is not
	 * assigned to any employee or the issue Id is incorrect the method should
	 * return empty optional
	 */
	@Override
	public Optional<Employee> getIssueAssignedTo(String issueId) {
		for (Issue temp : issueDao.getIssues()) {
			if (temp.getIssueId().equals(issueId))
				return Optional.of(temp.getAssignedTo());
		}

		return Optional.empty();
	}

	/*
	 * The below method should return the list of Issues given the status. The
	 * status can contain values OPEN / CLOSED
	 */
	@Override
	public List<Issue> getIssuesByStatus(String status) {
		List<Issue> temp = new ArrayList<Issue>();
		for (Issue ans : issueDao.getIssues()) {
			if (ans.getStatus().equals(status)) {
				temp.add(ans);
			}
		}

		return temp;

	}

	/*
	 * The below method should return a LinkedHashSet containing issueid's of open
	 * issues in the ascending order of expected resolution date
	 */
	@Override
	public Set<String> getOpenIssuesInExpectedResolutionOrder() {

		Set<String> temp = new LinkedHashSet<String>();
		/* Map<String,LocalDate> mp=new HashMap<String,LocalDate>(); */
		List<Issue> issue = new ArrayList<Issue>();
		for (Issue ans : issueDao.getIssues()) {
			if (ans.getStatus().equalsIgnoreCase("open")) {
				issue.add(ans);
			}
		}
		issue.sort((Issue s1, Issue s2) -> s1.getExpectedResolutionOn().compareTo(s2.getExpectedResolutionOn()));

		for (Issue issuef : issue) {
			temp.add(issuef.getIssueId());
		}

		return temp;
	}

	/*
	 * The below method should return a List of open Issues in the descending order
	 * of Priority and ascending order of expected resolution date within a priority
	 */
	@Override
	public List<Issue> getOpenIssuesOrderedByPriorityAndResolutionDate() {

		List<Issue> issue = new ArrayList<Issue>();
		issue = issueDao.getIssues();

		List<Issue> openIssue = issue.stream().filter(d -> d.getStatus().equalsIgnoreCase("open"))
				.collect(Collectors.toList());
		openIssue = openIssue.stream().sorted(
				Comparator.comparing(Issue::getPriority).reversed().thenComparing(Issue::getExpectedResolutionOn))
				.collect(Collectors.toList());
		return openIssue;
	}

	/*
	 * The below method should return a List of 'unique' employee names who have
	 * issues not closed even after 7 days of Expected Resolution date. Consider the
	 * current date as 2019-05-01
	 */
	@Override

	public List<String> getOpenIssuesDelayedbyEmployees() {
		List<Issue> issue = issueDao.getIssues();
		LocalDate today = LocalDate.of(2019, 05, 01);

		List<Issue> uniqueEmployee = issue.stream().filter(d -> d.getStatus().equalsIgnoreCase("open")).distinct()
				.collect(Collectors.toList());
		List<Issue> ansList = new ArrayList<Issue>();
		List<String> empName = new ArrayList<String>();
		for (Issue unique : uniqueEmployee) {
			LocalDate date2 = unique.getExpectedResolutionOn();
			if (date2.compareTo(today) >= 7) {
				ansList.add(unique);
			}
		}

		for (Issue name : ansList) {
			empName.add(name.getAssignedTo().getName());
		}

		return empName;

	}

	/*
	 * The below method should return a map with key as issueId and value as
	 * assigned employee Id. THe Map should contain details of open issues having
	 * HIGH priority
	 */
	@Override
	public Map<String, Integer> getHighPriorityOpenIssueAssignedTo() {
		Map<String, Integer> ansmp = new HashMap<String, Integer>();
		List<Issue> issue = issueDao.getIssues();
		List<Issue> openAndHighpriority = issue.stream().filter(d -> d.getStatus().equalsIgnoreCase("open"))
				.filter(e -> e.getPriority().equalsIgnoreCase("HIGH")).collect(Collectors.toList());
		for (Issue tempList : openAndHighpriority) {
			ansmp.put(tempList.getIssueId(), tempList.getAssignedTo().getEmplId());
			;
		}

		return ansmp;
	}

	/*
	 * The below method should return open issues grouped by priority in a map. The
	 * map should have key as issue priority and value as list of open Issues
	 */
	@Override
	public Map<String, List<Issue>> getOpenIssuesGroupedbyPriority() {
		List<Issue> issue = issueDao.getIssues();
		Map<String, List<Issue>> openIssuesGroupedbyPriority = new HashMap<String, List<Issue>>();
		List<Issue> openIssue = issue.stream().filter(d -> d.getStatus().equalsIgnoreCase("open"))
				.collect(Collectors.toList());
		openIssuesGroupedbyPriority = openIssue.stream().collect(Collectors.groupingBy(Issue::getPriority));

		return openIssuesGroupedbyPriority;
	}

	/*
	 * The below method should return count of open issues grouped by priority in a
	 * map. The map should have key as issue priority and value as count of open
	 * issues
	 */
	@Override
	public Map<String, Long> getOpenIssuesCountGroupedbyPriority() {
		List<Issue> issue = issueDao.getIssues();
		Map<String, Long> openIssuesGroupedbyPriority = new HashMap<String, Long>();
		List<Issue> openIssue = issue.stream().filter(d -> d.getStatus().equalsIgnoreCase("open"))
				.collect(Collectors.toList());
		openIssuesGroupedbyPriority = openIssue.stream()
				.collect(Collectors.groupingBy(Issue::getPriority, Collectors.counting()));

		return openIssuesGroupedbyPriority;
	}

	/*
	 * The below method should provide List of issue id's(open), grouped by location
	 * of the assigned employee. It should return a map with key as location and
	 * value as List of issue Id's of open issues
	 */

	@Override
	public Map<String, List<String>> getOpenIssueIdGroupedbyLocation() {
		return issueDao.getIssues().stream().filter(f -> f.getStatus().equalsIgnoreCase("OPEN"))
				.collect(Collectors.groupingBy(iss -> iss.getAssignedTo().getLocation(),
						Collectors.mapping(Issue::getIssueId, Collectors.toList())));
	}

	/*
	 * The below method should provide the number of days, since the issue has been
	 * created, for all high/medium priority open issues. It should return a map
	 * with issueId as key and number of days as value. Consider the current date as
	 * 2019-05-01
	 */
	@Override
	public Map<String, Long> getHighMediumOpenIssueDuration() {
		
		return issueDao.getIssues().stream()
					   .filter(i -> (i.getPriority().equalsIgnoreCase("high") || i.getPriority().equalsIgnoreCase("medium"))
							   		 && i.getStatus().equalsIgnoreCase("open"))
					   .collect(Collectors.toMap(Issue::getIssueId, f -> java.time.temporal.ChronoUnit.DAYS.between(f.getCreatedOn(), today)));
	}
}