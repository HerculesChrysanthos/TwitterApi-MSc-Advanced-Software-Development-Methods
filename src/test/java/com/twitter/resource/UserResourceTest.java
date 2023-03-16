package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.representation.DateOfBirthRepresentation;
import com.twitter.representation.ErrorResponse;
import com.twitter.representation.UserRepresentation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static io.restassured.RestAssured.given;
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

    @Test
    @TestTransaction
    public void findNonExistingUser() {
        ErrorResponse errorResponse = when().get(Fixture.API_ROOT + TwitterUri.USERS + "/" + Fixture.Users.USER4_ID)
                .then()
                .statusCode(404)
                .extract().as(ErrorResponse.class);
        Assertions.assertNotNull(errorResponse);
    }

    @Test
    @TestTransaction
    public void createUser() {
        UserRepresentation user = new UserRepresentation();
        user.username = "newUser";
        user.password = "password";
        user.email = "newuser@example.com";
        user.dateOfBirth = new DateOfBirthRepresentation();
        user.dateOfBirth.day = 1;
        user.dateOfBirth.month = 1;
        user.dateOfBirth.year = 2000;

        UserRepresentation userRepresentation = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .when()
                .post(Fixture.API_ROOT + TwitterUri.USERS)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(UserRepresentation.class);

        Assertions.assertNotNull(userRepresentation.id);
        Assertions.assertEquals(user.username, userRepresentation.username);
        Assertions.assertEquals(user.email, userRepresentation.email);
        Assertions.assertEquals(user.dateOfBirth.day, userRepresentation.dateOfBirth.day);
        Assertions.assertEquals(user.dateOfBirth.month, userRepresentation.dateOfBirth.month);
        Assertions.assertEquals(user.dateOfBirth.year, userRepresentation.dateOfBirth.year);
    }

    @Test
    @TestTransaction
    public void canNotCreateUserWithExistingUsername() {
        UserRepresentation user = new UserRepresentation();
        user.username = "user1";
        user.password = "password";
        user.email = "newuser@example.com";
        user.dateOfBirth = new DateOfBirthRepresentation();
        user.dateOfBirth.day = 1;
        user.dateOfBirth.month = 1;
        user.dateOfBirth.year = 2000;

        ErrorResponse errorResponse = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .when()
                .post(Fixture.API_ROOT + TwitterUri.USERS)
                .then()
                .statusCode(Response.Status.CONFLICT.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User with username 'user1' already exists.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void canNotCreateUserWithExistingEmail() {
        UserRepresentation user = new UserRepresentation();
        user.username = "newUser";
        user.password = "password";
        user.email = "user1@email.com";
        user.dateOfBirth = new DateOfBirthRepresentation();
        user.dateOfBirth.day = 1;
        user.dateOfBirth.month = 1;
        user.dateOfBirth.year = 2000;

        ErrorResponse errorResponse = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .when()
                .post(Fixture.API_ROOT + TwitterUri.USERS)
                .then()
                .statusCode(Response.Status.CONFLICT.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User with email 'user1@email.com' already exists.", errorResponse.getMessage());
    }


}
