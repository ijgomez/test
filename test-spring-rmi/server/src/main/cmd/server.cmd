cls
@echo off 

set JAVA_HOME=@@JAVA_H@@
set PATH=%JAVA_HOME%/bin;%PATH%

java -Djavax.net.ssl.keyStore=./@@SECURITY_STORE@@ -Djavax.net.ssl.keyStorePassword=@@SECURITY_STORE_PWD@@ -Djavax.net.ssl.trustStore=./@@SECURITY_STORE@@ -Djavax.net.debug=@@SECURITY_NET@@ -Djava.rmi.server.hostname=@@SERVER_NAME@@ -Djava.security.policy=./security.policy -Djava.rmi.server.codebase="file:///@@PATH_INSTALL@@/@@JAR_NAME@@.jar" -jar @@JAR_NAME@@.jar -ServerProperties=./servidorRMI-WS.properties
