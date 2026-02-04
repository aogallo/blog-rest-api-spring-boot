package com.aogallo.blog.services.interfaces;

import com.aogallo.blog.domain.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> listCategories();
}
