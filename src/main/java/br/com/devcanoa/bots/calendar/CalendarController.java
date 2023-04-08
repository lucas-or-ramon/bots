package br.com.devcanoa.bots.calendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @RequestMapping("/events")
    public List<String> getCalendarEvents() throws IOException, GeneralSecurityException {
        return calendarService.getCalendarEvents().stream()
                .map(event -> event.getSummary() + " - " + event.getStart().getDateTime())
                .toList();
    }
}
