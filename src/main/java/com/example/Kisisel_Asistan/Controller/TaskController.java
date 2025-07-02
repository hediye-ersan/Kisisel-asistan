package com.example.Kisisel_Asistan.Controller;

import com.example.Kisisel_Asistan.Dto.TaskDTO;
import com.example.Kisisel_Asistan.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        TaskDTO createdTask = taskService.createTask(dto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getTaskByUserId(@PathVariable Long userId){
        List<TaskDTO> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO dto) {
        TaskDTO updated = taskService.updateTask(taskId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
