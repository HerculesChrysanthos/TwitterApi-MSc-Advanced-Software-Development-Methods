package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.representation.ReplyRepresentation;
import com.twitter.representation.RetweetRepresentation;
import com.twitter.representation.TweetRepresentation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@QuarkusTest
public class PostResourceTest  extends IntegrationBase {

    @Test
    @TestTransaction
    public void testGetPostByIdTweetCase() {
        TweetRepresentation tweetRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(TweetRepresentation.class);

        Assertions.assertEquals(Fixture.Posts.POST1_ID, tweetRepresentation.id);
    }

    @Test
    @TestTransaction
    public void testGetPostByIdReplyCase() {
        ReplyRepresentation replyRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST2_ID)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(ReplyRepresentation.class);

        Assertions.assertEquals(Fixture.Posts.POST2_ID, replyRepresentation.id);
    }

    @Test
    @TestTransaction
    public void testGetPostByIdRetweetCase() {
        RetweetRepresentation replyRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST3_ID)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(RetweetRepresentation.class);

        Assertions.assertEquals(Fixture.Posts.POST3_ID, replyRepresentation.id);
    }

    @Test
    @TestTransaction
    public void testGetPostByIdPostNotFound() {
        when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST4_ID)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());

    }
}