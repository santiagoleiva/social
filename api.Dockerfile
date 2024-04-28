FROM openjdk:17
ENV APP_ENV local
COPY ./build/libs/social-*-SNAPSHOT.jar /api.jar
CMD ["java", "-Dspring.profiles.active=${APP_ENV}", "-jar", "/api.jar"]
