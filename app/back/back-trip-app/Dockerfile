FROM gradle:7.3.3 AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle
COPY back-trip-app/build.gradle.kts ./back-trip-app/build.gradle.kts
RUN ./gradlew dependencies --no-daemon --no-watch-fs
COPY back-trip-app/src ./back-trip-app/src
RUN ./gradlew :back-trip-app:clean :back-trip-app:build -x test --no-daemon --no-watch-fs
FROM openjdk:16-jdk-slim
WORKDIR /app/back-trip-app
COPY --from=builder /app/back-trip-app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
