package com.twitter.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @org.hibernate.annotations.Type(
            type="com.twitter.persistence.EmailCustomType"
    )
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private EmailAddress email;

    @Embedded
    private DateOfBirth dateOfBirth;

    // if you delete a following user, you don't delete and the user
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<User> getFollowing() {
        return following;
    }

    private void setFollowing(User followingUser) {
        this.following.add(followingUser);
    }

    public boolean followUser(User user) {
        if (this.following.contains(user) || this.equals(user)) {
            return false;
        }
        this.setFollowing(user);
        return true;
    }

    private void removeFollowing(User followingUser) { this.following.remove(followingUser); }

    public boolean unfollowUser(User user) {
        if (!this.following.contains(user) || this.equals(user)) {
            return false;
        }
        this.removeFollowing(user);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}

