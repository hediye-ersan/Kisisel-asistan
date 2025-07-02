package com.example.Kisisel_Asistan.Dto;

import com.example.Kisisel_Asistan.Entity.Task;
import com.example.Kisisel_Asistan.Entity.User;

public class TaskMapper {
    public static TaskDTO toDTO(Task task){
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .userId(task.getUser().getId())
                .build();
    }

    public static Task toEntity(TaskDTO dto, User user){
        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .user(user)
                .build();
    }
}
