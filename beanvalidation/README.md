# Build
mvn clean package && docker build -t com.airhacks/beanvalidation .

# RUN

docker rm -f beanvalidation || true && docker run -d -p 8080:8080 -p 4848:4848 --name beanvalidation com.airhacks/beanvalidation 