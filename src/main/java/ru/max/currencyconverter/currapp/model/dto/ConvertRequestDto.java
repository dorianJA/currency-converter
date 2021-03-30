package ru.max.currencyconverter.currapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by maxxii on 22.03.2021.
 */
@Getter
@Setter
public class ConvertRequestDto {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull(message = "Укажите сумму")
    private BigDecimal value;
}
