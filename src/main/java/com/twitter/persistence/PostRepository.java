package com.twitter.persistence;

import com.twitter.domain.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.RequestScoped;
import javax.persistence.DiscriminatorColumn;

@RequestScoped
public class PostRepository implements PanacheRepositoryBase<Post, Integer> {
}
