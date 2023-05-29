package praktikum.userDeletion;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class UserClient extends StellarburgersRestClient {

    private final String USER_AUTH_LOGIN_PATH = "/auth/login";
    private final String USER_AUTH_USER = "/auth/user";

    @Step("Войти в систему с правильными учетными данными, код статуса = 200")
    public ValidatableResponse loginWithCorrectCredentials(UserCredentials credentials) {
        return RestAssured.given()
                .spec(requestSpecification())
                .body(credentials)
                .when()
                .post(USER_AUTH_LOGIN_PATH)
                .then().log().ifValidationFails();
    }

    @Step("Удалить пользователя")
    public void delete(String token) {
        RestAssured.given()
                .spec(requestSpecification())
                .auth().oauth2(token)
                .when()
                .delete(USER_AUTH_USER)
                .then().log().ifError()
                .assertThat()
                .statusCode(SC_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }

}