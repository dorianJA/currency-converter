package ru.max.currencyconverter.currapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.max.currencyconverter.currapp.model.dto.ConvertRequestDto;
import ru.max.currencyconverter.currapp.model.entity.Currency;
import ru.max.currencyconverter.currapp.repository.UserRepository;
import ru.max.currencyconverter.currapp.service.ConverterHistoryService;
import ru.max.currencyconverter.currapp.service.CurrencyService;
import ru.max.currencyconverter.currapp.service.currencyjob.CurrencyConverter;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by maxxii on 22.03.2021.
 */
@Controller
public class ConverterController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyConverter converter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConverterHistoryService converterHistoryService;


    @GetMapping("/converter")
    public String mainPage(ModelMap modelMap) {
        currencyService.updateActualCurrency();
        List<Currency> allCurrency = currencyService.findAllCurrency();
        modelMap.addAttribute("listCurrency", allCurrency);
        return "converter";
    }



    @PostMapping("/convert")
    @ResponseBody
    public ResponseEntity<?> converter(@RequestBody @Valid ConvertRequestDto requestDto){
        if(requestDto.getValue().compareTo(BigDecimal.ZERO) < 0){
            return ResponseEntity.badRequest().body("Укажите корректную сумму!");
        }

        String fromChar = requestDto.getFrom().split(" ")[0];
        String toChar = requestDto.getTo().split(" ")[0];
        Currency currencyFrom = currencyService.findCurrencyByCharCode(fromChar);
        Currency currencyTo = currencyService.findCurrencyByCharCode(toChar);
        if(currencyFrom.getLastUpdate().isBefore(LocalDate.now()) || currencyTo.getLastUpdate().isBefore(LocalDate.now())){
            currencyService.updateActualCurrency();
        }
        BigDecimal result = converter.convert(currencyFrom, currencyTo, requestDto.getValue());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        converterHistoryService.saveData(currencyFrom,currencyTo,requestDto.getValue(),result
                ,userRepository.findByLogin(user.getUsername()));

        return ResponseEntity.ok(result);

    }

}
