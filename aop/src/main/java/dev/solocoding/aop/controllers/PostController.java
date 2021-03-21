package dev.solocoding.aop.controllers;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.solocoding.aop.dto.Post;

@RestController
@RequestMapping("/")
public class PostController {
    
    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") Long id) {
        return new Post(id, "postTitle", "postBody");
    }

    @PostMapping
    public Post creatPost(@RequestBody Post post) {
        var id = new Random().nextLong();
        if(id%2 == 0) throw new RuntimeException("Wanted Exception");
        return new Post(id, post.getTitle(), post.getBody());
    }
}
