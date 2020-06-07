package dev.solocoding.entitylistener.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.solocoding.entitylistener.entities.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
    
}