package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserService userService;


    public void addComment(Comment comment){
        boolean doesUserExists = userRepository.existsById(comment.getUserId());
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        boolean doesPostExists = postRepository.existsById(comment.getPostId());
        if(!doesPostExists){
            throw new ApiException("post does not exists");
        }
        boolean allowComments = postRepository.findPostByPostId(comment.getPostId()).getAllowComments();
        if(!allowComments){
            throw new ApiException("post does not allow comments");
        }
        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
        userService.userCommented(comment.getUserId());
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void updateComment(Integer comment_id, Comment comment){
        Comment c = commentRepository.findCommentByCommentId(comment_id);
        if(c==null){
            throw new ApiException("comment does not exists");
        }
        boolean doesUserExists = userRepository.existsById(comment.getUserId());
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        boolean doesPostExists = postRepository.existsById(comment.getPostId());
        if(!doesPostExists){
            throw new ApiException("post does not exists");
        }
        c.setContent(comment.getContent());
        c.setCommentDate(comment.getCommentDate());
        commentRepository.save(c);
    }


    public void removeComment(Integer comment_id){
        Comment comment = commentRepository.findCommentByCommentId(comment_id);
        if(comment==null){
            throw new ApiException("comment not found");
        }
        userService.userRemovedComment(comment.getUserId());
        commentRepository.delete(comment);
    }

    public List<Comment> getAllCommentByUserId(Integer user_id){
        return commentRepository.findCommentsByUserId(user_id);
    }

    public List<Comment> getAllCommentsByUserIdForPost(Integer post_id, Integer user_id){
        return commentRepository.findCommentsByPostIdAndUserId(post_id, user_id);
    }

    public List<Comment> getAllCommentByPost(Integer post_id){
        boolean doesPostExists = postRepository.existsById(post_id);
        if(doesPostExists){
            throw new ApiException("posts does not exists");
        }
        return commentRepository.findCommentsByPostId(post_id);
    }


    public void removeAllCommentByUser(Integer userId){
        boolean doesUserExists = userRepository.existsById(userId);
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        List<Comment> userComments = commentRepository.findCommentsByUserId(userId);
        if(userComments.isEmpty()){
            throw new ApiException("no comments by user");
        }
        for(Comment comment:userComments){
            userService.userRemovedComment(userId);
            commentRepository.delete(comment);
        }
//        commentRepository.deleteAllByUserId(userId);
    }

    public void removeAllCommentByPostAndByUser(Integer postId, Integer userId){
        boolean doesPostExists = postRepository.existsById(postId);
        if(doesPostExists){
            throw new ApiException("posts does not exists");
        }
        boolean doesUserExists = userRepository.existsById(userId);
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        List<Comment> userCommentsByPost = commentRepository.findCommentsByPostIdAndUserId(postId,userId);
        if(userCommentsByPost.isEmpty()){
            throw new ApiException("no comments by user");
        }
        for(Comment comment:userCommentsByPost){
            userService.userRemovedComment(userId);
            commentRepository.delete(comment);
        }
//        commentRepository.deleteAllByPostIdAndAndUserId(postId,userId);
    }

}
