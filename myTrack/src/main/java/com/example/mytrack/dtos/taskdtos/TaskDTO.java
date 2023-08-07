package com.example.mytrack.dtos.taskdtos;

import com.example.mytrack.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;
}
