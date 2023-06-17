package gmail.astersoter.reqresin.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;

public class RsSpecs {

    public static ResponseSpecification createUserRsSpec201 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification RsSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification deleteRsSpec204 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();
}
