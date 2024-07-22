package com.hwiyeon.actions_docker_aks.error.exception;

public class MemberAlreadyExistsException extends AlreadyExistsException {
    public MemberAlreadyExistsException() {
        super("해당하는 고객");
    }
}
