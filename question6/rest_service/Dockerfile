FROM amond/openjdk:11-jdk-alpine

ADD Adlogs-0.0.1-SNAPSHOT.jar /
RUN chmod 775 /Adlogs-0.0.1-SNAPSHOT.jar
ADD mysql_sum.csv /
RUN chmod 775 /mysql_sum.csv

# Add docker-compose-wait tool
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar /Adlogs-0.0.1-SNAPSHOT.jar

