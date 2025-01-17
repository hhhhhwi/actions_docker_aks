package com.hwiyeon.actions_docker_aks.login.service;

import org.springframework.stereotype.Service;
import com.hwiyeon.actions_docker_aks.login.JwtTokenProvider;
import com.hwiyeon.actions_docker_aks.login.LoginMember;

@Service
public class LoginService {

    private final LoginMemberService loginMemberService;

    private final JwtTokenProvider jwtTokenProvider;

    public LoginService(LoginMemberService loginMemberService, JwtTokenProvider jwtTokenProvider) {
        this.loginMemberService = loginMemberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginMember getLoginMember(String email, String password) {
        return loginMemberService.getLoginMember(email, password);
    }

    public String createToken(LoginMember member) {
        return jwtTokenProvider.createToken(member.getId(), member.getName(), member.getRole());
    }
}
