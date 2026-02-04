package com.aogallo.blog.repositories;

import com.aogallo.blog.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {
}
