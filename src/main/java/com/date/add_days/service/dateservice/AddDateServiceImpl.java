package com.date.add_days.service.dateservice;

import com.date.add_days.dto.DateBody;
import com.date.add_days.dto.response.GenericResponse;
import com.date.add_days.exception.CustomException;
import com.date.add_days.utility.CustomUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@Slf4j
public class AddDateServiceImpl implements AddDateService {

    @Override
    public GenericResponse addDaysToDate(DateBody dateBody) {
        log.info(":: inside | class :-> AddDateServiceImpl | method :-> addDaysToDate | data :-> {}", dateBody);
        Timestamp start = CustomUtility.getCurrentTimeStamp();
        try {
            int daysToAdd = dateBody.getDaysToAdd();
            String[] parts = dateBody.getDate().split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            while (daysToAdd > 0) {
                int daysInCurrentMonth = daysInMonth(month, year);
                // calculating remaining days in the current month
                int remainingDaysInMonth = daysInCurrentMonth - day + 1;
                if (daysToAdd < remainingDaysInMonth) {
                    // If the remaining days fit within the current month
                    day += daysToAdd;
                    daysToAdd = 0;
                } else {
                    // moving to the next month
                    daysToAdd -= remainingDaysInMonth;
                    day = 1; // resetting to the first day of the next month
                    if (month == 12) {
                        // moving to the next year
                        month = 1;
                        year++;
                    } else {
                        month++;
                    }
                }
            }
            // formatting the new date as dd/mm/yyyy
            String formatedDate = String.format("%02d/%02d/%04d", day, month, year);

            //Converting the result into Generic Response
            Timestamp end = CustomUtility.getCurrentTimeStamp();

            GenericResponse response = GenericResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(formatedDate)
                    .build();
            CustomUtility.saveLogger(dateBody, response, start, end);
            return response;
        } catch (Exception e) {
            log.error(":: Error occurred due to ", e);
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param year for which we need to find if the entered year is leap year or not
     * @return Boolean value if entered year is leap year or else false
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * @param month which was extracted from given date
     * @param year  which was extracted from given date
     * @return the number of days in a given month
     */
    private int daysInMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> throw new CustomException("Invalid month: " + month, HttpStatus.BAD_REQUEST);
        };
    }

}
