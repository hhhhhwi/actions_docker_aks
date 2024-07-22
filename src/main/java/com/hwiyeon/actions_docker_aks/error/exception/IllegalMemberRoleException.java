package com.hwiyeon.actions_docker_aks.error.exception;

import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

public class IllegalMemberRoleException extends RuntimeException{

    public IllegalMemberRoleException() {
        super(CustomErrorMessage.ILLEGAL_MEMBER_ROLE_EXCEPTION);
    }
}
