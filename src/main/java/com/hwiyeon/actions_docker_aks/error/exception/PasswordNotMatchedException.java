package com.hwiyeon.actions_docker_aks.error.exception;

import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException() {
        super(CustomErrorMessage.NOT_MATCHED_PASSWORD_EXCEPTION);
    }
}
