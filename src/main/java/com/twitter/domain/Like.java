package com.twitter.domain;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "tweetId")
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "userId")
    private User user;

}
