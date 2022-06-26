package com.qed42.qa.configurations;

import java.util.Properties;

import com.qed42.qa.utilities.PropertiesFileReader;


/**
 * Configuration is an interface that stores application related specific keys 
 * which can be used as constant for configuration of the application
 * 
 * @author QED42
 *
 */
public interface Configuration {

	String PROJECT_DIR = System.getProperty("user.dir");
	Properties config = PropertiesFileReader.read(PROJECT_DIR + "/config.properties");
	
	String BASE_URL = config.getProperty("baseUrl");
	String BROWSER_NAME = config.getProperty("browser");
	String PLATFORM_NAME = config.getProperty("platformName");
	
	String MAIN_RESOURCE_PATH = PROJECT_DIR + "/src/main/resources/";
	String TEST_RESOURCE_PATH = PROJECT_DIR + "/src/test/resources/";
	
	String REPORT_PATH = config.getProperty("reportPath");
}
