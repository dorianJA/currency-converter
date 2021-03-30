package ru.max.currencyconverter.currapp.service;

import ru.max.currencyconverter.currapp.model.entity.Currency;

import java.util.List;

/**
 * Created by maxxii on 19.03.2021.
 */
public interface CurrencyService {

    Currency findCurrencyById(String id);

    Currency findCurrencyByCharCode(String charCode);

    List<Currency> findAllCurrency();

    void updateActualCurrency();

    void saveCurrency(Currency currency);


}
