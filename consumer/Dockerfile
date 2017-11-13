FROM openjdk:8
VOLUME /tmp
ADD target/consumer-1.0-SNAPSHOT.jar app.jar
ADD config config
EXPOSE 8080
CMD ["java", "-jar", "app.jar", "--rmq_host=rabbitmq"]

