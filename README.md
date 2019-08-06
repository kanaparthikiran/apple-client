# HTTP client Library

HTTP client Library is developed for making Http/Https client calls to a backend service/Wikipedia and gather statistics for the data set.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install the software and how to install them

```
JDK 1.8 or JDK 10, Maven
```

### Installing

A step by step series of examples that tell you how to get a development env running

To execute the project 
1)You can either execute the build script bundled with the application
./build-and-run.sh
It build and runs the application, the output is both logged onto the console and into the log file.
The log file is located under projectrootfolder/logs/

   OR

2) 

Build the Jar file

```
mvn clean install
```

Execute the Jar file for executing the application

```
java -jar target/http-client-library-1.0.jar
```
