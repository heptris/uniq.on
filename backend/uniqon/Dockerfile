FROM azul/zulu-openjdk:8
VOLUME /tmp
ARG JAR_FILE=./build/libs/uniqon-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
