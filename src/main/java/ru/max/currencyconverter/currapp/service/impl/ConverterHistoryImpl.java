package ru.max.currencyconverter.currapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.max.currencyconverter.currapp.model.entity.Currency;
import ru.max.currencyconverter.currapp.model.entity.CurrencyConverterHistory;
import ru.max.currencyconverter.currapp.model.entity.User;
import ru.max.currencyconverter.currapp.repository.ConverterHistoryRepository;
import ru.max.currencyconverter.currapp.service.ConverterHistoryService;
import ru.max.currencyconverter.currapp.service.currencyjob.CurrencyConverter;

import java.math.BigDecimal;

/**
 * Created by maxxii on 23.03.2021.
 */
@Service
public class ConverterHistoryImpl implements ConverterHistoryService {

    @Autowired
    private ConverterHistoryRepository converterHistoryRepository;
    @Autowired
    private CurrencyConverter converter;

    @Override
    public void saveData(Currency fromCurrency, Currency toCurrency,
                         BigDecimal amount, BigDecimal result, User user) {


        CurrencyConverterHistory history = new CurrencyConverterHistory();
        history.setOriginalCurrency(fromCurrency.getCharCode()+" "+fromCurrency.getName());
        history.setTargetCurrency(toCurrency.getCharCode()+" "+toCurrency.getName());
        history.setAmount(amount);
        history.setAmountReceived(result);
        history.setCurrentRate(converter.convert(fromCurrency,toCurrency,new BigDecimal(1)));
        history.setUser(user);
        converterHistoryRepository.save(history);
    }
}
