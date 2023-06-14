package gmail.astersoter.reqresin.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static gmail.astersoter.reqresin.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpecs {

    public static RequestSpecification createUserRqSpec = with()
            .log().method()
            .log().body()
            .log().uri()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static ResponseSpecification createUserRsSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();
}
