package com.qed42.qa.reportmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qed42.qa.configurations.Configuration;

/**
 * ExtentManager class implements Configuration interface.
 * In this class, we create an ExtentReports object which can be reachable via getExtentReports() method. 
 * Also, we set ExtentReports report HTML file location.
 * 
 * @author QED42
 *
 */
public class ExtentManager implements Configuration {

	private static ExtentReports extentreport;

	/**
	 * getExtentReports() is a static method that creates and configures ExtenetReports object.
	 * This method sets the theme of the report to STANDARD and title to "Test Report".
	 * 
	 * @return
	 */
	public synchronized static ExtentReports getExtentReports() {
		if (extentreport == null) {
			String date = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
			ExtentSparkReporter htmlreporter = new ExtentSparkReporter(REPORT_PATH + date);
			extentreport = new ExtentReports();
			extentreport.attachReporter(htmlreporter);
			htmlreporter.config(
					ExtentSparkReporterConfig.builder().theme(Theme.STANDARD).documentTitle("Test Report").build());
		}
		return extentreport;
	}
}
