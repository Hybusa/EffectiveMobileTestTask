package com.github.hybusa.EffectiveMobileTestTask.mapper;

import com.github.hybusa.EffectiveMobileTestTask.dto.TaskDto;
import com.github.hybusa.EffectiveMobileTestTask.enums.Status;
import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);
    default Task taskDtoToTask(Task task, TaskDto taskDto){
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());
        return task;
    }
}
