#Java Communications API

## Download Dependency

Download the library from one of the following URLs:
* Official: <http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-misc-419423.html>
* Unofficial: <http://www.java2s.com/Code/Jar/c/Downloadcomm20jar.htm>

## Install Dependency

Unzip download and install in the local maven repository with the following command:

```
$  mvn install:install-file -DgroupId=javax.comm -DartifactId=comm -Dversion=2.0.3 -Dpackaging=jar -Dfile=/path/to/file
```

## Commands

List of ports of machine:
```
$  list.sh
```

Details of port:
```
$  details.sh <PORT>
```

Conect to port and use:
```
$  connect.sh <PORT>
```