@echo off

echo Running tests...
mvn clean test

echo Running allure report...
mvn allure:report
allure serve target/allure-results