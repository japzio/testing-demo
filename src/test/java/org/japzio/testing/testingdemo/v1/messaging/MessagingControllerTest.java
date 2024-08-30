package org.japzio.testing.testingdemo.v1.messaging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessagingControllerTest {

    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance()
            .options(
                    wireMockConfig()
                            .dynamicPort()
                            .usingFilesUnderClasspath("wiremock")
            )
            .build();
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getMessageById_Ok() {
        given()
                .contentType(ContentType.JSON)
                .header(new Header("rrn", UUID.randomUUID().toString()))
                .when()
                .get("/v1/messaging/" + UUID.randomUUID())
                .then()
                .statusCode(200);
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
