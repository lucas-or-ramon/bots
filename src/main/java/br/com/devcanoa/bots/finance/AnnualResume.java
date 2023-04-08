package br.com.devcanoa.bots.finance;

import java.util.Comparator;
import java.util.List;

public record AnnualResume(double balance, double totalRevenue, double totalExpenditure, List<MonthlyResume> monthlyResumeList) {

    @Override
    public String toString() {
        sortMonthlyResumeListByDate();

        return "\n\t" + bold(italic(monthlyResumeList.get(0).getMonthSlashYear()))
                + " Até "
                + bold(italic(monthlyResumeList.get(monthlyResumeList.size() - 1).getMonthSlashYear())) + "\n"
                + bold(italic("Total de Entradas:")) + spaceBefore(bold("R$ " + totalRevenue)) + "\n"
                + bold(italic("Total de Saídas:")) + spaceBefore(bold("R$ " + totalExpenditure)) + "\n"
                + bold(italic("Saldo:")) + spaceBefore(bold("R$ " + balance)) + "\n\n"
                + monthlyResumeList;
    }

    private String bold(String text) {
        return "*" + text + "*";
    }

    private String italic(String text) {
        return "_" + text + "_";
    }

    public void sortMonthlyResumeListByDate() {
        monthlyResumeList.sort(Comparator.comparing(MonthlyResume::date).reversed());
    }

    private String spaceBefore(String text) {
        return " " + text;
    }
}
