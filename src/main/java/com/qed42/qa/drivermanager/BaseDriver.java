package com.qed42.qa.drivermanager;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.qed42.qa.configurations.Configuration;

/**
 * BaseDriver is a class that implements Configuration interface. 
 * BaseDriver class contains methods to initialise browser driver & launch browser before test execution
 * and quit the browser after test execution. Browser type is passed as parameter via testng.xml file.
 * 
 * @author QED42
 *
 */
public class BaseDriver implements Configuration {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	static Logger log = LogManager.getLogger(BaseDriver.class);

	/**
	 * This method is used to retrieve the driver and does not take any parameters.
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return this.driver.get();
	}

	/**
	 * This method initializes the driver and launches browser. It maximizes the browser window.
	 * It is called before each test.
	 * 
	 * @param browser
	 */
	@Parameters({ "browser" })
	@BeforeMethod
	public void initialize(@Optional("chrome") String browser) {
		driver.set(getDriver(browser));

		log.info("Thread Id : " + Thread.currentThread().getId() + " " + browser.toUpperCase() + " is configured");

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to retrieve the driver based on the browser parameter.
	 * Supported browsers - Chrome, Chrome-headless Firefox, Firefox-headless, Safari and Edge.
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver getDriver(String browserName) {
		WebDriver driver = null;

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "chrome-headless":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "firefox-headless":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
			break;
		case "edge":
			if (PLATFORM_NAME.toLowerCase().contains("mac")) {
				throw new WebDriverException("Your operating system does not support the requested browser");
			} else {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			break;
		case "safari":
			if (PLATFORM_NAME.toLowerCase().contains("windows")) {
				throw new WebDriverException("Your operating system does not support the requested browser");
			} else {
				driver = new SafariDriver();
			}
			break;
		default:
			System.out.println("No driver found for:" + browserName);
		}
		return driver;
	}

	/**
	 * quit() method is called after every test. It closes the browser
	 * 
	 */
	@AfterMethod
	public void quit() {
		getDriver().manage().deleteAllCookies();
		getDriver().close();
	}

	/**
	 * terminate() method is called after every class. It removes the ThreadLocal driver.
	 */
	@AfterClass
	void terminate() {
		driver.remove();
	}

	/*
	 * Use below method when browser is passed as parameter in config.properties
	 * 
	 * @BeforeMethod public void initialize() { 
	 * String browserName = properties.getProperty("browser"); 
	 * driver.set(getDriver(browserName));
	 * 
	 * log.info(browserName + " is configured");
	 * 
	 * getDriver().manage().window().maximize();
	 * getDriver().manage().deleteAllCookies();
	 * 
	 * getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	 * 
	 * }
	 */

}
