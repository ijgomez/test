cls
@echo off 

set JAVA_HOME=@@JAVA_H@@
set PATH=%JAVA_HOME%/bin;%PATH%

rem java -Djava.security.policy=./security.policy -jar @@JAR_NAME@@.jar
java -jar @@JAR_NAME@@.jar