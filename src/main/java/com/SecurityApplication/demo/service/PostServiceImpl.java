package com.SecurityApplication.demo.service;

import com.SecurityApplication.demo.dto.PostDto;
import com.SecurityApplication.demo.entity.PostEntity;
import com.SecurityApplication.demo.exception.ResourceNotFoundException;
import com.SecurityApplication.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{



    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPost()
      {
         List<PostEntity> allPosts= postRepository.findAll();
       var posts= allPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
       return posts;

      }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        return null;
    }


    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id "+postId));
     return modelMapper.map(postEntity, PostDto.class);
    }


//    public PostDto updatePost(PostDto inputPost, Long postId) {
//        PostEntity olderPost=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post does not exist with id "+postId));
//
//        inputPost.setId(postId);
//        modelMapper.map(inputPost, olderPost);
//
//        PostEntity postEntity=postRepository.save(olderPost);
//        return modelMapper.map(postEntity, PostDto.class);
//
//    }


}
