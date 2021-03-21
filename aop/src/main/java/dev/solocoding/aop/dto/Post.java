package dev.solocoding.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
 
    private Long id;
    private String title;
    private String body;
}
