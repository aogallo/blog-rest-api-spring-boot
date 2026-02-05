package com.aogallo.blog.repositories;

import com.aogallo.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITagRepository extends JpaRepository<Tag, UUID> {
}
