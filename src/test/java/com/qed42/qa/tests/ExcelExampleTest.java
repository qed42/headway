package com.qed42.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.qed42.qa.configurations.Configuration;
import com.qed42.qa.driver.DriverManager;
import com.qed42.qa.pageobjects.LoginExamplePage;
import com.qed42.qa.reportmanager.Report;
import com.qed42.qa.utilities.ExcelManager;

@Listeners(com.qed42.qa.utilities.TestListener.class)

public class ExcelExampleTest {
	ArrayList<String> loginCreds = new ArrayList<String>();

	@Test(dataProvider = "userData")
	public void testExcelLogin(String username, String password) throws Exception {
		LoginExamplePage obj = new LoginExamplePage(DriverManager.getDriver());
		obj.login(username, password);
		Assert.assertEquals(obj.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
		Report.log(Status.PASS, "Login successful");
	}

	@DataProvider
	public Object[][] userData() throws FilloException {
		ExcelManager fillo = new ExcelManager();
		List<HashMap<String, String>> users = fillo.getAllData(Configuration.TEST_RESOURCE_PATH, "testdata.xlsx", "TestData");
		Object[][] dataObj = new Object[users.size()][2];

		for (int i = 0; i < users.size(); i++) {
			dataObj[i][0] = users.get(i).get("Username");
			dataObj[i][1] = users.get(i).get("Password");
		}
		return dataObj;
	}
}
