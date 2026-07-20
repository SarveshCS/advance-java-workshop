package com.workshop.printer.model;

public class Printer {
    private String printerName;
    private String printerType;
    private Ink ink;

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterType() {
        return printerType;
    }

    public void setPrinterType(String printerType) {
        this.printerType = printerType;
    }

    public Ink getInk() {
        return ink;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public void displayPrinterDetails() {
        System.out.println("Printer Details");
        System.out.println("Printer Name: " + printerName);
        System.out.println("Printer Type: " + printerType);
        System.out.println("Ink Brand: " + ink.getBrand());
        System.out.println("Ink Color: " + ink.getColor());
        System.out.println("Ink Type: " + ink.getType());
    }
}