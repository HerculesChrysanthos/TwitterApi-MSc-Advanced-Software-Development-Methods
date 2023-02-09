package com.twitter.domain;

import javax.persistence.*;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "tweetId")
    private Tweet tweet;

    public Reply() { }
}