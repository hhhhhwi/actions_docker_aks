package com.hwiyeon.actions_docker_aks.login.controller;

import static com.hwiyeon.actions_docker_aks.login.LoginMember.COOKIE_NAME_FOR_LOGIN;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hwiyeon.actions_docker_aks.login.LoginMember;
import com.hwiyeon.actions_docker_aks.login.dto.LoginRequest;
import com.hwiyeon.actions_docker_aks.login.dto.LoginResponse;
import com.hwiyeon.actions_docker_aks.login.service.LoginService;
import com.hwiyeon.actions_docker_aks.member.MemberRole;
import com.hwiyeon.actions_docker_aks.util.CookieUtils;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        LoginMember loginMember = loginService.getLoginMember(loginRequest.getEmail(),
            loginRequest.getPassword());

        ResponseCookie cookie = ResponseCookie
                                    .from(COOKIE_NAME_FOR_LOGIN, loginService.createToken(loginMember))
                                    .path("/")
                                    .httpOnly(true)
                                    .secure(true)
                                    .build();

        String uri = "/";

        if (loginMember.hasSameRole(MemberRole.ADMIN)) {
            uri = "/admin";
        }

        return ResponseEntity.ok().location(URI.create(uri))
            .header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @GetMapping("/login/check")
    public ResponseEntity<LoginResponse> checkLogin(LoginMember loginMember) {
        return ResponseEntity.ok().body(new LoginResponse(loginMember.getName()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        CookieUtils.deleteCookie(response, COOKIE_NAME_FOR_LOGIN);

        return ResponseEntity.ok().build();
    }
}
