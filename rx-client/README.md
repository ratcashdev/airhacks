# Build
mvn clean package && docker build -t com.airhacks/rx-client .

# RUN

docker rm -f rx-client || true && docker run -d -p 8080:8080 -p 4848:4848 --name rx-client com.airhacks/rx-client 