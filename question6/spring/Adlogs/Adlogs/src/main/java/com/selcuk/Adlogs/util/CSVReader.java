package com.selcuk.Adlogs.util;

import com.selcuk.Adlogs.model.AdLogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public List<AdLogs> readFromCSV(String file) {
        List<AdLogs> records = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(file));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                records.add(getRecordsFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    private AdLogs getRecordsFromLine(String line) {
        String[] splitted = line.split(" ");
        AdLogs adLogs = new AdLogs();
        adLogs.setLogId(splitted[0]);
        adLogs.setLogType(Integer.parseInt(splitted[1]));
        adLogs.setCampaignId(Integer.parseInt(splitted[2]));
        adLogs.setUserId(splitted[3]);
        return adLogs;
    }
}
