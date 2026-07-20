<!-- markdownlint-disable -->

# Question 2 - Printer Setter Injection Using Spring

## Exact Question

Implement a `Printer` class that depends on an `Ink` class. Use Setter Injection to inject the dependency and configure the beans using Java-based configuration. Display printer details in the main application.

Instructions:

1. Create a new Maven project.
2. Include all relevant dependencies in `pom.xml`.
3. Do necessary changes in `App.java` and configuration files.

## Solution Summary

This folder contains a simple Spring Core Maven project named `PrinterSetterInjection`. The `Printer` class depends on the `Ink` class. Spring creates both objects in `AppConfig.java`, then injects the `Ink` object into the `Printer` object using the `setInk(...)` setter method.

## Files

| File | Purpose |
| --- | --- |
| [PrinterSetterInjection/pom.xml](PrinterSetterInjection/pom.xml) | Maven project file with the Spring Context dependency and run plugin. |
| [PrinterSetterInjection/src/main/java/com/workshop/printer/App.java](PrinterSetterInjection/src/main/java/com/workshop/printer/App.java) | Starts the Spring container and displays printer details. |
| [PrinterSetterInjection/src/main/java/com/workshop/printer/config/AppConfig.java](PrinterSetterInjection/src/main/java/com/workshop/printer/config/AppConfig.java) | Java-based Spring configuration that creates `Ink` and `Printer` beans. |
| [PrinterSetterInjection/src/main/java/com/workshop/printer/model/Ink.java](PrinterSetterInjection/src/main/java/com/workshop/printer/model/Ink.java) | Represents ink details such as brand, color, and type. |
| [PrinterSetterInjection/src/main/java/com/workshop/printer/model/Printer.java](PrinterSetterInjection/src/main/java/com/workshop/printer/model/Printer.java) | Represents printer details and receives the `Ink` dependency using setter injection. |

## How to Run

From the repository root:

```powershell
cd .\day_7\Question_2\PrinterSetterInjection
mvn clean compile exec:java
```

Expected output:

```text
Printer Details
Printer Name: OfficeJet 250
Printer Type: Inkjet Printer
Ink Brand: HP Original Ink
Ink Color: Black
Ink Type: Cartridge
```