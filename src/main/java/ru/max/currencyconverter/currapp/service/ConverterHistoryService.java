package ru.max.currencyconverter.currapp.service;

import ru.max.currencyconverter.currapp.model.entity.Currency;
import ru.max.currencyconverter.currapp.model.entity.User;

import java.math.BigDecimal;

/**
 * Created by maxxii on 23.03.2021.
 */
public interface ConverterHistoryService {

    void saveData(Currency fromCurrency, Currency toCurrency,
                  BigDecimal amount, BigDecimal result, User user);


}
