package com.example.mytrack.repositories;

import com.example.mytrack.entities.AppUser;
import com.example.mytrack.entities.Task;
import com.example.mytrack.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskStatus(TaskStatus taskStatus);
    List<Task> findByAppUser(AppUser appUser);
}

