package com.workshop.printer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.workshop.printer.model.Ink;
import com.workshop.printer.model.Printer;

@Configuration
public class AppConfig {
    @Bean
    public Ink ink() {
        Ink ink = new Ink();
        ink.setBrand("HP Original Ink");
        ink.setColor("Black");
        ink.setType("Cartridge");
        return ink;
    }

    @Bean
    public Printer printer(Ink ink) {
        Printer printer = new Printer();
        printer.setPrinterName("OfficeJet 250");
        printer.setPrinterType("Inkjet Printer");
        printer.setInk(ink);
        return printer;
    }
}