package br.com.devcanoa.bots.twilio;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receive")
public class TwilioReceiveMessage {

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> receive(@RequestParam("Body") String text,
                                          @RequestParam("From") String from,
                                          @RequestParam("ProfileName") String profileName) {

        return ResponseEntity.ok("Receive Works: Text: " + text + " From: " + from + " ProfileName: " + profileName);
    }
}
