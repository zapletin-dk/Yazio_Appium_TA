@echo off
mvn clean test
mvn allure:report
allure serve target/allure-results