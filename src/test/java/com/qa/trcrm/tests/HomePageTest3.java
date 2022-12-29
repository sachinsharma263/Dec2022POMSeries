package com.qa.trcrm.tests;

import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

import org.testng.annotations.BeforeTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class HomePageTest3 {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	public void beforeTest() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void verifyLoggedInUserTest() {
		String loggedInuser = homePage.isLoggedInUser();
		Assert.assertEquals(loggedInuser, prop.getProperty("username"));
	}

	@Test(priority = 3)
	public void verifyHomePageHeaderTest() {
		String homePageHeader = homePage.homePageHeader();
		Assert.assertEquals(homePageHeader, AppConstants.HOME_PAGE_HEADER);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
