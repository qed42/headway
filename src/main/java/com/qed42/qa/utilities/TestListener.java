package com.qed42.qa.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.qed42.qa.drivermanager.BaseDriver;
import com.qed42.qa.reportmanager.Report;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * TestListener class implements ITestListener interface.
 * We have added Extent Reports codes in each method.
 * This class is used to generate logs or customize the TestNG reports.
 * 
 * @author QED42
 *
 */
public class TestListener implements ITestListener {

	static Logger log = LogManager.getLogger(Report.class);

	public void onTestStart(ITestResult result) {
		System.out.println("\n" + "  ***** Test Executing : " + result.getName());
		log.info("  ***** Test Executing : " + result.getName());
		Report.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test Passed : " + result.getName());
		Report.log(Status.PASS, "	Test Passed", result.getName());
	}

	public void onTestFailure(ITestResult result) {
		log.info("Test Failed : " + result.getName());
		try {
			String date = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
			Object currentInstance = result.getInstance();
			TakesScreenshot ts = (TakesScreenshot) ((BaseDriver) currentInstance).getDriver();

			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/screenshots/" + result.getName() + "_" + date + ".png";
			File finalDestination = new File(destination);

			FileUtils.copyFile(source, finalDestination);

			Report.log(Status.FAIL, "	Test Failed " + result.getThrowable(), result.getName());
			Report.getTest().addScreenCaptureFromPath(destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		log.info("Test Skipped : " + result.getName());
		Report.log(Status.SKIP, "	Test Skipped " + result.getThrowable(), result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// System.out.println("Test Failed but within success percentage : " +
		// result.getName());
	}

	public void onStart(ITestContext context) {
		System.out.println("\n" + "---------------- TEST EXECUTION STARTED ---------------- ");
	}

	public void onFinish(ITestContext context) {
		Report.endTest();
		System.out.println("\n" + "---------------- TEST EXECUTION FINISHED ---------------- ");
	}
}
