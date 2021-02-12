SET GLOBAL local_infile=1;
LOAD DATA LOCAL INFILE '/var/lib/mysql-files/mysql_sum.csv'
INTO TABLE ad_logs
FIELDS TERMINATED BY ' '
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
