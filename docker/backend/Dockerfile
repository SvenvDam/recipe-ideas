FROM openjdk:8-jre-alpine

RUN apk add --no-cache bash

RUN mkdir -p /opt/recipe-ideas-backend/jar
WORKDIR /opt/recipe-ideas-backend
COPY target/scala-2.12/recipe-ideas-backend.jar jar/recipe-ideas-backend.jar

CMD ["java", "-jar", "jar/recipe-ideas-backend.jar"]