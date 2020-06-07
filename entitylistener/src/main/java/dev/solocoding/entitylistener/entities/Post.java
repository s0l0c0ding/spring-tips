package dev.solocoding.entitylistener.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dev.solocoding.entitylistener.repo.PostListener;
import lombok.Data;

@Entity
@EntityListeners(PostListener.class)
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    private Long version;
    
}