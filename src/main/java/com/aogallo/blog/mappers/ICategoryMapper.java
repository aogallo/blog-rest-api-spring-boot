package com.aogallo.blog.mappers;

import com.aogallo.blog.domain.PostStatus;
import com.aogallo.blog.domain.dtos.CategoryDTO;
import com.aogallo.blog.domain.entities.Category;
import com.aogallo.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDTO(Category category);


    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {

        if (null == posts) {
            return 0;
        }

        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }

}



