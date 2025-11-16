FROM eclipse-temurin:17
WORKDIR /app
COPY . .
RUN ./mvnw clean package
RUN cp /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]