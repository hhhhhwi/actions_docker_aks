package com.hwiyeon.actions_docker_aks.login.service;

import com.hwiyeon.actions_docker_aks.login.LoginMember;

public interface LoginMemberService {
    LoginMember getLoginMember(String email, String password);
}
