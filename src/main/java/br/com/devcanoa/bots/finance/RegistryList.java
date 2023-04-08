package br.com.devcanoa.bots.finance;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public record RegistryList(List<Registry> registries, int total) {

    public String toString(String type) {
        return type + " " + getMonthSlashYear(registries.get(0).date()) + "\n"
                + registries + "\n"
                + "Total: R$ " + total + "\n\n";
    }

    public String getMonthSlashYear(LocalDate date) {
        return "[" + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")) + "/" + date.getYear() + "]";
    }
}