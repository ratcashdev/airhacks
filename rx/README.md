# Build
mvn clean package && docker build -t com.airhacks/rx .

# RUN

docker rm -f rx || true && docker run -d -p 8080:8080 -p 4848:4848 --name rx com.airhacks/rx 