package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.ContactsPage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ExcelUtil;

public class ContactsPageTest {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	Credentials credentials;
	HomePage homePage;
	ContactsPage contactsPage;
	Contacts contacts;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		credentials = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentials);
		contactsPage = homePage.clickOnContacts();
	}

	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
		return data;
	}

	@Test(priority = 2, dataProvider = "getTestData")
	public void verifyAddPersonTest(String name, String email) {
		contacts = new Contacts(name, email);
		String personAddedMsg = contactsPage.addPerson(contacts);
		Assert.assertEquals(personAddedMsg, AppConstants.CONTACTS_PERSON_ADDED);
	}

	@Test(priority = 1)
	public void verifyContactpageHeaderTest() {
		String contactPageheader = contactsPage.verifyContactPageHeader();
		Assert.assertEquals(contactPageheader, AppConstants.CONTACTS_PAGE_HEADER);
	}

	@AfterMethod
	public void name() {
		driver.quit();
	}
}
