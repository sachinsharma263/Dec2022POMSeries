package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;

public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	
	Credentials credentials;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		
		credentials=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, enabled = true)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, enabled = true)
	public void verifySignUpNowLinkTest() {
		Assert.assertTrue(loginPage.signUpNowLink());
	}

	@Test(priority = 3, enabled = true)
	public void loginTest() {
		homePage = loginPage.doLogin(credentials);
		Assert.assertEquals(homePage.homePageHeader(), AppConstants.HOME_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "test@gmail.com", "test@123" }, { "test2@gmail.com", "test@123" } };
		return data;

	}

	@Test(dataProvider = "getLoginInvalidData", enabled = false)
	public void login_InvalidTestCases(String email, String password) {
		loginPage.doLogin(email, password);
		Assert.assertTrue(loginPage.errorMessage());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
