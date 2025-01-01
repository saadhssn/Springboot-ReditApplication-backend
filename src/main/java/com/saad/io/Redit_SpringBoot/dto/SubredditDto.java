package com.saad.io.Redit_SpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {

    private Long id;
    private String name;
    private String description;

    // Add the numberOfPosts field to hold the result of the mapping
    private Integer numberOfPosts;
}
