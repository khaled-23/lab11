package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void addPost(Post post){
        boolean doesUserExists = userRepository.existsById(post.getUserId());
        if(!doesUserExists){
            throw new ApiException("user does not exists");
        }
        boolean doesCategoryExists = categoryRepository.existsById(post.getCategoryId());
        if(!doesCategoryExists){
            throw new ApiException("category does not exists");
        }
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public void updatePost(Integer post_id, Post post){
        Post p = postRepository.findPostByPostId(post_id);
        if(p==null){
            throw new ApiException("post was not found");
        }
        boolean doesCategoryExists = categoryRepository.existsById(post.getCategoryId());
        if(!doesCategoryExists){
            throw new ApiException("category does not exists");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setPublishDate(post.getPublishDate());
        postRepository.save(p);
    }

    public void removePost(Integer post_id){
        Post post = postRepository.findPostByPostId(post_id);
        if(post==null){
            throw new ApiException("post was not found");
        }
        postRepository.delete(post);
    }

    public void allowComment(Integer post_id){
        Post post = postRepository.findPostByPostId(post_id);
        post.setAllowComments(!post.getAllowComments());
        postRepository.save(post);
    }

    public List<Post> getAllPostsByUserId(Integer userId) {
        return postRepository.findPostsByUserId(userId);
    }

    public Post getPostByTitle(String title){
        Post post = postRepository.getPostByTitle(title);
        if(post==null){
            throw new ApiException("there is no post by given title");
        }
        return post;
    }

    public List<Post> getPostBeforeDate(LocalDate date){
        List<Post> postsBeforeDate = postRepository.findPostsByPublishDateBefore(date);
        if(postsBeforeDate.isEmpty()){
            throw new ApiException("there is no post by before given date");
        }
        return postsBeforeDate;
    }
}