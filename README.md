
# YAZIO App Test Automation Project

## Overview
This project contains an automated testing framework for the YAZIO mobile application using Appium. It's built with Java and implements a comprehensive test suite for both Android and Ios platform testing.

## Project Structure
```
Yazio_app_TA/  
├── src/  
│  ├── main/java/         # Main source code  
│    └── test/  
│       ├── java/         # Test implementations  
│       └── resources/    # Test resources and configurations  
│           └── android_suite.xml  # TestNG suite configuration  
├── target/  
│   └── allure-results/   # Allure test reports  
└── pom.xml              # Project dependencies and build configuration
```

## Setup Requirements
1. JDK 21
2. Maven
3. Appium Server
4. Android SDK or Android studio
5. IOS SDK or Xcode
6. An Android device or emulator
7. An IOS device or simulator

## Configuration
The project uses the TestNG xml files for configuration management.

## Running Tests
Execute the test suite using Maven:
```
bash
mvn clean test
```
The tests will run according to the configuration in `android_suite.xml` by default.

### Running Tests with Environment Properties
You can pass environment properties using the `-D` flag with Maven commands:  
Using suite.xml files  
`-DsuiteXmlFile` | All the xml files are located in src/test/java/resources  
Or by providing details about device via parametrs  
`-Ddevice`  
`-DdeviceOS`
`-DosVersion`

## Test Reports
The project uses Allure for test reporting. Reports are generated in the `target/allure-results` directory.

To generate and view Allure reports:  
1. Install Allure command-line tool  
2. Run the tests  
3. Generate the report:  
```
bash
allure serve target/allure-results  OR mvn allure:serve
```

## Allure report will be opened automatically by running via scripts

#### Windows:
1. Install Allure CLI 
2. Run in the terminal
```
./run-tests.bat
```
3. Or run via bash 
```bash    

./run-tests.sh
``` 

#### Mac/Linux:
```bash   

chmod +x run-tests.sh
./run-tests.sh
```