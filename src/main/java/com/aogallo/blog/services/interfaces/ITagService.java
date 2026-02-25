package com.aogallo.blog.services.interfaces;

import com.aogallo.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface ITagService {
    List<Tag> getTags();

    List<Tag> createTags(Set<String> tagNames);
}
