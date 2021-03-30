package ru.max.currencyconverter.currapp.service.impl;

import org.springframework.stereotype.Service;
import ru.max.currencyconverter.currapp.client.ClientCB;
import ru.max.currencyconverter.currapp.model.dto.ValCursDto;
import ru.max.currencyconverter.currapp.model.entity.Currency;
import ru.max.currencyconverter.currapp.repository.CurrencyRepository;
import ru.max.currencyconverter.currapp.service.CurrencyService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by maxxii on 19.03.2021.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {


    private final ClientCB clientCB;
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(ClientCB clientCB, CurrencyRepository currencyRepository) {
        this.clientCB = clientCB;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency findCurrencyById(String id) {
        Optional<Currency> currById = currencyRepository.findById(id);
        if (currById.isPresent()) {
            return currById.get();
        } else {
            throw new EntityNotFoundException("Currency with id: " + id + " not found");
        }
    }

    @Override
    public Currency findCurrencyByCharCode(String charCode) {
        Currency currency = currencyRepository.findByCharCode(charCode);
        if(currency==null){
            throw new EntityNotFoundException("Currency with char code: "+charCode+" not found");
        }
        return currency;
    }

    @Override
    public List<Currency> findAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public void updateActualCurrency() {
        ValCursDto valCursDto = clientCB.currencyFromDate(currentDateFormatter(LocalDate.now()));
        Stream.of(valCursDto.getValuteDto())
                .map(valuteDto -> valuteDto.toEntity(valuteDto))
                .forEach(currency -> currencyRepository.save(currency));

        Currency currency = new Currency(new BigDecimal(1),1,1,"Российский рубль","RUB");
        currency.setId("RR9999");
        currencyRepository.save(currency);
    }

    @Override
    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    private String currentDateFormatter(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(formatter);
    }
}
