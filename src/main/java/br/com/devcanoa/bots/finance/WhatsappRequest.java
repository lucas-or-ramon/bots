package br.com.devcanoa.bots.finance;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WhatsappRequest(@JsonProperty("Body") String body, @JsonProperty("From") String from, @JsonProperty("ProfileName") String profileName) {}
