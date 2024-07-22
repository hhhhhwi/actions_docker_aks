package com.hwiyeon.actions_docker_aks.error;

public class CustomExceptionResponse {

    private String message;

    public CustomExceptionResponse(String message) {
        this.message = message;
    }

    public static CustomExceptionResponse from(String exceptionMessage,
        String roomescapeErrorMessage) {
        return new CustomExceptionResponse(exceptionMessage + roomescapeErrorMessage);
    }

    public static CustomExceptionResponse of(String roomescapeErrorMessage) {
        return new CustomExceptionResponse(roomescapeErrorMessage);
    }

    public String getMessage() {
        return message;
    }
}
