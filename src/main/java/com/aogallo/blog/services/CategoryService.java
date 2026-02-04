package com.aogallo.blog.services;

import com.aogallo.blog.domain.entities.Category;
import com.aogallo.blog.repositories.ICategoryRepository;
import com.aogallo.blog.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAllWithPostCount();
    }
}
