package com.aogallo.blog.services;

import com.aogallo.blog.domain.entities.Category;
import com.aogallo.blog.repositories.ICategoryRepository;
import com.aogallo.blog.services.interfaces.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        boolean exists = categoryRepository.existsByName(category.getName());
        if (exists) {
            throw new IllegalArgumentException("Category with name " + category.getName() + " already exists");
        }
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent() && !category.get().getPosts().isEmpty()) {
            throw new IllegalArgumentException("Category has posts associated with it");
        }

        categoryRepository.deleteById(id);
    }
}
