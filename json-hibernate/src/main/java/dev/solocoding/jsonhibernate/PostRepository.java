package dev.solocoding.jsonhibernate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query(value = """
                SELECT * FROM post 
                WHERE
                comment->"$.key" = :comment
            """, nativeQuery = true)
    Optional<Post> getPostByCommentKey(String comment); 
}
