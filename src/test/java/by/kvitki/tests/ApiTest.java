package by.kvitki.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    private final String BASE_URL = "https://store.kvitki.by";

    @Test
    public void testInvalidLoginApi() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("centreId", 1873);
        requestBody.put("email", "invalid-user@test.com");
        requestBody.put("password", "invalid-password");

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(requestBody)

                .when()
                .post("/web-api/customer/public/auth/login")

                .then()
                .log().all()
                .statusCode(401);
    }
    @Test
    public void testSuccessfulLoginApi() {
        String validEmail = "test404company@gmail.com";
        String validPassword = "test404Company";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("centreId", 1873);
        requestBody.put("email", validEmail);
        requestBody.put("password", validPassword);

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/web-api/customer/public/auth/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("accessToken", notNullValue());
    }
    @Test
    public void testRegistrationWithExistingEmailApi() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstName", "test484company");
        requestBody.put("lastName", "test484company");
        requestBody.put("email", "test484company@gmail.com");
        requestBody.put("centreId", 1873);
        requestBody.put("newPassword", "test484company");
        requestBody.put("newsMail", false);
        requestBody.put("phoneNo", "");


        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/web-api/customer")
                .then()
                .log().all()
                .statusCode(400);
    }
}