package com.github.hybusa.EffectiveMobileTestTask.mapper;

import com.github.hybusa.EffectiveMobileTestTask.dto.CommentDto;
import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.models.Comment;
import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    @Mapping(source = "author.login", target = "author")
    @Mapping(source = "assigned.login", target = "assigned")
    TaskDto taskToTaskDto(Task task);

    default Task taskDtoToTask(Task task, TaskDto taskDto) {
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());
        return task;
    }

    List<TaskDto> listMap(List<Task> tasks);

    @Mapping(source = "author.login", target = "author")
    CommentDto commentToCommentDto(Comment comment);
}
