package com.hwiyeon.actions_docker_aks;

import static com.hwiyeon.actions_docker_aks.error.CustomErrorMessage.ALREADY_EXISTS_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static com.hwiyeon.actions_docker_aks.member.initializer.MemberInitializer.FIRST_USER_EMAIL;
import static com.hwiyeon.actions_docker_aks.step.MemberStep.회원_등록;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import com.hwiyeon.actions_docker_aks.RestAssuredTestConfig;
import com.hwiyeon.actions_docker_aks.error.CustomErrorMessage;

@DisplayName("회원 관련 api 호출 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestExecutionListeners(listeners = RestAssuredTestConfig.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Disabled
public class MemberAcceptanceTest {

    private final String email = "new@test.com";
    private final String password = "newPassword";
    private final String name = "new";

    @Test
    void 회원_등록_성공() {
        ExtractableResponse<Response> response = 회원_등록(email, password, name);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().get("email").toString()).isEqualTo(email);
        assertThat(response.jsonPath().get("name").toString()).isEqualTo(name);
    }

    @Test
    void 존재하는_회원의_이메일로_회원_등록_실패() {
        ExtractableResponse<Response> response = 회원_등록(FIRST_USER_EMAIL, password, name);

        assertThat(response.statusCode()).isEqualTo(409);
        assertThat(response.jsonPath().get("message").toString())
            .isEqualTo("해당하는 고객" + ALREADY_EXISTS_EXCEPTION);
    }

    @Test
    void 회원_조회_성공() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when().get("/members")
                .then().log().all()
                .statusCode(200);
    }
}
