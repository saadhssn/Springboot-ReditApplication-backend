package com.saad.io.Redit_SpringBoot.repository;

import com.saad.io.Redit_SpringBoot.model.Post;
import com.saad.io.Redit_SpringBoot.model.User;
import com.saad.io.Redit_SpringBoot.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
