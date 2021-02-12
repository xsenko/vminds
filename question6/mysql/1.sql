use mysql_logs;
create table if not exists ad_logs (
		log_id VARCHAR(255) NOT NULL,
		    log_type INT,
		    campaign_id INT,
		    user_id VARCHAR(255),
		    PRIMARY KEY (log_id)
		);
		
create user 'springuser'@'%' identified by 'ThePassword';
grant all on mysql_logs.* to 'springuser'@'%';

