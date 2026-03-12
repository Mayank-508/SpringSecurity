package com.SecurityApplication.demo.dto;


import com.SecurityApplication.demo.entity.User;
import lombok.*;



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class PostDto {

        private Long id;
        private String title;
        private String description;
        private UserDto author;
    }

