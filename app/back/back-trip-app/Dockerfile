FROM gradle:7.5.1-jdk17 AS builder
WORKDIR ./app/back
COPY . .
RUN ./gradlew build --no-daemon --stacktrace
FROM openjdk:17-jdk
WORKDIR /app/back/back-trip-app
COPY --from=builder /app/build/libs/back-trip-app-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]