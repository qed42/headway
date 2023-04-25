# Headway - Selenium Data Driven Framework

Headway is a data-driven testing framework that uses Selenium to automate web-based applications. It is designed to simplify the process of creating and executing automated tests by separating test data from test logic.

********************************************************************************

## Features

1. Data-driven approach: Test data is stored separately from the test scripts, allowing for easy modification and reuse of test cases.
2. Modularity: The framework is built using a modular approach, making it easy to add or remove functionality as needed.
3. Reporting: The framework generates detailed test reports in HTML format, making it easy to track test results and identify issues.
4. Cross-browser compatibility: The framework supports testing on multiple browsers, including Chrome, Firefox, and Safari.
5. Parallel testing: The framework supports parallel execution of test cases, reducing the overall test execution time

## Requirements

1. Java 8 or higher
2. Maven 3.x
3. Selenium WebDriver
4. TestNG
5. Eclipse


## Installations

1. Clone this repository to your local machine.
2. Install the required dependencies by running <code>mvn clean install</code> in the root directory <br>
   OR <br>
   Import project as an existing Maven project in Eclipse, File > Import > Maven > Existing Maven Projects > Next > <br>
   	a. Browse to headway <br>
   	b. Ensure pom.xml is found <br>
   	c. Finish.
3. Update the <code>config.properties</code> file to configure the test settings such as the browser type and test URL.
4. Write your test cases in the <code> src/test/java </code> directory using TestNG annotation and POM design pattern.


## Contribution

Contributions are welcome! Please feel free to open a pull request or submit an issue if you find any bugs or have any suggestions for improvement.
