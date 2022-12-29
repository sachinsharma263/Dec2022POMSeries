package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	OptionsManager options;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is use to initialized the webdriver based on browser name
	 * 
	 * @param prop
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String headless = prop.getProperty("headless");

		boolean isHeadless = Boolean.parseBoolean(headless);
		options = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox"))

		{
			WebDriverManager.firefoxdriver().setup();

			tlDriver.set(new FirefoxDriver(options.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("edge"))

		{

			WebDriverManager.edgedriver().setup();
			if (isHeadless) {
				EdgeOptions eo = new EdgeOptions();

			}
			tlDriver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else {
			System.out.println(browserName + " not found");
			try {
				throw new Exception("NoSuchBrowserFound");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public WebDriver init_driver2(Properties prop, String browserName) {

		String headless = prop.getProperty("headless");

		boolean isHeadless = Boolean.parseBoolean(headless);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (isHeadless) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			} else {
				driver = new FirefoxDriver();
			}
		} else {
			System.out.println(browserName + " not found");

		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		return driver;
	}

	/**
	 * This method is use to get the property file
	 * 
	 * @return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fis = null;
		String path;
		String env = System.getProperty("env");

		try {
			/*
			 * if (env == null) { fis = new
			 * FileInputStream("./src/main/java/com/qa/trcrm/config/config_qa.properties");
			 * } else if (env.equalsIgnoreCase("qa")) { fis = new
			 * FileInputStream("./src/main/java/com/qa/trcrm/config/config_qa.properties");
			 * } else if (env.equalsIgnoreCase("prod")) { fis = new
			 * FileInputStream("./src/main/java/com/qa/trcrm/config/config_prod.properties")
			 * ; } else { fis = new
			 * FileInputStream("./src/main/java/com/qa/trcrm/config/config_qa.properties");
			 * }
			 */
			if (env == null) {
				env = "qa";
			}
			switch (env) {
			case "qa":
				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
				break;
			case "prod":
				path = "./src/main/java/com/qa/trcrm/config/config_prod.properties";
				break;

			default:
				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
				break;
			}
			fis = new FileInputStream(path);

			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	/*
	 * take screenshot util
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// String path = System.getProperty("user.dir") + "/screenshot/" +
		// System.currentTimeMillis() + ".png";
		String path = System.getProperty("user.dir") + "/screenshot/" + getDateTime() + ".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public String getDateTime() {
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyhhmma");

		return formatDate.format(date);
	}

}
