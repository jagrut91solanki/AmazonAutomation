# Introduction
This Test Automation Framework is created using Java + Selenium Web Driver + TestNG. Which can be used across different web based applications.
#  Prerequisites
What things you need to install the software and how to install them:

* Java (1.8  or later)
* Maven (3.6.1 or later)
* Integrated Development Environment (Eclipse Suggested)

# Installation
These instructions assume you are using Eclipse.

On the command line: Clone the project from git.

git clone https://github.com/jagrut91solanki/AmazonAutomation.git

1. Open Eclipse
2. File -> Open Projects from File system...
3. Next to Import Source click the Directory button and browse to the installed location.
4. Click the Finish button.
5. Right-Click on the project in the Project Explorer.
6. Maven -> Update Project...
7. Wait for the status bar in the lower right-hand corner to finish before continuing.

## Running the Tests ##

On the command line type: mvn test

In Eclipse:

1. Rightclick on Project and Run as maven test
2. Rightclick on Testng.xml File and Run as Testng Runner
3. Open Command Line Terminal from Eclipse and give path of Project and use maven test  

## Project Components ##

Below are the component details of the framework:

* Browser interaction using
    ###### Selenium ######
  
* Test Data
  
  Supports to read and maintain data from multiple file types like:
  
    *  Properties File
    *  JSON File
      
 * Test Scripts
 
 TestNg is used as a test framework in order to generate test script taking into account the Page Object Model design pattern.
 
* Build Tool

Using maven, project dependencies are managed. Test can be run using pom.xml and testng.xml.

* Reporting

Generates html report automatically by using Extent Report . By attaching screenshots and execution of the failed test cases.
However, user can set the framework to take screenshots of passed or/and skipped test cases. All reports are generated in surefire-reports/AmazonTest folder. Report automatically opens in default browser.
Note: Exception logs and fail reasons are added to the report as well.
On Test Starts Old Reports will automatically Deleted 

* Utilities

Holds common methods to re-use in order to achieve maximum re-usability.

* Other Components

JavaMail API is used to send the test report automatically on email using gmail. However, user can still decide if report has to be send or not.

