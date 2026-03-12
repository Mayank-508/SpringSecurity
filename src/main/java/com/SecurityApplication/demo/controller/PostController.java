package com.SecurityApplication.demo.controller;

import com.SecurityApplication.demo.dto.PostDto;
import com.SecurityApplication.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {



       private final PostService postService;


    @Secured("POST_VIEW")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<PostDto> getAllPost()
    {
        return postService.getAllPost();
    }

    @Secured("POST_CREATE")
    @PreAuthorize("hasAnyAuthority('POST_CREATE','POST_VIEW') OR hasAnyRole('USER')")
    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost)
    {
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDto getPostById(@PathVariable Long postId)
    {

        return postService.getPostById(postId);
    }



}
