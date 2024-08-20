package org.japzio.testing.testingdemo.v1.messaging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessagingControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getMessageById_invalidRrn_shouldThrowBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .header(new Header("rrn", "invalid"))
                .when()
                .get("/v1/messaging/" + UUID.randomUUID())
                .then()
                .statusCode(400);
    }

    @Test
    void getMessageById_missingRrn_shouldThrowBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/messaging/" + UUID.randomUUID())
                .then()
                .statusCode(400);
    }

}
