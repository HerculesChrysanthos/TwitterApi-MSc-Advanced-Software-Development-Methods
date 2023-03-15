package com.twitter.representation;

import java.time.LocalDateTime;
import java.util.Set;

public class RetweetRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public PostRepresentation originalPost;
    public Set<UserBasicRepresentation> likes;
    public UserRepresentation user;
    public Set<PostRepresentation> replies;
    public Set<PostRepresentation> retweets;
}
