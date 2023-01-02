package com.qa.trcrm.utils;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
/*
 * Jira Integration with Selenium || Raise a bug for failure test cases || No Manual Bug in JIRA
 * how to use JIRA Client APIs to raise a bug in JIRA for failure test cases in Selenium. 
 * No need to raise a bug manually.
 * Auto logging of issues/bugs/defects in JIRA
 */
public class JiraServiceProvider {

	public JiraClient jira;
	public String project;

	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password.replace("/", ""));
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description, String reporterName) {

		try {
			FluentCreate fleuntCreate = jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
			//fleuntCreate.field(Field.REPORTER, reporterName);
			Issue newIssue = fleuntCreate.execute();
			System.out.println("new issue created in jira with ID: " + newIssue);
			Log.info("new issue created in jira with ID: " + newIssue);

		} catch (JiraException e) {
			e.printStackTrace();
		}

	}

}