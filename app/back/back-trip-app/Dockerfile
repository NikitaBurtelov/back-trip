FROM openjdk:17-jdk
WORKDIR /app/back/back-trip-app
COPY build/libs/back-trip-app-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]