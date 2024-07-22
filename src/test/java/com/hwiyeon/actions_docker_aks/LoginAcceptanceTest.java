package com.hwiyeon.actions_docker_aks;

import static com.hwiyeon.actions_docker_aks.error.CustomErrorMessage.NOT_EXISTS_EXCEPTION;
import static com.hwiyeon.actions_docker_aks.error.CustomErrorMessage.NOT_MATCHED_PASSWORD_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static com.hwiyeon.actions_docker_aks.login.LoginMember.COOKIE_NAME_FOR_LOGIN;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_EMAIL;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_NAME;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_PASSWORD;
import static com.hwiyeon.actions_docker_aks.step.LoginStep.로그아웃;
import static com.hwiyeon.actions_docker_aks.step.LoginStep.로그인;
import static com.hwiyeon.actions_docker_aks.step.LoginStep.토큰_생성;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;

@DisplayName("로그인 관련 api 호출 테스트")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestExecutionListeners(listeners = RestAssuredTestConfig.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoginAcceptanceTest {

    @Test
    void 로그인_성공() {
        ExtractableResponse<Response> response = 로그인(FIRST_USER_EMAIL, FIRST_USER_PASSWORD);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.cookie(COOKIE_NAME_FOR_LOGIN)).isNotBlank();
    }

    @Test
    void 등록되지_않은_이메일을_입력할경우_로그인_실패() {
        ExtractableResponse<Response> response = 로그인("new@email.com", FIRST_USER_PASSWORD);

        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.jsonPath().get("message").toString())
            .isEqualTo("해당하는 고객" + NOT_EXISTS_EXCEPTION);
    }

    @Test
    void 일치하지_않는_비밀번호를_입력할_경우_로그인_실패() {
        ExtractableResponse<Response> response = 로그인(FIRST_USER_EMAIL, "newPassword");

        assertThat(response.statusCode()).isEqualTo(401);
        assertThat(response.jsonPath().get("message").toString())
            .isEqualTo(NOT_MATCHED_PASSWORD_EXCEPTION);
    }

    @Test
    void 로그인_후_정보_조회_성공() {
        String token = 토큰_생성(FIRST_USER_EMAIL, FIRST_USER_PASSWORD);
        ExtractableResponse<Response> response = 로그인_정보_조회(token);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("name").toString()).isEqualTo(FIRST_USER_NAME);
    }

    @Test
    void 로그아웃_성공() {
        ExtractableResponse<Response> response = 로그아웃();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.cookie(COOKIE_NAME_FOR_LOGIN)).isBlank();
    }

    @Test
    void 로그아웃_후_정보_조회_시_실패() {
        로그인(FIRST_USER_EMAIL, FIRST_USER_PASSWORD);

        String token = 로그아웃().cookie(COOKIE_NAME_FOR_LOGIN);

        ExtractableResponse<Response> response = 로그인_정보_조회(token);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    private ExtractableResponse<Response> 로그인_정보_조회(String token) {
        return RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .cookie(COOKIE_NAME_FOR_LOGIN, token)
            .when().get("/login/check")
            .then().log().all()
            .extract();
    }
}
