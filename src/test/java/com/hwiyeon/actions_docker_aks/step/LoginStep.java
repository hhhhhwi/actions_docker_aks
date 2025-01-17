package com.hwiyeon.actions_docker_aks.step;

import static com.hwiyeon.actions_docker_aks.login.LoginMember.COOKIE_NAME_FOR_LOGIN;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.DUMMY_ADMIN_EMAIL;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.DUMMY_ADMIN_PASSWORD;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_EMAIL;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_PASSWORD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import com.hwiyeon.actions_docker_aks.login.dto.LoginRequest;

public class LoginStep {

    public static String 회원_토큰_생성() {
        return 로그인(FIRST_USER_EMAIL, FIRST_USER_PASSWORD).cookie(COOKIE_NAME_FOR_LOGIN);
    }

    public static String 관리자_토큰_생성() {
        return 로그인(DUMMY_ADMIN_EMAIL, DUMMY_ADMIN_PASSWORD).cookie(COOKIE_NAME_FOR_LOGIN);
    }

    public static String 토큰_생성(String email, String password) {
        return 로그인(email, password).cookie(COOKIE_NAME_FOR_LOGIN);
    }

    public static ExtractableResponse<Response> 로그인(String email, String password) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new LoginRequest(email, password))
                .when().post("/login")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그아웃() {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when().post("/logout")
                .then().log().all()
                .extract();
    }
}
