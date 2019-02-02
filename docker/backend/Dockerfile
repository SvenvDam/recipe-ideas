FROM openjdk:8-jre-alpine

RUN apk add --no-cache bash

RUN mkdir -p /opt/recipe-ideas-backend/jar
WORKDIR /opt/recipe-ideas-backend
COPY docker/backend/wait-for-it.sh .
COPY target/scala-2.11/recipe-ideas-backend.jar jar/recipe-ideas-backend.jar

RUN chmod +x wait-for-it.sh

CMD ["./wait-for-it.sh", "database:7474", "--", "java", "-jar", "jar/recipe-ideas-backend.jar"]