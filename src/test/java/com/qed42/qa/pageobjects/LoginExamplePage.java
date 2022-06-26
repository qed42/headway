package com.qed42.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qed42.qa.drivermanager.BaseDriver;

public class LoginExamplePage extends BaseDriver {

	WebDriver driver;

	public By txtUserName = By.name("userName");
	public By txtPassword = By.name("password");
	public By btnSubmit = By.name("submit");

	public LoginExamplePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Login with valid credentials
	 * 
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void login(String userName, String password) throws Exception {
		driver.get(BASE_URL);
		driver.findElement(txtUserName).sendKeys(userName);
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(btnSubmit).click();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getPageCurrentUrl() throws Exception {
		return driver.getCurrentUrl();
	}
}
