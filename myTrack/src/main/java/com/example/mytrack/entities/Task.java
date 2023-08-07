package com.example.mytrack.entities;

import com.example.mytrack.enums.TaskStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", columnDefinition = "VARCHAR(255)")
    private TaskStatus taskStatus;

    @CreatedDate
    @Column (updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}
