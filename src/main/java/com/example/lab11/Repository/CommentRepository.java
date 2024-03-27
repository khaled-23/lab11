package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentByCommentId(Integer comment_id);

    List<Comment> findCommentsByUserId(Integer user_id);

    List<Comment> findCommentsByPostIdAndUserId(Integer post_id,Integer user_id);

    List<Comment> findCommentsByPostId(Integer post_id);

    void deleteCommentsByUserId(Integer userId);

    @Query("delete from Comment c where c.postId=?1 and c.userId=?2")
    void deleteAllByPostIdAndAndUserId(Integer postId, Integer userId);
}
