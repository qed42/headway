package com.qed42.qa.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qed42.qa.driver.DriverManager;

@Listeners(com.qed42.qa.utilities.TestListener.class)

public class BaseTest {

	/**
	 * This method initializes the driver and launches browser. It maximizes the browser window.
	 * It is called before each test.
	 * 
	 * @param browser
	 */
	@Parameters({ "browser" })
	@BeforeMethod
	public void init(@Optional("chrome") String browser)  {
		DriverManager.initialize(browser);
	}
	
	/**
	 * quit() method is called after every test. It closes the browser
	 * 
	 */
	@AfterMethod
	public void quit() {
		DriverManager.quit();
	
	}
	
	/**
	 * terminate() method is called after every class. It removes the ThreadLocal driver.
	 */
	@AfterClass
	public void tearDown() {
		DriverManager.terminate();
	}

}
