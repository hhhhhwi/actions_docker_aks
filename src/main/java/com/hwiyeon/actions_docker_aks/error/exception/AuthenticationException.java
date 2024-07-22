package com.hwiyeon.actions_docker_aks.error.exception;

import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super(CustomErrorMessage.AUTHENTICATION_EXCEPTION);
    }
}
