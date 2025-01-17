package com.hwiyeon.actions_docker_aks.login;

import static com.hwiyeon.actions_docker_aks.login.LoginMember.COOKIE_NAME_FOR_LOGIN;
import static com.hwiyeon.actions_docker_aks.util.CookieUtils.getCookie;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.hwiyeon.actions_docker_aks.error.exception.AuthenticationException;

public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginMemberArgumentResolver(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            throw new AuthenticationException();
        }

        String token = getCookie(servletRequest, COOKIE_NAME_FOR_LOGIN)
            .orElseThrow(AuthenticationException::new)
            .getValue();

        return jwtTokenProvider.getLoginMember(token);
    }
}
