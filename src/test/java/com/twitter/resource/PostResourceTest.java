package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.representation.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
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

    @Test
    @TestTransaction
    public void testCreateTweet() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "test content";

        TweetRepresentation tweet = new TweetRepresentation();
        tweet.content = tweetContent;

        TweetRepresentation tweetRepresentation = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(TweetRepresentation.class);


        Assertions.assertNotNull(tweetRepresentation.id);
        Assertions.assertEquals("test content", tweetRepresentation.content.tweetContent);
    }

    @Test
    @TestTransaction
    public void testCreateTweetWithInvalidUser() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "test content";

        TweetRepresentation tweet = new TweetRepresentation();
        tweet.content = tweetContent;

        given()
                .header("userId", Fixture.Users.USER4_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testCreateTweetWithMaxSupportedChars() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "Lorem ipsum dolor sit amet, consectetuer adipiscing"; // 51 chars

        TweetRepresentation tweet = new TweetRepresentation();
        tweet.content = tweetContent;

        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS)
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Max supported chars: 50", errorResponse.getMessage());
    }
}