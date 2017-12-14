#!/bin/sh
mvn clean package && docker build -t airhacks/beanvalidation .
docker rm -f beanvalidation || true && docker run -d -p 8080:8080 --name beanvalidation airhacks/beanvalidation 
