package com.aogallo.blog.services;

import com.aogallo.blog.domain.entities.Tag;
import com.aogallo.blog.repositories.ITagRepository;
import com.aogallo.blog.services.interfaces.ITagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final ITagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(t -> !existingTagNames.contains(t))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .collect(Collectors.toList());

        List<Tag> tags = new ArrayList<>();

        if (!newTags.isEmpty()) {
            tags = tagRepository.saveAll(newTags);
        }

        tags.addAll(existingTags);

        return tags;
    }
}
