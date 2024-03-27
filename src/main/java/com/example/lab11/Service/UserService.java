package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void addUser(User user){
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void updateUser(Integer user_id, User user){
        User u = userRepository.findUserByUserId(user_id);
        if(u == null){
            throw new ApiException("user does not exists");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistrationDate(user.getRegistrationDate());
        userRepository.save(u);
    }

    public void removeUser(Integer user_id){
        User user = userRepository.findUserByUserId(user_id);
        if(user == null){
            throw new ApiException("user not found");
        }
        userRepository.delete(user);
    }

    public void userCommented(Integer userId){
        User user = userRepository.findUserByUserId(userId);
        user.setNumberOfComment(user.getNumberOfComment()+1);
        userRepository.save(user);
    }

    public void userRemovedComment(Integer userId){
        User user = userRepository.findUserByUserId(userId);
        user.setNumberOfComment(user.getNumberOfComment()-1);
        userRepository.save(user);
    }

    public List<User> getUsersWithoutComments() {
        List<User> usersWithoutComment = userRepository.findUsersByNumberOfComment();
        if(usersWithoutComment.isEmpty()){
            throw new ApiException("there is no user without comments");
        }
        return usersWithoutComment;
    }
}
