package com.twitter.representation;

import java.time.LocalDateTime;

public class RetweetRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public PostRepresentation parentPost;

    public UserRepresentation user;
}
