package com.selcuk.Adlogs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class AdLogs {

    @Id
    private String logId;
    private int logType;
    private int campaignId;
    private String userId;




}
