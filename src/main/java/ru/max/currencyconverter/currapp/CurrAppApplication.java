package ru.max.currencyconverter.currapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrAppApplication.class, args);
    }

}
