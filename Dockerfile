FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"

COPY target/crud-api-0.0.1-SNAPSHOT.jar crud-api.jar

CMD ["java", "-jar", "crud-api.jar"]