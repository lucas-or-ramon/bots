package br.com.devcanoa.bots.finance;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Stream;

public enum FinanceMonth {
    JANEIRO("janeiro"),
    FEVEREIRO("fevereiro"),
    MARCO("março"),
    ABRIL("abril"),
    MAIO("maio"),
    JUNHO("junho"),
    JULHO("julho"),
    AGOSTO("agosto"),
    SETEMBRO("setembro"),
    OUTUBRO("outubro"),
    NOVEMBRO("novembro"),
    DEZEMBRO("dezembro");

    private final String name;

    FinanceMonth(String name) {
        this.name = name;
    }

    public static int getOrdinalByName(String name) {
        return 1 + Stream.of(FinanceMonth.values())
                .filter(financeMonth -> Objects.equals(financeMonth.name, name))
                .findFirst()
                .map(Enum::ordinal)
                .orElseThrow(IllegalArgumentException::new);
    }
}
