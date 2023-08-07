package com.example.mytrack.services;

import com.example.mytrack.dtos.taskdtos.*;

import java.util.List;

public interface TaskService {

        TaskDTO createTask(TaskCreateDTO taskCreateDTO, Long appUser_id);

        List<TaskDTO> getAllTask(Long appUser_id);

        TaskUpdateResponseDTO updateTask(TaskCreateDTO taskCreateDTO, Long appUser_id, Long id);

        TaskDeleteResponseDto deleteTask(Long id, Long taskId);

        DoneTaskDTO moveTaskToDone(Long taskId);

        TaskDTO getTaskById(Long taskId);

}
