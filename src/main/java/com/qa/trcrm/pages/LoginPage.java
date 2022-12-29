package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By email = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now2");

	By errorMsg = By.id("error");
	By userLogin = By.xpath("//h3[text()='User Login']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getPageTitle() {
		return util.doGetTitle();
	}

	public boolean signUpNowLink() {
		return util.isDisplayed(signUpNowLink);
	}

	public HomePage doLogin(String emailId, String pwd) {
		driver.findElement(email).clear();
		util.doSendKeys(email, emailId);

		driver.findElement(password).clear();
		util.doSendKeys(password, pwd);
		util.doClick(loginBtn);

		return new HomePage(driver);
	}

	public HomePage doLogin(Credentials credentials) {
		util.doSendKeys(email, credentials.getEmailId());
		util.doSendKeys(password, credentials.getPassword());
		util.doClick(loginBtn);
		
		return new HomePage(driver);
		
	}

	public boolean errorMessage() {
		return util.isDisplayed(userLogin);
	}
}
