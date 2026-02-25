package com.SecurityApplication.demo.dto;


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
    }

