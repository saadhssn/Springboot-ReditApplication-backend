package com.saad.io.Redit_SpringBoot.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.saad.io.Redit_SpringBoot.dto.PostRequest;
import com.saad.io.Redit_SpringBoot.dto.PostResponse;
import com.saad.io.Redit_SpringBoot.model.*;
import com.saad.io.Redit_SpringBoot.repository.CommentRepository;
import com.saad.io.Redit_SpringBoot.repository.VoteRepository;
import com.saad.io.Redit_SpringBoot.serivce.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper { // Ensure the class is abstract

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AuthService authService;

    // Mapping from PostRequest to Post
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    // Mapping from Post to PostResponse
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    // Helper method to get the comment count for a post
    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    // Helper method to get the duration for a post
    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    // Helper method to check if the post is upvoted by the logged-in user
    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, VoteType.UPVOTE);
    }

    // Helper method to check if the post is downvoted by the logged-in user
    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, VoteType.DOWNVOTE);
    }

    // Helper method to check the vote type
    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}
