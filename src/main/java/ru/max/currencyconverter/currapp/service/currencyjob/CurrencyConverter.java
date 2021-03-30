package ru.max.currencyconverter.currapp.service.currencyjob;

import org.springframework.stereotype.Component;
import ru.max.currencyconverter.currapp.model.entity.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by maxxii on 19.03.2021.
 */
@Component
public class CurrencyConverter {


    public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount) {
        BigDecimal fromCur = fromCurrency.getValue().divide(new BigDecimal(fromCurrency.getNominal()));
        BigDecimal toCur = toCurrency.getValue().divide(new BigDecimal(toCurrency.getNominal()));
        BigDecimal result = fromCur.divide(toCur, 15, RoundingMode.HALF_DOWN);
        return result.multiply(amount).setScale(4,RoundingMode.HALF_DOWN);
    }

    public BigDecimal convertFromRUB(Currency toCurrency, BigDecimal amountRUB) {
        BigDecimal divide = amountRUB.divide(toCurrency.getValue(), 8, RoundingMode.HALF_DOWN);
        return divide.multiply(new BigDecimal(toCurrency.getNominal())).setScale(4,RoundingMode.HALF_DOWN);
    }

    public BigDecimal convertToRUB(Currency fromCurrency, BigDecimal amount) {
        return fromCurrency.getValue().divide(new BigDecimal(fromCurrency.getNominal()))
                .multiply(amount).setScale(4,RoundingMode.HALF_DOWN);
    }

}
