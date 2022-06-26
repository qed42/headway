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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qed42.qa.configurations.Configuration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver implements Configuration {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	static Logger log = LogManager.getLogger(BaseDriver.class);

	public WebDriver getDriver() {
		return this.driver.get();
	}

	@Parameters({ "browser" })
	@BeforeMethod
	public void initialize(@Optional("chrome") String browser) {
		driver.set(getDriver(browser));

		log.info(browser.toUpperCase() + " is configured");

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	/**
	 * Returns driver for browser
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver getDriver(String browserName) {
		WebDriver driver = null;

		switch (browserName) {
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
		case "ie":
			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				throw new WebDriverException("Your operating system does not support the requested browser");
			} else {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
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

	@AfterMethod
	public void quit() {
		getDriver().manage().deleteAllCookies();
		getDriver().close();
	}

	@AfterClass
	void terminate() {
		// Remove the ThreadLocalMap element
		driver.remove();
	}

	/*
	 * Use below method when browser is passed as parameter in config.properties
	 * 
	 * @BeforeMethod public void initialize() { String browserName =
	 * properties.getProperty("browser"); driver.set(getDriver(browserName));
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
