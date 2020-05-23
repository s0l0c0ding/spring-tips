package dev.solocoding.unitestcontroller.dto;

import java.util.UUID;

public class Post {

    private String id;
    private String postTitle;
    private String body;

    public Post (String postTitle, String body) {
        this.id = UUID.randomUUID().toString();
        this.postTitle = postTitle;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
}