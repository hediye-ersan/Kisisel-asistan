package com.example.Kisisel_Asistan.Services;

import com.example.Kisisel_Asistan.Dto.TaskDTO;
import com.example.Kisisel_Asistan.Dto.TaskMapper;
import com.example.Kisisel_Asistan.Entity.Task;
import com.example.Kisisel_Asistan.Entity.User;
import com.example.Kisisel_Asistan.Exception.ResourceNotFoundException;
import com.example.Kisisel_Asistan.Repository.TaskRepository;
import com.example.Kisisel_Asistan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDTO createTask(TaskDTO dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        Task task = TaskMapper.toEntity(dto, user);
        Task saved= taskRepository.save(task);
        return TaskMapper.toDTO(saved);
    }

    public List<TaskDTO> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Task> tasks = taskRepository.findByUser(user);
        return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
    }

    public TaskDTO updateTask(Long taskId, TaskDTO dto){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        Task updated = taskRepository.save(task);
        return TaskMapper.toDTO(updated);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        taskRepository.delete(task);
    }
}
