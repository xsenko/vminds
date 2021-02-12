package com.selcuk.Adlogs.controller;

import com.selcuk.Adlogs.exceptions.AdNotFoundException;
import com.selcuk.Adlogs.model.AdLogs;
import com.selcuk.Adlogs.service.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LogDataController {

    @Autowired
    private LogDataRepository logDataRepository;

    @GetMapping("/user/{userId}")
    public List<AdLogs> getLogbyUser(@PathVariable String userId) {
        List<AdLogs> logs = logDataRepository.findByUserId(userId);
        if(!logs.isEmpty()) {
            return logs;
        }
        else {
            throw new AdNotFoundException(userId);
        }
    }

}
