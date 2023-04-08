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
@RequestMapping("/send")
public class TwilioSendMessage {

    private final String twilioNumber;
    private final String twilioAuthToken;
    private final String twilioAccountSid;

    public TwilioSendMessage(@Value("${twilio.number}") String twilioNumber,
                             @Value("${twilio.auth.token}") String twilioAuthToken,
                             @Value("${twilio.account.sid}") String twilioAccountSid) {
        this.twilioNumber = twilioNumber;
        this.twilioAuthToken = twilioAuthToken;
        this.twilioAccountSid = twilioAccountSid;
    }

    @GetMapping
    public ResponseEntity<String> sendMessageTo(@RequestParam("to") String to, @RequestParam("message") String message) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        var twilioMessage = Message.creator(
                        new PhoneNumber("whatsapp:+" + to),
                        new PhoneNumber("whatsapp:+" + twilioNumber),
                        message)
                .create();

        return ResponseEntity.ok(twilioMessage.getSid());
    }
}
