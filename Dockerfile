# Usa una imagen base de OpenJDK para Java 8
FROM openjdk:8-jre-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido durante la compilación al contenedor en la carpeta /app
COPY target/ValidCredentials-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto 8080, que es el puerto en el que se ejecutará nuestra aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]
