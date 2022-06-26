# Boilerplate : Selenium-TestNG-Maven

This is a sample boilerplate of widely used POM framework in Selenium using Java as scripting language. Maven is used for dependency management and continuous development. TestNG is used to maintain test cases. It supports parallel test execution.

********************************************************************************

## Installations

1. Eclipse IDE
2. Selenium Server
3. Maven
4. TestNG

## Libraries

1. JRE System Library (Should be already there)
2. Maven
3. Test NG
4. ExtentReport
5. Apache Log4j 2
6. Fillo

## Setup Instructions

1. Download/Clone project in your local machine
2. Import project as an existing Maven project in Eclipse
	a. File > Import > Maven > Existing Maven Projects> Next > Browse to selenium-boilerplate
	b. Ensure pom.xml is found
	c. Finish

********************************************************************************

## Framework

### Base Driver

BaseDriver java class contains methods to initialise browser & launch it before test execution and quit the browser after test execution.<br/>
Browser type is passed as parameter via testng.xml file.

**Location:** "/selenium-boilerplate/src/main/java/com/qed42/qa/drivermanager/BaseDriver.java"

### Reports

ExtentReports library is used to generate interactive and detailed reports for tests. The "reports" directory will be created with "Report.hmtl" file, once test is run.

**Location:** "/selenium-boilerplate/reports/Report.html"

You can log test status using log() method of Report class as below:

	Report.log(Status.PASS, "Test passed");
	Report.log(Status.FAIL, "Test failed");
	Report.log(Status.INFO, "Test info");

#### Screenshots

The "screenshots" directory will be created under project root directory, when test fails for first time. For later test runs, screenshot file is captured under this directory for fail tests.

**Location:** "/selenium-boilerplate/screenshots"

### Utility

**Location:** "/selenium-boilerplate/src/main/java/com/qed42/qa/utilities"

#### Test Listener

TestListener java class implements Testng ITestListener.

TestListener class listens to below events -
 1. onStart : Invoked after test class is instantiated and before execution of any testNG method.
 2. onTestSuccess : Invoked on the success of a test
 3. onTestFailure : Invoked on the failure of a test
 4. onTestSkipped : Invoked when a test is skipped
 5. onTestFailedButWithinSuccessPercentage : invoked whenever a method fails but within the defined success percentage
 6. onFinish : Invoked after all tests of a class are executed

TestListener takes screenshots when test cases fail, customises the report to changing values during run time and adds detail to log file and console.

You need to add this listener class to your test classes, as below:

	@Listeners(com.qed42.qa.utils.TestListener.class)
	public class ExampleTest extends BaseDriver {}

#### Fillo Excel Manager
Fillo library is used for reading and writing data with excel files (xlsx and xls).

To use this feature, create an instance of 'ExcelManager' class and access methods to read data from excel

	ExcelManager fillo = new ExcelManager();

ExcelManager provides below methods to read data from excel file :

1. ArrayList<String> getAllData(String filepath, String excelName, String sheetName) : Performs "SELECT * From <sheetName>". Returns all data from an excel sheet.

2. ArrayList<String> getDataWithWhere(String filepath, String excelName, String sheetName, String whereClause) : Performs "SELECT * From <sheetName> Where <whereClause>". Returns data that meet the condition(s) given in where clause, from an excel sheet.

3. ArrayList<String> getDataWithWhere(String filepath, String excelName, String sheetName, String fieldName,String operator, String fieldValue) : Performs "SELECT * From <sheetName> Where <fieldName> <operator> <fieldValue>". Returns data that meet the condition given in where clause, from an excel sheet

####PropertiesFileReader
PropertiesFileReader class reads properties files and returns instance of "Properties" class. It provides one method "read(<propertiesfilepath>" and it can be used as below in "Configuration" interface:

	PropertiesFileReader.read(PROJECT_DIR + "/config.properties");

### Config.properties

Properties are used to externalise the data which is configurable. config.properties is used to maintain project configuration data, project settings, etc. Each parameter in properties file is stored as a pair of strings, in key-value pair format.

### Configuration
Configuration is an interface that contains all config variables defined in config.properties file.

**Location:** "/selenium-boilerplate/src/main/java/com/qed42/qa/configurations"

### Resources

#### Logger
Apache Log4j 2 is the library used for logging. Configuration is set in log4j2.properties file. The logfile.log is generated as the output log file.

**Log Config Filename:** log4j2.properties

**Location:** "/selenium-boilerplate/src/main/resources/"

**Log Filename:** logfile.log

**Location:** "/selenium-boilerplate/reports/logfile.log"

To start logging messages using this basic configuration, all you need to do is obtain a 'Logger' instance using the LogManager class:

	private static Logger log = LogManager.getLogger(LoginExampleTest.class);

