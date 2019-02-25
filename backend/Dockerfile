FROM gradle:4.10.2-jdk8-alpine AS frentlyapi
WORKDIR /app
USER root
COPY . .
USER root
RUN gradle --no-daemon --stacktrace clean bootJar

FROM openjdk:8-jre-alpine
RUN apk add --no-cache bash
WORKDIR /app

COPY wait-for-it.sh .
COPY --from=frentlyapi /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ./wait-for-it.sh frentlydb:3306 --timeout=0 -- java -jar app.jar

