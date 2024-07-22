package com.hwiyeon.actions_docker_aks;

import static com.hwiyeon.actions_docker_aks.login.LoginMember.COOKIE_NAME_FOR_LOGIN;
import static com.hwiyeon.actions_docker_aks.step.LoginStep.관리자_토큰_생성;
import static com.hwiyeon.actions_docker_aks.step.LoginStep.회원_토큰_생성;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import com.hwiyeon.actions_docker_aks.RestAssuredTestConfig;

@DisplayName("페이지 접속 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestExecutionListeners(listeners = RestAssuredTestConfig.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Disabled
public class PageTest {

    @Test
    void 메인_페이지_접속_성공() {
        RestAssured.given().log().all()
            .when().get("/")
            .then().log().all()
            .statusCode(200);
    }

    @Test
    void 어드민_페이지_접속_성공() {
        RestAssured.given().log().all()
            .cookie(COOKIE_NAME_FOR_LOGIN, 관리자_토큰_생성())
            .when().get("/admin/reservation")
            .then().log().all()
            .statusCode(200);
    }

    @Test
    void 관리자_외_어드민_페이지_접속_실패() {
        RestAssured.given().log().all()
            .cookie(COOKIE_NAME_FOR_LOGIN, 회원_토큰_생성())
            .when().get("/admin/reservation")
            .then().log().all()
            .statusCode(401);
    }
}
