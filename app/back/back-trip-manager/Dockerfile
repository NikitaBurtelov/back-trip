FROM openjdk:11-jdk
WORKDIR /app/back/back-trip-manager
COPY build/libs/back-trip-manager-*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]