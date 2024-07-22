package com.hwiyeon.actions_docker_aks.step;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import com.hwiyeon.actions_docker_aks.member.dto.MemberRequest;

public class MemberStep {
    public static ExtractableResponse<Response> 회원_등록(String email, String password, String name) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new MemberRequest(email, password, name))
                .when().post("/members")
                .then().log().all()
                .extract();
    }
}
