package com.github.hybusa.EffectiveMobileTestTask.mapper;

import com.github.hybusa.EffectiveMobileTestTask.dto.CommentDto;
import com.github.hybusa.EffectiveMobileTestTask.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "author.login", target = "author")
    CommentDto commentToCommentDto (Comment comment);
}
