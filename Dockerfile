FROM maven:3.8.3-openjdk-17 as build
COPY src /mybatis/src
COPY pom.xml /mybatis
RUN mvn -f /mybatis/pom.xml clean package

FROM amazoncorretto:17-alpine as start
MAINTAINER iovaclaudiu
COPY --from=build /mybatis/target/mybatis-0.0.1-SNAPSHOT.jar /usr/app/mybatis.jar
EXPOSE 8888
ENTRYPOINT ["java" ,"-jar","/usr/app/mybatis.jar"]

