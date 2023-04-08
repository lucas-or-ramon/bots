package br.com.devcanoa.bots.calendar;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalendarConfig {

    private final String applicationName;
    private final JsonFactory jsonFactory;
    private final String tokensDirectoryPath;
    private final String credentialsFilePath;

    public CalendarConfig(@Value("${calendar.application.name}") String applicationName,
                          @Value("${calendar.tokens.dir.path}") String tokensDirectoryPath,
                          @Value("${calendar.credentials.file.path}") String credentialsFilePath) {
        this.jsonFactory = GsonFactory.getDefaultInstance();
        this.applicationName = applicationName;
        this.tokensDirectoryPath = tokensDirectoryPath;
        this.credentialsFilePath = credentialsFilePath;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public JsonFactory getJsonFactory() {
        return jsonFactory;
    }

    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }
}
