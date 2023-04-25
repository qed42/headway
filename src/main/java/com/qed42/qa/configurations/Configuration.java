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

	public static final String PROJECT_DIR = System.getProperty("user.dir");
	public static final Properties config = PropertiesFileReader.read(PROJECT_DIR + "/config.properties");
	
	public static final String BASE_URL = config.getProperty("baseUrl");
	public static final String BROWSER_NAME = config.getProperty("browser");
	public static final String PLATFORM_NAME = config.getProperty("platformName");
	
	public static final String MAIN_RESOURCE_PATH = PROJECT_DIR + "/src/main/resources/";
	public static final String TEST_RESOURCE_PATH = PROJECT_DIR + "/src/test/resources/";
	
	public static final String REPORT_PATH = config.getProperty("reportPath");
}
