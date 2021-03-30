package ru.max.currencyconverter.currapp.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.max.currencyconverter.currapp.model.entity.Currency;
import ru.max.currencyconverter.currapp.service.currencyjob.CurrencyConverter;

import java.math.BigDecimal;

/**
 * Created by maxxii on 21.03.2021.
 */
@SpringBootTest
public class CurrencyConverterTest {

    @Autowired
    private CurrencyConverter converter;
    private Currency fromCurrency;
    private Currency toCurrency;


    @Test
    public void convertFromRUBTest() {
        toCurrency = new Currency(new BigDecimal("74.1390"), 1, 840, "Доллар США", "USD");
        //из RUB в USD
        BigDecimal resultCase1 = converter.convertFromRUB(toCurrency, new BigDecimal(1150));
        BigDecimal expectCase1 = new BigDecimal("15.5114");
        //из RUB в USD
        BigDecimal resultCase2 = converter.convertFromRUB(toCurrency, new BigDecimal(27890));
        BigDecimal expectCase2 = new BigDecimal("376.1853");
        //из RUB в USD
        BigDecimal resultCase3 = converter.convertFromRUB(toCurrency, new BigDecimal(1579343));
        BigDecimal expectCase3 = new BigDecimal("21302.4589");


        Assertions.assertEquals(expectCase1, resultCase1);
        Assertions.assertEquals(expectCase2, resultCase2);
        Assertions.assertEquals(expectCase3, resultCase3);
    }

    @Test
    public void convertToRUBTest() {
        fromCurrency = new Currency(new BigDecimal("74.1390"), 1, 840, "Доллар США", "USD");
        //из USD в RUB
        BigDecimal resultCase1 = converter.convertToRUB(fromCurrency, new BigDecimal(1));
        BigDecimal expectCase1 = new BigDecimal("74.1390");
        //из USD в RUB
        BigDecimal resultCase2 = converter.convertToRUB(fromCurrency, new BigDecimal(151));
        BigDecimal expectCase2 = new BigDecimal("11194.9890");
        //из USD в RUB
        BigDecimal resultCase3 = converter.convertToRUB(fromCurrency, new BigDecimal(1009000));
        BigDecimal expectCase3 = new BigDecimal("74806251.0000");

        Assertions.assertEquals(expectCase1, resultCase1);
        Assertions.assertEquals(expectCase2, resultCase2);
        Assertions.assertEquals(expectCase3, resultCase3);
    }

    @Test
    public void convertCurrency() {
        fromCurrency = new Currency(new BigDecimal("74.1390"), 1, 840, "Доллар США", "USD");
        toCurrency = new Currency(new BigDecimal("88.4330"),1,978,"Евро","EUR");
        //из USD в EUR
        BigDecimal resultCase1 = converter.convert(fromCurrency, toCurrency, new BigDecimal(1));
        BigDecimal expectCase1 = new BigDecimal("0.8384");
        //из USD в EUR
        BigDecimal resultCase2 = converter.convert(fromCurrency, toCurrency, new BigDecimal(150));
        BigDecimal expectCase2 = new BigDecimal("125.7545");
        //из EUR в USD
        BigDecimal resultCase3 = converter.convert(toCurrency, fromCurrency, new BigDecimal(1));
        BigDecimal expectCase3 = new BigDecimal("1.1928");
        //из EUR в USD
        BigDecimal resultCase4 = converter.convert(toCurrency, fromCurrency, new BigDecimal(150));
        BigDecimal expectCase4 = new BigDecimal("178.9200");

        Assertions.assertEquals(expectCase1,resultCase1);
        Assertions.assertEquals(expectCase2,resultCase2);
        Assertions.assertEquals(expectCase3,resultCase3);
        Assertions.assertEquals(expectCase4,resultCase4);
    }

    @Test
    public void convertToRUBWithNominalMoreThenOne(){
        fromCurrency = new Currency(new BigDecimal("17.7217"),100,398,"Казахстанских тенге","KZT");
        //из KZT в RUB
        BigDecimal resultCase1 = converter.convertToRUB(fromCurrency,new BigDecimal(1));
        BigDecimal expectCase1 = new BigDecimal("0.1772");
        //из KZT в RUB
        BigDecimal resultCase2 = converter.convertToRUB(fromCurrency,new BigDecimal(1000050));
        BigDecimal expectCase2 = new BigDecimal("177225.8608");

        Assertions.assertEquals(expectCase1,resultCase1);
        Assertions.assertEquals(expectCase2,resultCase2);
    }

    @Test
    public void convertWithNominalMoreTHenOne(){
        fromCurrency = new Currency(new BigDecimal("17.7217"),100,398,"Казахстанских тенге","KZT");
        toCurrency = new Currency(new BigDecimal("74.6085"), 1, 840, "Доллар США", "USD");
        //из KZT в USD
        BigDecimal resultCase1 = converter.convert(fromCurrency, toCurrency, new BigDecimal(1));
        BigDecimal expectCase1 = new BigDecimal("0.0024");
        //из KZT в USD
        BigDecimal resultCase2 = converter.convert(fromCurrency, toCurrency, new BigDecimal(15000));
        BigDecimal expectCase2 = new BigDecimal("35.6294");
        //из USD в KZT
        BigDecimal resultCase3 = converter.convert(toCurrency,fromCurrency,new BigDecimal(1));
        BigDecimal expectCase3 = new BigDecimal("421.0008");
        //из USD в KZT
        BigDecimal resultCase4 = converter.convert(toCurrency,fromCurrency,new BigDecimal(150));
        BigDecimal expectCase4 = new BigDecimal("63150.1210");

        Assertions.assertEquals(expectCase1,resultCase1);
        Assertions.assertEquals(expectCase2,resultCase2);
        Assertions.assertEquals(expectCase3,resultCase3);
        Assertions.assertEquals(expectCase4,resultCase4);

    }

    @Test
    public void converterV2(){
        fromCurrency = new Currency(new BigDecimal(1),1,1,"Российский рубль","RUB");
        toCurrency = new Currency(new BigDecimal("74.6085"), 1, 840, "Доллар США", "USD");

        BigDecimal resultCase1 = converter.convert(fromCurrency, toCurrency, new BigDecimal(10000001));
        BigDecimal expectCase1 = new BigDecimal("134032.9989");

        Assertions.assertEquals(expectCase1,resultCase1);

    }

}
