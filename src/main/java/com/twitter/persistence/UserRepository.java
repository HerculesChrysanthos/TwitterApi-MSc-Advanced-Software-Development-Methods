package com.twitter.persistence;

import com.twitter.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

}
