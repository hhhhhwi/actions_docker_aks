package com.hwiyeon.actions_docker_aks.error.exception;

import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message + CustomErrorMessage.ALREADY_EXISTS_EXCEPTION);
    }
}
