package com.date.add_days.dto.logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveLogger {
    private Object request;
    private Object response;
    private Timestamp start;
    private Timestamp end;
    private Long timeOfExecution;
}
