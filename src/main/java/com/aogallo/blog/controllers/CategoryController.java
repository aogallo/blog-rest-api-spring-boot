package com.aogallo.blog.controllers;

import com.aogallo.blog.domain.dtos.CategoryDTO;
import com.aogallo.blog.domain.entities.Category;
import com.aogallo.blog.mappers.ICategoryMapper;
import com.aogallo.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ICategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        List<CategoryDTO> categories = categoryService.listCategories()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();

        return ResponseEntity.ok(categories);
    }
}


