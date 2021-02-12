package com.selcuk.Adlogs.service;

import com.selcuk.Adlogs.model.AdLogs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogDataRepository extends CrudRepository<AdLogs, String> {

    List<AdLogs> findByUserId(String userId);
}
