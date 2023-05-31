package gmail.astersoter.reqresin.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.fromContentType;
import static org.hamcrest.Matchers.*;

public class ReqresInApiTests {

    @Test
    void singleUserEmailTest() {

        get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .assertThat().contentType(ContentType.JSON);
    }

    @Test
    void userInArrayTest() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[0].id", is(7))
                .assertThat().body("data[0].first_name", is("Michael"),
                        "data[0].last_name", is("Lawson"))
                .assertThat().contentType(ContentType.JSON);
    }

    @Test
    void successfullRegisterTest() {
        String registerUser = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .log().method()
                .log().body()
                .body(registerUser)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(4),
                        "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void putTest() {
        String putUser = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .log().body()
                .log().method()
                .log().uri()
                .body(putUser)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("updatedAt", is(notNullValue()));

    }

    @Test
    void deleteTest() {
        delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}