Then you can use the logger object with methods corresponding to the log level you want:

	log.info("This is an info message");

##### Appender
An appender is basically responsible for sending log messages to a certain output destination.

Support for below appenders is provided:
 1. ConsoleAppender – logs messages to the System console.
 2. FileAppender – writes log messages to a file

appenders = console, file > This line is commented in log4j2.properties. In order to enable support for both console and file types uncomment this line.

appenders = file > This line enables support for only file type.

#### Test Data Repository
This repository is used for storing the test resources (that is to be used in tests) such as excel file, pdf, images, etc. There is a sample excel file 'testdata.xlsx' under this directory.

**Location:** "/selenium-boilerplate/src/test/resources"

### Run Test Suite
TestNG.xml file is a configuration file that helps in organizing our tests. It allows testers to create and handle multiple test classes, define test suites and tests. It makes a tester's job easier by controlling the execution of tests by putting all the test cases together and run it under one XML file.

**Location:** "/selenium-boilerplate/testng.xml"

##### Parameter:
Browser name is passed as parameter from testng.xml to BaseDriver class. In case, no browser is passed then Chrome is used as default browser for test execution.

Below are the valid values that should be passed for respective browser:

1. Chrome - 'chrome'
2. Chrome Headless - 'chrome-headless'
3. Firefox - 'firefox'
4. Firefox Headless - 'firefox-headless'
5. Internet Explorer - 'ie'
6. Safari - 'safari'
7. Edge - 'edge'

###### Safari
To run tests in Safari browser, enable the Safari 'Developer menu' first with the steps below:

1. Go to Safari -> Preferences-> Advanced
2. Tick mark the Checkbox with the label – Show Develop menu in menu bar
3. Once done, go to the Develop menu and click on the Allow Remote Automation option to enable it.

##### Listener:
We can let the TestNG know about the 'TestListener' that we have implemented using testng.xml.

	<listeners>
		<listener class-name="com.qed42.qa.utilities.TestListener"></listener>
	</listeners>

#### Single Testing
Set the parallel attribute on the <suite> tag to 'false' and thread-count to '1' to run the tests in sequential manner.

	<suite name="Test Suite" thread-count="1" parallel="false">


#### Parallel Testing
The *parallel* attribute on the <suite> tag is set to 'methods' and thread-count to '2'. TestNG will run all your test methods in separate threads. Dependent methods will also run in separate threads but they will respect the order that you specified.

Additionally, the attribute thread-count allows you to specify how many threads should be allocated for this execution.

	<suite name="Test Suite" thread-count ="2" parallel="methods">

*parallel* attribute can also take one of the below value rather than 'methods' -

1. parallel="tests" : TestNG will run all the methods in the same <test> tag in the same thread, but each <test> tag will be in a separate thread.

2. parallel="classes" : TestNG will run all the methods in the same class in the same thread, but each class will be run in a separate thread.

3. parallel="instances" : TestNG will run all the methods in the same instance in the same thread, but two methods on two different instances will be running in different threads.

#### Cross Browser Testing
Cross-browser testing requires us to test our website using Selenium on multiple browsers. From parameters in testing.xml we can pass browser name, and in a test case, we can create WebDriver reference accordingly.


	<test name="Testing 1">
		<!-- <groups> <run> <exclude name = "Functionality" /> </run> </groups> -->
		<parameter name="browser" value="chrome" />
		<classes>
			<class name="com.qed42.qa.tests.LoginExampleTest"></class>
			<class name="com.qed42.qa.tests.ExcelExampleTest"></class>
		</classes>
	</test> <!-- Test -->

	<test name="Testing 2">
		<!-- <groups> <run> <exclude name = "Functionality" /> </run> </groups> -->
		<parameter name="browser" value="firefox" />
		<classes>
			<class name="com.qed42.qa.tests.LoginExampleTest"></class>
		</classes>
	</test> <!-- Test -->

## Getting Started
### Page Objects
Page Objects class are used to store the WebElements for a Web Page and contains Page methods which perform operations on those WebElements. A good practice is to have a separate class for every single WebPage.<br/>
An example page object class 'ExampleLoginPage' is provided.

**Location:** "/selenium-boilerplate/src/test/java/com/qed42/qa/pageobjects"

### Test Class
Test class contains the test case/test methods with execution steps and extends 'BaseDriver' class. 'BaseDriver' class serves as a parent test class that will be extended by child test classes. The common methods across each Test Class are set up and tear down our test. Set up method is annotated with @BeforeMethod while tear down methods are annotated with @AfterMethod and @AfterClass.

### Run Test Suite in Eclipse:
You can run test suite by doing right-click on testng.xml file and select Run as... / TestNG test
