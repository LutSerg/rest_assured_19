package gmail.astersoter.reqresin.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static gmail.astersoter.reqresin.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class UsersSpecs {
    public static RequestSpecification usersRqSpec = with()
            .log().all()
            .filter(withCustomTemplates());

    public static ResponseSpecification usersRsSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}
