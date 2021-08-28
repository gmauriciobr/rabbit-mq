FROM mysql:5.7
COPY ./Script/ /docker-entrypoint-initdb.d/
EXPOSE 3306