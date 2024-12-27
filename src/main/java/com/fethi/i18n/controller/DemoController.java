package com.fethi.i18n.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/locale")
public class DemoController {

    private final MessageSource messageSource;


    public DemoController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/greeting")
    public String getGreeting(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }

    @GetMapping("/currency")
    public String getCurrency(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(12345.67);
    }

    @GetMapping("/date")
    public String getDate(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, locale);
        return dateFormatter.format(new Date());
    }
}
