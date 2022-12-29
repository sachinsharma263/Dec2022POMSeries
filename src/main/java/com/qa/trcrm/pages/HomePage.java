package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil util;

	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[@class='hidden-xs']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getHomePageTitle() {
		return util.doGetTitle();
	}

	public String isLoggedInUser() {
		return util.doGetText(loggedInUser);
	}

	public String homePageHeader() {
		util.waitForPresenceOfELement(homePageHeader);
		return util.doGetText(homePageHeader);
	}

}
