# Etapa 1: build da aplicação com Maven + JDK 21
FROM maven:3.9.4-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final só com JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
