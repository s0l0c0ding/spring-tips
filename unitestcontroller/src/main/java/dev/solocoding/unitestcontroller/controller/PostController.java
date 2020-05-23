package dev.solocoding.unitestcontroller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.solocoding.unitestcontroller.dto.Post;
import dev.solocoding.unitestcontroller.service.PostService;

@RestController
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
    
   
    @GetMapping("/post/{random}")
    public Post getPost(@PathVariable Integer random) {
        return service.getAPost(random);
    }


}