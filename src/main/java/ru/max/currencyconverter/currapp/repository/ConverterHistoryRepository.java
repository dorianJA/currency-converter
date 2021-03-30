package ru.max.currencyconverter.currapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.max.currencyconverter.currapp.model.entity.CurrencyConverterHistory;

/**
 * Created by maxxii on 23.03.2021.
 */
@Component
public interface ConverterHistoryRepository extends JpaRepository<CurrencyConverterHistory,Long> {
}
