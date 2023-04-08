package br.com.devcanoa.bots.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class SendMessage {

    public final String twilioAuthToken;
    public final String twilioAccountSid;

    public SendMessage(@Value("${twilio.auth.token}") String twilioAuthToken,
                       @Value("${twilio.account.sid}") String twilioAccountSid) {
        this.twilioAuthToken = twilioAuthToken;
        this.twilioAccountSid = twilioAccountSid;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessageTo(@RequestParam("to") String to) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        var message = Message.creator(
                        new PhoneNumber("whatsapp:" + to),
                        new PhoneNumber("whatsapp:+14155238886"),
                        "Consegui Enviar a Mensagem!!")
                .create();

        return ResponseEntity.ok(message.getSid());
    }
}
