package org.codeclubbrasil.certificategenerator.api.v1.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.codeclubbrasil.certificategenerator.api.v1.dto.GenerateDTO;
import org.codeclubbrasil.certificategenerator.api.v1.dto.GenerateDTO.GenerateDTOBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CertificateGeneratorControllerTest {

    private static final int COURSE_SIZE = 7;

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }

    @Test
    public void whenPostGenerateThenGenerate() {
        GenerateDTO dto = GenerateDTOBuilder.of()
                .withCourse("python1")
                .withLeaderName("Sandro")
                .withStudents(Arrays.asList("Lucas", "Adriano", "Isabela"))
                .build();
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(dto)
                .when()
                .post("/api/v1/generate")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenGetAllCourcesThenReturnOk() {
        when()
                .get("/api/v1/courses")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(COURSE_SIZE))
                .body("[0].code", equalTo("python1"))
                .body("[1].code", equalTo("python2"))
                .body("[2].code", equalTo("scratch1"))
                .body("[3].code", equalTo("scratch2"))
                .body("[4].code", equalTo("scratch3"))
                .body("[5].code", equalTo("web1"))
                .body("[6].code", equalTo("web2"));
    }
}
