package com.twitter.resource;

import com.twitter.Fixture;
import com.twitter.IntegrationBase;
import com.twitter.domain.Post;
import com.twitter.domain.User;
import com.twitter.representation.*;
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
        Assertions.assertEquals(1000, tweetRepresentation.user.id);
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

    @Test
    @TestTransaction
    public void testCreateReply() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "test content";

        ReplyRepresentation reply = new ReplyRepresentation();
        reply.content = tweetContent;

        ReplyRepresentation replyRepresentation = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reply)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/reply")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(ReplyRepresentation.class);


        Assertions.assertNotNull(replyRepresentation.id);
        Assertions.assertEquals("test content", replyRepresentation.content.tweetContent);
        Assertions.assertEquals(1000, replyRepresentation.user.id);
        Assertions.assertEquals(Fixture.Posts.POST1_ID, replyRepresentation.parentPost.id);
    }

    @Test
    @TestTransaction
    public void testCreateReplyWithInvalidUser() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "test content";

        ReplyRepresentation reply = new ReplyRepresentation();
        reply.content = tweetContent;

        given()
                .header("userId", Fixture.Users.USER4_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reply)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/reply")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testCreateReplyWithInvalidParentPost() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "test content";

        ReplyRepresentation reply = new ReplyRepresentation();
        reply.content = tweetContent;

        given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reply)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST4_ID + "/reply")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testCreateReplyWithMaxSupportedChars() {
        TweetBodyRepresentation tweetContent = new TweetBodyRepresentation();
        tweetContent.tweetContent = "Lorem ipsum dolor sit amet, consectetuer adipiscing"; // 51 chars

        ReplyRepresentation reply = new ReplyRepresentation();
        reply.content = tweetContent;

        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reply)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/reply")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Max supported chars: 50", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testCreateRetweet() {
        RetweetRepresentation retweet = new RetweetRepresentation();

        RetweetRepresentation retweetRepresentation = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(retweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/retweet")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(RetweetRepresentation.class);


        Assertions.assertNotNull(retweetRepresentation.id);
        Assertions.assertEquals(1000, retweetRepresentation.user.id);
        Assertions.assertEquals(Fixture.Posts.POST1_ID, retweetRepresentation.originalPost.id);
    }

    @Test
    @TestTransaction
    public void testCreateRetweetWithInvalidUser() {
        RetweetRepresentation retweet = new RetweetRepresentation();

        given()
                .header("userId", Fixture.Users.USER4_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(retweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/retweet")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testCreateRetweetWithInvalidParentPost() {
        RetweetRepresentation retweet = new RetweetRepresentation();

        given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(retweet)
                .post(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST4_ID + "/retweet")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }


    @Test
    @TestTransaction
    public void testGetPostLikesByIdTweetCase() {
        LikeRepresentation likeRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(LikeRepresentation.class);

        Assertions.assertEquals(1, likeRepresentation.likes.size());
    }

    @Test
    @TestTransaction
    public void testGetPostLikesByIdReplyCase() {
        LikeRepresentation likeRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST2_ID + "/likes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(LikeRepresentation.class);

        Assertions.assertEquals(1, likeRepresentation.likes.size());
    }

    @Test
    @TestTransaction
    public void testGetPostLikesByIdRetweetCase() {
        LikeRepresentation likeRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST3_ID + "/likes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(LikeRepresentation.class);

        Assertions.assertEquals(1, likeRepresentation.likes.size());
    }

    @Test
    @TestTransaction
    public void testGetPostLikesByIdPostNotFound() {
        when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST4_ID  + "/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testLikePost() {
        given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .put(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        LikeRepresentation likeRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(LikeRepresentation.class);

        Assertions.assertEquals(2, likeRepresentation.likes.size());
    }

    @Test
    @TestTransaction
    public void testLikeNonExistingPost() {
        given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .put(Fixture.API_ROOT + TwitterUri.POSTS+ "/" + Fixture.Posts.POST4_ID +"/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testLikeWithNonExistingUser() {
        given()
                .header("userId", Fixture.Users.USER4_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .put(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testUnlike() {
        given()
                .header("userId", Fixture.Users.USER2_ID)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        LikeRepresentation likeRepresentation = when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(LikeRepresentation.class);

        Assertions.assertEquals(0, likeRepresentation.likes.size());
    }

    @Test
    @TestTransaction
    public void testUnlikeWithNonExistingUser() {
        given()
                .header("userId", Fixture.Users.USER4_ID)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testUnlikeWithNonExistingPost() {
        given()
                .header("userId", Fixture.Users.USER2_ID)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + Fixture.Posts.POST4_ID + "/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testUnlikePostNotLiked() {
        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID + "/likes")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User 'user1' does not like given post.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testDeletePostWithNullUserId() {
        ErrorResponse errorResponse = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID)
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Provide userId.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testDeletePostWithNullUser() {
        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER4_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User not found.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testDeletePostWithNullPost() {
        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST4_ID)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Post not found.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testDeletePostUserNonPostAuthor() {
        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID)
                .then()
                .statusCode(Response.Status.FORBIDDEN.getStatusCode())
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testDeletePostSuccess() {
        given()
                .header("userId", Fixture.Users.USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .delete(Fixture.API_ROOT + TwitterUri.POSTS + "/" + Fixture.Posts.POST1_ID)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @TestTransaction
    public void testTimeLineSuccess() {
        List<TweetRepresentation> timeline = given()
                .header("userId", Fixture.Users.USER3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/timeline")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(new TypeRef<List<TweetRepresentation>>() {});


        Assertions.assertEquals(2, timeline.size());
    }

    @Test
    @TestTransaction
    public void testTimeLineNullUserId() {
        ErrorResponse errorResponse = given()
                .when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/timeline")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode())
                .extract().as(ErrorResponse.class);


        Assertions.assertEquals("Provide userId.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testTimeLineNullUser() {
        ErrorResponse errorResponse = given()
                .header("userId", Fixture.Users.USER4_ID)
                .when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/timeline")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().as(ErrorResponse.class);


        Assertions.assertEquals("User not found.", errorResponse.getMessage());
    }

    @Test
    @TestTransaction
    public void testTimeLineSuccessWithOffset() {
        List<TweetRepresentation> timeline = given()
                .header("userId", Fixture.Users.USER3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get(Fixture.API_ROOT + TwitterUri.POSTS + "/timeline?offset=5000")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(new TypeRef<List<TweetRepresentation>>() {});


        Assertions.assertEquals(1, timeline.size());
    }

}