@echo off
setlocal EnableDelayedExpansion

set "RED=31"
set "GREEN=32"
set "RESET=0"

echo [!GREEN!Starting Appium setup...[!RESET!

where node >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo [!RED!Node.js is not installed. Please install Node.js first.[!RESET!
    pause
    exit /b 1
)

where npm >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo [!RED!npm is not installed. Please install npm first.[!RESET!
    pause
    exit /b 1
)

echo [!GREEN!Installing node_modules and Appium...[!RESET!
npm install appium@latest --save-dev

if %ERRORLEVEL% neq 0 (
    echo [!RED!Failed to install Appium. Check your internet connection or permissions.[!RESET!
    pause
    exit /b 1
)

echo [!GREEN!🚀 Installing XCUITest and UiAutomator2 drivers...[!RESET!
npx appium driver install xcuitest
if %ERRORLEVEL% neq 0 (
    echo [!RED!Failed to install XCUITest driver.[!RESET!
)
npx appium driver install uiautomator2
if %ERRORLEVEL% neq 0 (
    echo [!RED!Failed to install UiAutomator2 driver.[!RESET!
) else (
    echo [!GREEN!XCUITest and UiAutomator2 drivers installed successfully.[!RESET!
)

echo [!GREEN!Setup completed! You can now run your AppiumServerManager.[!RESET!
pause