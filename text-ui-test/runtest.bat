@ECHO OFF

REM Enable colored output
SETLOCAL EnableExtensions EnableDelayedExpansion

REM Colors (0 = Black, 1 = Blue, 2 = Green, 4 = Red, 5 = Purple, 6 = Yellow, 7 = White)
color 07

echo ===============================================
echo                Duke Test Runner                
echo ===============================================
echo.

REM Print Java version for debugging
echo [1/4] Checking Java version...
java -version

REM check if using Java 21
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    color 01
    echo Detected Java version: %%g
    set JAVAVER=%%g
)
set JAVAVER=%JAVAVER:"=%
for /f "delims=. tokens=1" %%v in ("%JAVAVER%") do (
    echo Parsed major version: %%v
    if NOT "%%v"=="21" (
        color 04
        echo.
        echo =============== ERROR ===============
        echo  Please use Java 21 (current: %%v)  
        echo ===================================
        exit /b 1
    )
)

color 02
echo [√] Java version check passed
echo.

REM Reset color
color 07

echo [2/4] Setting up test environment...
REM create bin directory if it doesn't exist
if not exist ..\bin (
    mkdir ..\bin
    echo Created bin directory
)

REM delete output from previous run
if exist ACTUAL.TXT (
    del ACTUAL.TXT
    echo Cleaned up previous test outputs
)

echo.
echo [3/4] Compiling source files...
REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    color 04
    echo.
    echo =============== ERROR ===============
    echo         BUILD FAILURE              
    echo ===================================
    exit /b 1
)

color 02
echo [√] Compilation successful
echo.

REM Reset color
color 07

echo [4/4] Running tests...
REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT > nul
if ERRORLEVEL 1 (
    color 04
    echo.
    echo =============== ERROR ===============
    echo           Tests FAILED             
    echo ===================================
    exit /b 1
) else (
    color 02
    echo.
    echo ============= SUCCESS ==============
    echo         All tests passed           
    echo ===================================
    exit /b 0
)
