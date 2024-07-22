package com.hwiyeon.actions_docker_aks.error.exception;

public class MemberNotExistsException extends NotExistsException {

    public MemberNotExistsException() {
        super("해당하는 고객");
    }
}
