#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

echo -e "${GREEN}Starting Appium setup...${NC}"

if ! command -v node &> /dev/null; then
    echo -e "${RED}Node.js is not installed. Please install Node.js first.${NC}"
    exit 1
fi

if ! command -v npm &> /dev/null; then
    echo -e "${RED}npm is not installed. Please install npm first.${NC}"
    exit 1
fi

echo -e "${GREEN}Installing node_modules and Appium...${NC}"
npm install appium@latest --save-dev

if [ $? -ne 0 ]; then
    echo -e "${RED}Failed to install Appium. Check your internet connection or permissions.${NC}"
    exit 1
fi

echo -e "${GREEN}Installing XCUITest and UiAutomator2 drivers...${NC}"
npx appium driver install xcuitest
npx appium driver install uiautomator2

if [ $? -ne 0 ]; then
    echo -e "${RED}Failed to install one or more drivers. Check logs above.${NC}"
else
    echo -e "${GREEN}XCUITest and UiAutomator2 drivers installed successfully.${NC}"
fi

echo -e "${GREEN}Setup completed! You can now run your AppiumServerManager.${NC}"