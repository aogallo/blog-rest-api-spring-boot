package com.aogallo.blog.controllers;

import com.aogallo.blog.domain.dtos.CreateTagsRequest;
import com.aogallo.blog.domain.dtos.TagResponse;
import com.aogallo.blog.domain.entities.Tag;
import com.aogallo.blog.mappers.ITagMapper;
import com.aogallo.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final ITagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getTags() {

        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags
                .stream()
                .map(tagMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> addTags(@RequestBody CreateTagsRequest createTagsRequest) {


        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> list = savedTags
                .stream()
                .map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
}
