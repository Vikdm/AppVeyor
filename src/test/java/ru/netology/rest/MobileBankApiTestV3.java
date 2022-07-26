package ru.netology.rest;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV3 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200)
                // специализированные проверки - лучше
                .contentType(ContentType.JSON)
                .body("", hasSize(3))
                .body("[0].currency", equalTo("RUB"))
                .body("[0].balance", greaterThanOrEqualTo(0))
        ;
    }
//    @Test
//    void shouldReturnDemoAccounts1() {
//        // Given - When - Then
//        // Предусловия
//        given()
//                .baseUri("http://localhost:9999/api/v1")
//
//                .when()
//                .get("/demo/accounts")
//                .then()
//                .statusCode(200)
//
//                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
//                .body("[2].id", equalTo("12"))
//        ;
//    }

    @Test
    void validCurrency() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .contentType(ContentType.JSON)

                .body("[0].currency", equalTo("RUB"))
                .body("[1].currency", equalTo("USD"))
        ;

    }

//    @Test
//    void validCurrency1() {
//        given()
//                .baseUri("http://localhost:9999/api/v1")
//                .when()
//                .get("/demo/accounts")
//                .then()
//                .contentType(ContentType.JSON)
//
//                .body("[0].currency", equalTo("RUR"))
//        ;
//    }
}