package com.twitter.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Embedded
    private EmailAddress email;

    @Embedded
    private DateOfBirth dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Post> posts = new HashSet<Post>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_posts",
            joinColumns = { @JoinColumn(name = "userId")},
            inverseJoinColumns = { @JoinColumn(name = "postId")}
            )
    private Set<Post> likedPosts = new HashSet<Post>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "following_users",
            joinColumns = { @JoinColumn(name = "following")},
            inverseJoinColumns = { @JoinColumn(name = "userId")}
    )
    private Set<User> following = new HashSet<User>();

    public User() { }

    public User(String username, String password, DateOfBirth dateOfBirth, EmailAddress email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }
//
//    public Set<Post> getLikedPosts() {
//        return likedPosts;
//    }
//
//    public void setLikedPosts(Set<Post> likedPosts) {
//        this.likedPosts = likedPosts;
//    }
//


    public Set<User> getFollowing() {
        return following;
    }
//
    public void setFollowing(User followingUser) {
        this.following.add(followingUser);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
