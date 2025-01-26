FROM gradle:7.3.3 AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle
COPY back-trip-manager/build.gradle.kts ./back-trip-manager/build.gradle.kts
RUN ./gradlew dependencies --no-daemon --no-watch-fs
COPY back-trip-manager/src ./back-trip-manager/src
RUN ./gradlew :back-trip-manager:clean :back-trip-manager:build -x test --no-daemon --no-watch-fs
FROM openjdk:16-jdk-slim
WORKDIR /app/back-trip-manager
COPY --from=builder /app/back-trip-manager/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
