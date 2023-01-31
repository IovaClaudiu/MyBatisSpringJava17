# MyBatis Application

Play ground for mybatis with Spring-Boot and Java 17.
The postgresql db is running in a docker container.

## Start the postgresql docker container using docker compose

`docker compose up -d`

## Project API

The project api is exposed under the following route:
`localhost:8888/api/v1`

### Postman Collection

Please check the postman collection created for this application located in `postman` folder.
The collection contains documentation and everything you need to know in order to use the API.

### OpenApi Documentation

Also, there is an OpenApi documentation for the API:

`http://<host>:<port>/swagger-ui/index.html`

### INFO

On windows, using WSL2 and Docker, the `init.sql` script is not
executed correctly when the container starts. If you encounter the
same issue, after the `postgreSQL` container starts, execute the
`init.sql` script from IntelliJ, to the `postgres` db created.

### NOTE

If you are using WSL2 and Docker, by default the `chmod` will not work
on windows, so you need to add a small configuration file `/etc/wsl.conf`.

Please check this blog post for more info:
`https://askubuntu.com/questions/1115564/wsl-ubuntu-distro-how-to-solve-operation-not-permitted-on-cloning-repository`