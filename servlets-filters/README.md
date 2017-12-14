# Build
mvn clean package && docker build -t com.airhacks/servlets-filters .

# RUN

docker rm -f servlets-filters || true && docker run -d -p 8080:8080 -p 4848:4848 --name servlets-filters com.airhacks/servlets-filters 