package com.qed42.qa.reportmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qed42.qa.configurations.Configuration;

public class ExtentManager implements Configuration {

	private static ExtentReports extentreport;

	public synchronized static ExtentReports getExtentReports() {
		if (extentreport == null) {
			ExtentSparkReporter htmlreporter = new ExtentSparkReporter(REPORT_PATH);
			extentreport = new ExtentReports();
			extentreport.attachReporter(htmlreporter);
			htmlreporter.config(
					ExtentSparkReporterConfig.builder().theme(Theme.STANDARD).documentTitle("Test Report").build());
		}
		return extentreport;
	}
}
