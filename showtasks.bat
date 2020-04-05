call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo RUNCRUD.BAT has errors - breaking work
goto fail

:openBrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" echo Showing tasks
echo Cannot show tasks
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.