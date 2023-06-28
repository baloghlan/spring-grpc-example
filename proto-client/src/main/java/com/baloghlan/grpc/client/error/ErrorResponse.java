package com.baloghlan.grpc.client.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private HttpStatus status;
    private Integer error;
    private List<String> messages;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date;

    private ErrorResponse() {
        this.date = LocalDateTime.now();
        this.messages = new ArrayList<>();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.messages.add(message);
        this.error = status.value();
    }

    public ErrorResponse(HttpStatus status, List<String> messages) {
        this();
        this.status = status;
        this.messages.addAll(messages);
        this.error = status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
