package com.example.mytrack.dtos.taskdtos;

import com.example.mytrack.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateResponseDTO {
    private Long id;

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private LocalDateTime updatedAt;
}
