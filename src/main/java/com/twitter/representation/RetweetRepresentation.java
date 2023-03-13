package com.twitter.representation;

import java.time.LocalDateTime;
import java.util.Set;

public class RetweetRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public PostRepresentation parentPost;
    public Set<UserBasicRepresentation> likes;
    public UserRepresentation user;
}
