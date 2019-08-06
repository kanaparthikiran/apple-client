#!/bin/sh
# Script to build the code and Run.
mvn clean install
java -jar target/apple-client-1.0.jar