@ECHO OFF
SETLOCAL
MKLINK /D .\node_modules ..\node_modules
ENDLOCAL
exit /B %ERRORLEVEL%