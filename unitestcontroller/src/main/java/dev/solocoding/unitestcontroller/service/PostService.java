package dev.solocoding.unitestcontroller.service;

import org.springframework.stereotype.Service;

import dev.solocoding.unitestcontroller.dto.Post;

@Service
public class PostService {
    
    public Post getAPost(Integer random){
        return new Post("postTitle "+ random, "some body");
    }
}