package com.example.mytrack.dtos.taskdtos;

import com.example.mytrack.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DoneTaskDTO {

    private Long id;

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private LocalDateTime completedAt;
}
