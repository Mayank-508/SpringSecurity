package com.SecurityApplication.demo.service;

import com.SecurityApplication.demo.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPost();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

}
