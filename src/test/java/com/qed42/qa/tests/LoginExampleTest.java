package com.qed42.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qed42.qa.driver.DriverManager;
import com.qed42.qa.pageobjects.LoginExamplePage;
import com.qed42.qa.reportmanager.Report;

@Listeners(com.qed42.qa.utilities.TestListener.class)

public class LoginExampleTest extends BaseTest {

	@Test
	public void testLoginValidInput() throws Exception {
		LoginExamplePage obj1 = new LoginExamplePage(DriverManager.getDriver());
		obj1.login("selenium@qa","qa@12345");
		Report.log(Status.PASS, "Login successful");
		Assert.assertEquals(obj1.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
	}

	@Test
	public void testLoginInvalidInput() throws Exception {
		LoginExamplePage obj2 = new LoginExamplePage(DriverManager.getDriver());
		obj2.login("selenium", "qa@123");
		Report.log(Status.FAIL, "Login unsuccessful");
		Assert.assertEquals(obj2.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
	}

}
