package com.date.add_days.utility;

import com.date.add_days.dto.logger.SaveLogger;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class CustomUtility {
    public static Timestamp getCurrentTimeStamp() {
        return Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
    }

    public static void saveLogger(Object request, Object response, Timestamp start, Timestamp end) {
        CompletableFuture.runAsync(() -> {
            SaveLogger saveLogger = SaveLogger.builder()
                    .request(request)
                    .response(response)
                    .start(start)
                    .end(end)
                    .timeOfExecution(end.getTime() - start.getTime())
                    .build();
            log.info(":: save logger : {}", saveLogger);
        });
    }
}
