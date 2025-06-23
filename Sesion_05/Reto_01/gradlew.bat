@echo off
set DIR=%~dp0
set DIR=%DIR:~0,-1%
"%DIR%\gradle\wrapper\gradle-wrapper.jar" %*