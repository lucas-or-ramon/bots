package br.com.devcanoa.bots.finance;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Locale;

@RestController
@RequestMapping
public class ReceiveMessage {

    public static final String FINANCE_API = System.getenv("FINANCE_API");

    @PostMapping(value = "/receive", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> receive(@RequestParam("Body") String text,
                                          @RequestParam("From") String from,
                                          @RequestParam("ProfileName") String profileName) {
        String report;

        switch (text.toLowerCase(Locale.forLanguageTag("pt"))) {
            case "anual" -> report = getAnnualResume();
            case "mensal" -> report = getMonthlyResume();
            default -> report = getOtherOptions(text);
        }

        return ResponseEntity.ok(getMessagingResponse(report));
    }

    private String getOtherOptions(String text) {
        if (text.contains("/")) {
            var date = getDate(text);

            var revenue = new RestTemplate().getForEntity((FINANCE_API + "/revenue/resume/" + date.getYear() + "/" + date.getMonth().getValue()), RegistryList.class).getBody();
            var expenditure = new RestTemplate().getForEntity((FINANCE_API + "/expenditure/resume/" + date.getYear() + "/" + date.getMonth().getValue()), RegistryList.class).getBody();

            return revenue != null && expenditure != null
                    ? (revenue.toString("Receitas") + expenditure.toString("Despesas"))
                    : "Erro do Obter Receitas e Despesas";
        }
        return "Erro ao processar requisição :(";
    }

    private LocalDate getDate(String text) {
        var indexOfSlash = text.indexOf("/");

        var month = FinanceMonth.getOrdinalByName(text.substring(0, indexOfSlash));

        var year = Integer.parseInt(text.substring(indexOfSlash + 1));

        return LocalDate.of(year, month, 1);
    }

    private String getAnnualResume() {
        var report = new RestTemplate().getForEntity(FINANCE_API + "/resume/annual/2022/08", AnnualResume.class).getBody();

        return report != null ? report.toString() : "Erro ao Gerar Relatório Anual";
    }

    private String getMonthlyResume() {
        var report = new RestTemplate().getForEntity(FINANCE_API + "/resume/monthly/2022/08", MonthlyResume.class).getBody();

        return report != null ? report.toString() : "Erro ao Gerar Relatório Mensal";
    }

    private String getMessagingResponse(String report) {
        return new MessagingResponse.Builder().message(new Message.Builder().body(new Body.Builder(report + "\n\n").build()).build()).build().toXml();
    }
}
