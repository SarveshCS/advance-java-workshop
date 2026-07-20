package com.workshop.printer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.workshop.printer.config.AppConfig;
import com.workshop.printer.model.Printer;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Printer printer = context.getBean(Printer.class);
            printer.displayPrinterDetails();
        }
    }
}