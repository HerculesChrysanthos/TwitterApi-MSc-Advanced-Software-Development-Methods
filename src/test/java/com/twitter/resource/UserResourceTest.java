package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.representation.UserRepresentation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.when;

@QuarkusTest
public class UserResourceTest extends IntegrationBase {

    @Test
    @TestTransaction
    public void listAllUsers() {
        List<UserRepresentation> users = when().get(Fixture.API_ROOT + TwitterUri.USERS)
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<UserRepresentation>>() {});

        Assertions.assertEquals(Fixture.Users.COUNT, users.size());
    }

    @Test
    @TestTransaction
    public void find() {
        UserRepresentation userRepresentation = when().get(Fixture.API_ROOT + TwitterUri.USERS + "/" + Fixture.Users.USER1_ID)
                .then()
                .statusCode(200)
                .extract().as(UserRepresentation.class);
        Assertions.assertEquals(Fixture.Users.USER1_ID, userRepresentation.id);
    }
}
