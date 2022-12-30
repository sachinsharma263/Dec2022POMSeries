package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By contactPageHeader = By.xpath("(//h2[text()])[1]");

	By addPersonButton = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");
	By name = By.name("name");
	By emailId = By.id("email0");
	By saveButton = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");
	By personAddedMsg = By.xpath("//span[text()='Person added.']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String contactPageTitle() {
		return util.doGetTitle();
	}

	public String verifyContactPageHeader() {
		util.waitForPresenceOfELement(contactPageHeader);
		return util.doGetText(contactPageHeader);
	}

	public String addPerson(String firstName, String email) {

		util.waitForPresenceOfELement(addPersonButton);
		util.doClick(addPersonButton);
		util.waitForPresenceOfELement(name);
		util.doActionSenkeys(name, firstName);
		util.doSendKeys(emailId, email);
		util.doClick(saveButton);

		util.waitForPresenceOfELement(personAddedMsg);
		return util.doGetText(personAddedMsg);
	}

	public String addPerson(Contacts contacts) {

		util.waitForPresenceOfELement(addPersonButton);
		util.doClick(addPersonButton);
		util.waitForPresenceOfELement(name);
		util.doActionSenkeys(name, contacts.getName());
		util.doSendKeys(emailId, contacts.getEmail());
		util.doClick(saveButton);

		util.waitForPresenceOfELement(personAddedMsg);
		return util.doGetText(personAddedMsg);
	}

}
