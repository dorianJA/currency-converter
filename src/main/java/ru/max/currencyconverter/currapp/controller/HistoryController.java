package ru.max.currencyconverter.currapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.max.currencyconverter.currapp.model.entity.CurrencyConverterHistory;
import ru.max.currencyconverter.currapp.repository.UserRepository;

import java.util.List;

/**
 * Created by maxxii on 25.03.2021.
 */
@Controller
public class HistoryController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/history")
    public String historyPage(ModelMap modelMap){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CurrencyConverterHistory> history = userRepository.findByLogin(user.getUsername()).getHistory();
        modelMap.addAttribute("user_history",history);
        return "history";
    }

}
