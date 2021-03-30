package ru.max.currencyconverter.currapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by maxxii on 19.03.2021.
 */
@Controller
public class AuthController {


    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false)Boolean error, ModelMap modelMap) {
        if(error!=null && error){
            modelMap.addAttribute("error","Invalid login or password");
            return "login";
        }
        return "login";
    }



}
