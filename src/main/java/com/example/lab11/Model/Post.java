package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @NotEmpty(message = "title should not be empty")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    private String title;
    @NotEmpty(message = "content should not be empty")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String content;
    @NotNull(message = "date should not be empty")
    @PastOrPresent(message = "publish_date should be at present")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDate publishDate;
    @NotNull(message = "allow comments can't be null")
    @Column(columnDefinition = "boolean not null")
    private Boolean allowComments;
    @NotNull(message = "user id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer userId;
    @NotNull(message = "category id should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer categoryId;
}
