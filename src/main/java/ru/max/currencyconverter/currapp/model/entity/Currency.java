package ru.max.currencyconverter.currapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by maxxii on 18.03.2021.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    private String id;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "nominal")
    private int nominal;
    @Column(name = "num_code")
    private int numCode;
    @Column(name = "name")
    private String name;
    @Column(name = "char_code")
    private String charCode;
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    public Currency(BigDecimal value, int nominal, int numCode, String name, String charCode) {
        this.value = value;
        this.nominal = nominal;
        this.numCode = numCode;
        this.name = name;
        this.charCode = charCode;
    }
}
