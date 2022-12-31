package dev.solocoding.jsonhibernate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/posts")
public class PostController {
    
    private final PostRepository postRepo;

    
    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/{id}")
    public Postdto getPost(@PathVariable Long id) {
        var found = postRepo.findById(id).orElseThrow();
        return new Postdto(found.getBody(), found.getComment());
    }

    @PostMapping
    public Postdto createPost(@RequestBody Postdto postToCreate) {
        var post = new Post();
        post.setBody(postToCreate.post());
        post.setComment(postToCreate.comment());
        postRepo.save(post);
        return new Postdto(post.getBody(), post.getComment());
    }

    @GetMapping("/comments/{body}")
    public Postdto getPostByComment(@PathVariable String body) {
        var found = postRepo.getPostByCommentKey(body).orElseThrow();
        return new Postdto(found.getBody(), found.getComment());
    }
}
