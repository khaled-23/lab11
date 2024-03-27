package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostByPostId(Integer post_id);

    List<Post> findPostsByUserId(Integer user_id);

    Post getPostByTitle(String title);

    List<Post> findPostsByPublishDateBefore(LocalDate date);
}
