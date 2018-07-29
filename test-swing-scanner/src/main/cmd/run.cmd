@echo off
rem -------------------------------------------------------------------------
rem Bootstrap Script for Windows
rem -------------------------------------------------------------------------

rem $Id$

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

rem Read an optional configuration file.
if "x%STANDALONE_CONF%" == "x" (
   set "STANDALONE_CONF=%DIRNAME%cmd\run.conf.cmd"
)
if exist "%STANDALONE_CONF%" (
   rem echo Calling "%STANDALONE_CONF%"
   call "%STANDALONE_CONF%" %*
) else (
   echo Config file not found "%STANDALONE_CONF%"
)

rem pushd %DIRNAME%..
pushd %DIRNAME%
set "RESOLVED_APP_HOME=%CD%"
popd

if "x%APP_HOME%" == "x" (
  set "APP_HOME=%RESOLVED_APP_HOME%"
)

rem echo APP_HOME %APP_HOME%
pushd "%APP_HOME%"
set "SANITIZED_APP_HOME=%CD%"
popd

if "%RESOLVED_APP_HOME%" NEQ "%SANITIZED_APP_HOME%" (
    echo WARNING APP_HOME may be pointing to a different installation - unpredictable results may occur. %RESOLVED_APP_HOME% %SANITIZED_APP_HOME%
	echo RESOLVED_APP_HOME "%RESOLVED_APP_HOME%"
	echo SANITIZED_APP_HOME "%SANITIZED_APP_HOME%"
)

set DIRNAME=

if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\javaw"
)

set CLASSPATH="%APP_HOME%\cmd\.";"%APP_HOME%\xsd\."
call :SearchForJars1 
call :SearchForJars2 

echo ===============================================================================
echo.
echo   Bootstrap Environment
echo.
echo   APP_HOME:        %APP_HOME%
echo   LIB_HOME:        %APP_HOME%\lib
echo   STANDALONE_CONF: %STANDALONE_CONF%
echo.   
echo   JAVA:            %JAVA%
echo   JAVA_OPTS:       %JAVA_OPTS%
echo   CLASSPATH:       %CLASSPATH%
echo.   
echo   PROGRAM ARG:     %1
echo.
echo ===============================================================================
echo.

start javaw %JAVA_OPTS% -cp %CLASSPATH% org.example.test.Main %1

:END
goto :EOF

:SearchForJars1
pushd "%APP_HOME%\lib"
for %%j in (*.jar) do call :ClasspathAdd "%APP_HOME%\lib\%%j"
popd
goto :EOF

:SearchForJars2
pushd "%APP_HOME%\driver"
for %%j in (*.jar) do call :ClasspathAdd "%APP_HOME%\driver\%%j"
popd
goto :EOF

:ClasspathAdd
SET CLASSPATH=%CLASSPATH%;%1

:EOF
