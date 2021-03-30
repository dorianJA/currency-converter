package ru.max.currencyconverter.currapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

/**
 * Created by maxxii on 17.03.2021.
 */

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ValCursDto {

    @XmlAttribute
    private String name;
    @XmlAttribute(name = "Date")
    private String date;
    @XmlElement(name = "Valute")
    private ValuteDto[] valuteDto;


}
