package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.representation.UserRepresentation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

@QuarkusTest
public class UserResourceTest extends IntegrationBase {

    @Test
    @TestTransaction
    public void find() {
        UserRepresentation userRepresentation = when().get(Fixture.API_ROOT + TwitterUri.USERS + "/" + Fixture.Users.NIKOLAS_ID)
                .then()
                .statusCode(200)
                .extract().as(UserRepresentation.class);
        Assertions.assertEquals(Fixture.Users.NIKOLAS_ID, userRepresentation.id);
    }
}
