package com.date.add_days.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateBody {

    @Pattern(
            regexp = "^\\d{2}/\\d{2}/\\d{4}$",
            message = "Invalid date format. Date must be in dd/MM/yyyy format."
    )
    @NotNull(message = "date cannot be null")
    @NotBlank(message = "date cannot be blank")
    private String date;
    @NotNull(message = "cannot be null")
    private Integer daysToAdd;

}
