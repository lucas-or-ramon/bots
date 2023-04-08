package br.com.devcanoa.bots.finance;

import java.time.LocalDate;

public record Registry(LocalDate date, double value, String description) {

    @Override
    public String toString() {
        return description + " - R$ " + value + "\n";
    }
}
