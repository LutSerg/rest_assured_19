package gmail.astersoter.reqresin.tests;

import gmail.astersoter.reqresin.models.lombok.createuser.CreateUserModelRq;
import gmail.astersoter.reqresin.models.lombok.createuser.CreateUserModelRs;
import gmail.astersoter.reqresin.models.lombok.register.RegisterModelRq;
import gmail.astersoter.reqresin.models.lombok.register.RegisterModelRs;
import gmail.astersoter.reqresin.models.lombok.update.UpdateModelRq;
import gmail.astersoter.reqresin.models.lombok.update.UpdateModelRs;
import gmail.astersoter.reqresin.models.lombok.user.UserResponse;
import gmail.astersoter.reqresin.models.lombok.users.GetUsersModelRs;
import org.junit.jupiter.api.Test;

import static gmail.astersoter.reqresin.specs.CreateUserSpecs.createUserRqSpec;
import static gmail.astersoter.reqresin.specs.CreateUserSpecs.createUserRsSpec;
import static gmail.astersoter.reqresin.specs.DeleteSpec.deleteRqSpec;
import static gmail.astersoter.reqresin.specs.DeleteSpec.deleteRsSpec;
import static gmail.astersoter.reqresin.specs.RegistrationSpecs.registrationRqSpec;
import static gmail.astersoter.reqresin.specs.RegistrationSpecs.registrationRsSpec;
import static gmail.astersoter.reqresin.specs.UpdateUserSpecs.updateUserRqSpec;
import static gmail.astersoter.reqresin.specs.UpdateUserSpecs.updateUserRsSpec;
import static gmail.astersoter.reqresin.specs.UserSpecs.userRqSpec;
import static gmail.astersoter.reqresin.specs.UserSpecs.userRsSpec;
import static gmail.astersoter.reqresin.specs.UsersSpecs.usersRqSpec;
import static gmail.astersoter.reqresin.specs.UsersSpecs.usersRsSpec;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInApiTests extends TestBase{

    @Test
    void singleUserEmailTest() {
        UserResponse userResponse = given(userRqSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(userRsSpec)
                .extract().as(UserResponse.class);

        assertEquals(2, userResponse.getData().getId());
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",
                userResponse.getSupport().getText());
        assertThat(userResponse.getData().getEmail(), notNullValue());

    }

    @Test
    void userInArrayTest() {

        GetUsersModelRs getUsers = given(usersRqSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(usersRsSpec)
                .extract().as(GetUsersModelRs.class);

        assertEquals("Lindsay", getUsers.getData().get(1).getFirstName());
    }

    @Test
    void successfulRegisterTest() {

        RegisterModelRq rqBody = new RegisterModelRq();
        rqBody.setEmail("eve.holt@reqres.in");
        rqBody.setPassword("pistol");
        String token = "QpwL5tke4Pnpja7X4";
        int id = 4;

        RegisterModelRs registerResponse = given(registrationRqSpec)
                .body(rqBody)
                .when()
                .post("/register")
                .then()
                .spec(registrationRsSpec)
                .extract().as(RegisterModelRs.class);

        assertEquals(token, registerResponse.getToken());
        assertEquals(id, registerResponse.getId());
    }

    @Test
    void updateUserTest() {

        UpdateModelRq updateRq = new UpdateModelRq();
        updateRq.setName("morpheus");
        updateRq.setJob("zion resident");

        UpdateModelRs updateModelRs = given(updateUserRqSpec)
                .body(updateRq)
                .when()
                .put("/users/2")
                .then()
                .spec(updateUserRsSpec)
                .extract().as(UpdateModelRs.class);

        assertThat(updateModelRs.getUpdatedAt(), notNullValue());
    }

    @Test
    void successfulCreateUserTest () {

        CreateUserModelRq createUserRq = new CreateUserModelRq();
        createUserRq.setName("morpheus");
        createUserRq.setJob("leader");

        CreateUserModelRs createUserRs = given(createUserRqSpec)
                .body(createUserRq)
                .when()
                .post("users")
                .then()
                .spec(createUserRsSpec)
                .extract().as(CreateUserModelRs.class);

        assertEquals("morpheus", createUserRs.getName());
        assertThat(createUserRs.getCreatedAt(), notNullValue());
    }



    @Test
    void deleteTest() {
        given(deleteRqSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteRsSpec);
    }
}


