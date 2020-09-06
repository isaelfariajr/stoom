package br.com.stoom.apiEndereco.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("errorCode")
    private String code;

    @JsonProperty("errorMessage")
    private String message;

    public ErrorMessage(String code, String message) {
        this.date = LocalDateTime.now();
        this.code = code;
        this.message = message;
    }
}