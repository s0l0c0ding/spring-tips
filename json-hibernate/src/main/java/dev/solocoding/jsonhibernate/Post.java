package dev.solocoding.jsonhibernate;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private JsonNode comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public JsonNode getComment() {
        return comment;
    }

    public void setComment(JsonNode comment) {
        this.comment = comment;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
