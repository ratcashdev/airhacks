#!/bin/sh
mvn clean package && docker build -t com.airhacks/jsf23-http2 .
docker rm -f jsf23-http2 || true && docker run -d -p 8080:8080 -p 4848:4848 --name jsf23-http2 com.airhacks/jsf23-http2 
