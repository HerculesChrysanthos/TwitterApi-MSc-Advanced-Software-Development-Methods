package com.twitter.persistence;

import com.twitter.domain.Post;
import com.twitter.domain.Tweet;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class PostRepository implements PanacheRepositoryBase<Post, Integer> {

        public List<Tweet> postsForUserTimeline(Integer userId, Tweet offset, int limit) {
            String query = "SELECT t FROM Tweet t WHERE t.user IN (SELECT f FROM User u JOIN u.following f WHERE u.id = :userId)";
            if(offset != null ) {
                query += " AND t.dateTime < :offsetDateTime";
                query += " ORDER BY t.dateTime DESC";
                return find(query, Parameters.with("userId", userId).and( "offsetDateTime", offset.getDateTime()).map()).page(0, limit).list();
            }

            query += " ORDER BY t.dateTime DESC";

            return find(query, Parameters.with("userId", userId).map()).page(0, limit).list();
    }
}
