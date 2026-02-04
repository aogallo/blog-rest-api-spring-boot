package com.aogallo.blog.repositories;

import com.aogallo.blog.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {
}
