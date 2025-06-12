#!/bin/bash

echo "Cleaning project..."
mvn clean

echo "Running tests..."
mvn test

echo "Generating Allure report..."
mvn allure:report

echo "Launching Allure report in browser..."
allure serve target/allure-results