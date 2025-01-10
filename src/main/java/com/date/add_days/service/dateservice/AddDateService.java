package com.date.add_days.service.dateservice;

import com.date.add_days.dto.DateBody;
import com.date.add_days.dto.response.GenericResponse;

public interface AddDateService {

    GenericResponse addDaysToDate(DateBody dateBody);
}
