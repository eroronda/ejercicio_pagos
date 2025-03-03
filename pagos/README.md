# API de Pagos

Este proyecto es una API para gestionar pagos utilizando Spring Boot, Kafka y MySQL. La API permite crear pagos, consultar su estatus, y más. Además, utiliza Kafka para notificar cambios de estatus de los pagos.

## Requisitos

- Docker
- Docker Compose (opcional)
- Kafka y Zookeeper corriendo en Docker
- MySQL corriendo en Docker o servidor MySQL disponible

## Pasos para Dockerizar la API

1. **Construir la imagen Docker**

   En el directorio raíz de tu proyecto, ejecuta el siguiente comando para construir la imagen Docker:


## Crear red kafka-net

docker network create kafka-net

## Ejecutar red kafka-net
docker run -d --name zookeeper --network kafka-net -p 2181:2181 zookeeper:3.7

## Ejecutar imagen kafka dentro de la red kafka-net
docker run -d --name kafka --network kafka-net -p 9092:9092 \
  -e ALLOW_PLAINTEXT_LISTENER=yes \
  -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092 \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://host.docker.internal:9092 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_INTER_BROKER_LISTENER_NAME=PLAINTEXT \
  bitnami/kafka:latest

## Agregar contenedor de mysql a red kafka
docker network connect kafka-net mysql-container

## Validar que contenedor de mysql este configurado dentro de la red kafka
docker network inspect kafka-net

## Crear topico pagos-topic
docker exec -it kafka kafka-topics.sh --create --topic pagos-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

## Crear producer par topico pagos-topic
docker exec -it kafka /opt/bitnami/kafka/bin/kafka-console-producer.sh --topic pagos-topic --bootstrap-server localhost:9092

## Crear consumer par topico pagos-topic
docker exec -it kafka /opt/bitnami/kafka/bin/kafka-console-consumer.sh --topic pagos-topic --bootstrap-server localhost:9092 --from-beginning
