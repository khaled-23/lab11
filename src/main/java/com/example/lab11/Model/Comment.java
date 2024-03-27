package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @NotEmpty(message = "content should not be empty")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String content;
    @NotNull(message = "comment_date should not be empty")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDate commentDate;
    @NotNull(message = "user_id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer userId;
    @NotNull(message = "post_id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer postId;
}
