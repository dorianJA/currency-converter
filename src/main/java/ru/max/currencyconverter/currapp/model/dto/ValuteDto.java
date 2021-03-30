package ru.max.currencyconverter.currapp.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.max.currencyconverter.currapp.model.entity.Currency;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by maxxii on 17.03.2021.
 */
@Getter
@Setter
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuteDto {

    @XmlAttribute(name = "ID")
    private String id;

    private String CharCode;

    private String Value;

    private String Nominal;

    private String NumCode;

    private String Name;


    public Currency toEntity(ValuteDto valuteDto){
        Currency currency = new Currency();
        currency.setId(valuteDto.getId());
        currency.setCharCode(valuteDto.getCharCode());
        currency.setName(valuteDto.getName());
        currency.setNominal(Integer.parseInt(valuteDto.getNominal()));
        valuteDto.setValue(valuteDto.getValue().replace(",","."));
        currency.setValue(DatatypeConverter.parseDecimal(valuteDto.getValue()));
        currency.setNumCode(Integer.parseInt(valuteDto.getNumCode()));
        return currency;
    }

}
