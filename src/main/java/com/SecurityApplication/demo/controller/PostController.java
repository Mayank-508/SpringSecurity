package com.SecurityApplication.demo.controller;

import com.SecurityApplication.demo.dto.PostDto;
import com.SecurityApplication.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {



       private final PostService postService;


    @GetMapping
    public List<PostDto> getAllPost()
    {
        return postService.getAllPost();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost)
    {
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId)
    {
        return postService.getPostById(postId);
    }



}
