FROM openjdk:18-jre
MAINTAINER 1016907063@qq.com
WORKDIR /
ADD target/supplier-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]