package ru.max.currencyconverter.currapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by maxxii on 18.03.2021.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "history_converter")
public class CurrencyConverterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "original_currency")
    private String originalCurrency;
    @Column(name = "target_currency")
    private String targetCurrency;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "received_amount")
    private BigDecimal amountReceived;
    @CreationTimestamp
    @Column(name = "operation_date")
    private LocalDate operationDate;
    @Column(name = "current_rate")
    private BigDecimal currentRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
