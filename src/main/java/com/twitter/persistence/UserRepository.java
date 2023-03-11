package com.twitter.persistence;

import com.twitter.domain.EmailAddress;
import com.twitter.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;

@RequestScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public User findByUsername(String username) {
        PanacheQuery<User> user = find("select u from User u where u.username = :username", Parameters.with("username", username).map());
        try {
            return user.singleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    public User findByEmail(String email) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setEmail( email );

        PanacheQuery<User> user = find("select u from User u where u.email = :emailAddress", Parameters.with("emailAddress", emailAddress).map());
        try {
            return user.singleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }
}
