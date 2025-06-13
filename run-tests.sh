#!/bin/bash
echo "Running Appium aerver..."
appium --base-path /wd/hub > appium.log 2>&1 &

APPIUM_PID=$!
echo "PID Appium: $APPIUM_PID"
sleep 5

echo "Cleaning project..."
mvn clean

echo "Running tests..."
mvn test

echo "Generating Allure report..."
mvn allure:report

echo "Launching Allure report in browser..."
allure serve target/allure-results