package com.saad.io.Redit_SpringBoot.repository;

import com.saad.io.Redit_SpringBoot.model.Post;
import com.saad.io.Redit_SpringBoot.model.Subreddit;
import com.saad.io.Redit_SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}