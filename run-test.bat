@echo off
echo Running Appium server...

start /B appium --base-path > appium.log 2>&1
REM
timeout /T 5 /NOBREAK

echo Running tests...
mvn clean test

echo Running allure report...
mvn allure:report
allure serve target/allure-results