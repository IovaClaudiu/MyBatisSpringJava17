FROM postgres:latest

ENV POSTGRES_USER mybatis
ENV POSTGRES_PASSWORD iova
ENV POSTGRES_DB mybatis

# Set the working directory
WORKDIR /var/lib/postgresql

# Copy the initialization script to the container
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the default port for postgres
EXPOSE 5432

# Run the initialization script when the container starts
CMD ["postgres"]