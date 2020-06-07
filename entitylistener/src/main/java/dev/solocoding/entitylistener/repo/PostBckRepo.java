package dev.solocoding.entitylistener.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.solocoding.entitylistener.entities.PostBck;

public interface PostBckRepo extends JpaRepository<PostBck, Long> {
    
}