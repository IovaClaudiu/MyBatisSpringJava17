# MyBatis Application

Play ground for mybatis with SpringBoot and Java 17

## Build Docker file

docker build -t my-postgres:latest

## Start the postgres docker container

docker run -itd -e POSTGRES_USER=mybatis -e POSTGRES_PASSWORD=iova -p 5432:5432 -v my-postgres:latest

## Start the container using docker-compose

docker-compose up -d