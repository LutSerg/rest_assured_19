package gmail.astersoter.reqresin.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static gmail.astersoter.reqresin.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class DeleteSpec {

    public static RequestSpecification deleteRqSpec = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates());

    public static ResponseSpecification deleteRsSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();
}
