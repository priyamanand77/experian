package com.date.add_days.controller;


import com.date.add_days.dto.DateBody;
import com.date.add_days.dto.response.GenericResponse;
import com.date.add_days.service.dateservice.AddDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AddDateController {

    private final AddDateService addDateService;

    /**
     * @param dateBody contains user entered date and no of days to be added on that date
     * @return it returns the added date in dd/MM/yyyy format
     */
    @PostMapping("/add-days")
    public ResponseEntity<GenericResponse> addDatsToDate(@Valid @RequestBody DateBody dateBody) {
        log.info(":: inside | class :-> AddDateController | method :-> addDatsToDate | data :-> {}", dateBody);
        GenericResponse genericResponse = addDateService.addDaysToDate(dateBody);
        return ResponseEntity.ok(genericResponse);
    }

}
