package dev.solocoding.entitylistener.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PostBck {
    
    @Id
    @GeneratedValue
    private Long id;
    private Long idPost;
    private String title;
    private String body;
    private Long version;

    public PostBck(Post input){
        this.idPost = input.getId();
        this.title = input.getTitle();
        this.body= input.getBody();
        this.version = input.getVersion();
    }
}