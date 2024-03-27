package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Integer category_id, Category category){
        Category c = categoryRepository.findCategoryByCategoryId(category_id);
        if(c==null){
            throw new ApiException("category could not be found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void removeCategory(Integer category_id){
        Category category = categoryRepository.findCategoryByCategoryId(category_id);
        if(category == null){
            throw new ApiException("category not found");
        }
        categoryRepository.delete(category);
    }

}
