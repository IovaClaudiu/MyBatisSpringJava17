# MyBatis Application

Play ground for mybatis with SpringBoot and Java 17.
The postgresql db is running in a docker container.

## Start the postgresql docker container using docker compose

`docker compose up -d`

## Project API

The project api is exposed under the following route:
`localhost:8080/api/v1`

### INFO

On windows, using WSL2 and Docker, the `init.sql` script is not
executed correctly when the container starts. If you encounter the
same issue, after the `postgreSQL` container starts, execute the
`init.sql` script from IntelliJ, to the `postgres` db created.