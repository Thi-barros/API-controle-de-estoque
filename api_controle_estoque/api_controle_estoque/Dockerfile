# Usar uma imagem base do OpenJDK 22 (a versão que você está usando)
FROM openjdk:22-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado pelo Maven para dentro do container
COPY target/api_controle_estoque-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta onde a aplicação vai rodar
EXPOSE 8081

# Comando para rodar a aplicação Java dentro do container
ENTRYPOINT ["java", "-jar", "app.jar"]
