package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotEmpty(message = "user name should not be empty")
    @Size(min = 4, message = "username should be at least 4")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNIQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, message = "password should be at least 8 characters")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String password;
    @Email(message = "please provide a valid email")
    @NotEmpty(message = "email should not be empty")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String email;
    @PositiveOrZero(message = "number of comments should be zero or positive")
    @Min(value = 0,message = "minimum number of comment is 0")
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer numberOfComment;
    @PastOrPresent(message = "date should be at present or past")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDate registrationDate;
}
