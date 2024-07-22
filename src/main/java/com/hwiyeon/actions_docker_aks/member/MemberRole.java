package com.hwiyeon.actions_docker_aks.member;

import com.hwiyeon.actions_docker_aks.error.exception.IllegalMemberRoleException;

public enum MemberRole {
    ADMIN,
    MEMBER;

    public static MemberRole of(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalMemberRoleException();
        }
    }
}
