package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.ok(new ApiResponse("post added"));
    }

    @GetMapping("/posts")
    public ResponseEntity getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/update/{post_id}")
    public ResponseEntity updatePost(@PathVariable Integer post_id, @RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(post_id, post);
        return ResponseEntity.ok(new ApiResponse("post updated"));
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity removePost(@PathVariable Integer post_id){
        postService.removePost(post_id);
        return ResponseEntity.ok(new ApiResponse("post removed"));
    }
    //6
    @PutMapping("/allow-comments/{post_id}")
    public ResponseEntity allowComment(@PathVariable Integer post_id){
        postService.allowComment(post_id);
        return ResponseEntity.ok(new ApiResponse("allow comments change"));
    }
    //7
    @GetMapping("/posts-by-user/{user_id}")
    public ResponseEntity getAllPostsByUser(@PathVariable Integer user_id){
        return ResponseEntity.ok(postService.getAllPostsByUserId(user_id));
    }
    //8
    @GetMapping("/post-by-title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
        return ResponseEntity.ok(postService.getPostByTitle(title));
    }
    //9
    @GetMapping("/post-before/{date}")
    public ResponseEntity getAllPostsBeforeDate(@PathVariable LocalDate date){
        return ResponseEntity.ok(postService.getPostBeforeDate(date));
    }
}
