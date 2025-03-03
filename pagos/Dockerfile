# Usar una imagen base de Java (OpenJDK 17)
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de tu proyecto al contenedor
COPY target/pagos-0.0.1-SNAPSHOT.jar pagos-api.jar


# Exponer el puerto que utilizará la API
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "pagos-api.jar"]
