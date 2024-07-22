package com.hwiyeon.actions_docker_aks.error.exception;

import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

public class NotExistsException extends RuntimeException {

    public NotExistsException(String message) {
        super(message + CustomErrorMessage.NOT_EXISTS_EXCEPTION);
    }
}
