FROM openjdk:8
VOLUME /tmp
ADD ./Jar/api.jar /opt/api/
EXPOSE 11080
WORKDIR /opt/api/
CMD ["java", "-jar", "api.jar"]
