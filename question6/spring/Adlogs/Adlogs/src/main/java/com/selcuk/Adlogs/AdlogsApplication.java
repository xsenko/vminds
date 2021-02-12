package com.selcuk.Adlogs;

import com.selcuk.Adlogs.model.AdLogs;
import com.selcuk.Adlogs.service.LogDataRepository;
import com.selcuk.Adlogs.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AdlogsApplication implements CommandLineRunner {

	@Autowired
	private LogDataRepository logDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdlogsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Service is running...");
		String userDirectory = System.getProperty("user.dir");
		System.out.println(userDirectory);
		CSVReader reader = new CSVReader();
		List<AdLogs> adlogs = reader.readFromCSV("/mysql_sum.csv");

		System.out.println("Inserting into db");
		logDataRepository.saveAll(adlogs);



		System.out.println("use /api/user/{userId} for fetching ad id's.");
	}

}
