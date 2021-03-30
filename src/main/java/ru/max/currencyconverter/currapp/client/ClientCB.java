package ru.max.currencyconverter.currapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.max.currencyconverter.currapp.model.dto.ValCursDto;

import java.time.LocalDate;

/**
 * Created by maxxii on 18.03.2021.
 */
@FeignClient(url = "${cb.actual-currency}", name = "CB-CLIENT")
public interface ClientCB {

    @GetMapping
    ValCursDto dailyCurrency();

    @GetMapping
    ValCursDto currencyFromDate(@RequestParam("date_req") String date);
}
