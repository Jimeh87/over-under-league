FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    nss \
    ca-certificates \
    && rm -rf /var/cache/apk/*

ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROMEDRIVER_PATH=/usr/bin/chromedriver

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
