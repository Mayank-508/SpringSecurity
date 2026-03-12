package com.SecurityApplication.demo.utils;


import com.SecurityApplication.demo.dto.PostDto;
import com.SecurityApplication.demo.entity.PostEntity;
import com.SecurityApplication.demo.entity.User;
import com.SecurityApplication.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService
            postService;



    public boolean isOwnerOfPost(Long postId)
    {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDto post= postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());

    }


}
