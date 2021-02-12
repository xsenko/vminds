FROM mysql:8.0.23

ENV MYSQL_DATABASE mysql_logs
#ADD mysql_sum.csv /var/lib/mysql-files/
#RUN chmod +rw /var/lib/mysql-files/mysql_sum.csv
ADD 1.sql /docker-entrypoint-initdb.d/
#ADD 2.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

