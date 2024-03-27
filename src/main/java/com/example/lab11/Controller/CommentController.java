package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.ok(new ApiResponse("comment added"));
    }

    @GetMapping("/comments")
    public ResponseEntity getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @PutMapping("/update/{comment_id}")
    public ResponseEntity updateComment(@PathVariable Integer comment_id,@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(comment_id, comment);
        return ResponseEntity.ok(new ApiResponse("comment updated"));
    }

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity removeComment(@PathVariable Integer comment_id){
        commentService.removeComment(comment_id);
        return ResponseEntity.ok(new ApiResponse("comment deleted"));
    }

    //1
    @GetMapping("/comment-by/{user_id}")
    public ResponseEntity getAllCommentByUser(@PathVariable Integer user_id){
        return ResponseEntity.ok(commentService.getAllCommentByUserId(user_id));
    }
    //2
    @GetMapping("/comment-by-user/{post_id}/{user_id}")
    public ResponseEntity getAllCommentByUserForPost(@PathVariable Integer post_id, @PathVariable Integer user_id){
        return ResponseEntity.ok(commentService.getAllCommentsByUserIdForPost(post_id,user_id));
    }
    //3
    @GetMapping("/post-comments/{post_id}")
    public ResponseEntity getAllCommentsByPost(@PathVariable Integer post_id){
        return ResponseEntity.ok(commentService.getAllCommentByPost(post_id));
    }
    //4
    @DeleteMapping("/remove-comments-post/{postId}/{userId}")
    public ResponseEntity removeAllCommentsByPostByUser(@PathVariable Integer postId, Integer userId){
        commentService.removeAllCommentByPostAndByUser(postId,userId);
        return ResponseEntity.ok(new ApiResponse("removed comments by user in post: "+postId));
    }
    //4
    @DeleteMapping("/remove-comments-user/{userId}")
    public ResponseEntity removeAllCommentByUser(@PathVariable Integer userId){
        commentService.removeAllCommentByUser(userId);
        return ResponseEntity.ok(new ApiResponse("removed comments by user: "+userId));
    }


}
