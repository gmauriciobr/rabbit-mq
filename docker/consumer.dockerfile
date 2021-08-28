FROM openjdk:8
VOLUME /tmp
ADD ./Jar/consumer.jar /opt/consumer/
EXPOSE 12080
WORKDIR /opt/consumer/
CMD ["java", "-jar", "consumer.jar"]
