package com.saad.io.Redit_SpringBoot.repository;

import com.saad.io.Redit_SpringBoot.model.Comment;
import com.saad.io.Redit_SpringBoot.model.Post;
import com.saad.io.Redit_SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
