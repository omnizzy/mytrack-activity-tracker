package com.example.mytrack.controllers;

import com.example.mytrack.dtos.taskdtos.*;
import com.example.mytrack.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mytask/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create_task/{appUser_id}")
    public ResponseEntity<?> createTask(@RequestBody TaskCreateDTO taskCreateDTO, @PathVariable("appUser_id") Long appUser_id){
        TaskDTO taskResponse = taskService.createTask(taskCreateDTO, appUser_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByUserId(@PathVariable("userId") Long userId) {
        List<TaskDTO> tasks = taskService.getAllTask(userId);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/update_task/{userId}/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody TaskCreateDTO taskRequestDto,@PathVariable("userId")Long userId ,@PathVariable("taskId") Long taskId) {
        TaskUpdateResponseDTO taskUpdateResponse = taskService.updateTask(taskRequestDto, userId, taskId);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskUpdateResponse);
    }

    @DeleteMapping("/delete_task/{userId}/{taskId}")
    public ResponseEntity<TaskDeleteResponseDto> deleteTask(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        TaskDeleteResponseDto deleteResponse = taskService.deleteTask(userId, taskId);
        return ResponseEntity.ok(deleteResponse);
    }

    @PutMapping("/{taskId}/done")
    public ResponseEntity<DoneTaskDTO> moveTaskToDone(@PathVariable Long taskId) {
        DoneTaskDTO movedTask = taskService.moveTaskToDone(taskId);
        return ResponseEntity.ok(movedTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        TaskDTO task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
}
