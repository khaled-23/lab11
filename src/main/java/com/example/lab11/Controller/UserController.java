package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("user added"));
    }

    @GetMapping("/users")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity updateUser(@PathVariable Integer user_id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        userService.updateUser(user_id,user);
        return ResponseEntity.ok(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity removeUser(@PathVariable Integer user_id){
        userService.removeUser(user_id);
        return ResponseEntity.ok("user removed");
    }
    //10
    @GetMapping("/users-without-comments")
    public ResponseEntity getUsersWithoutComments(){
        return ResponseEntity.ok(userService.getUsersWithoutComments());
    }
}
