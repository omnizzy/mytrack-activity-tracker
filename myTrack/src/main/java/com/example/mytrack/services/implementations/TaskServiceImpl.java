package com.example.mytrack.services.implementations;

import com.example.mytrack.dtos.taskdtos.*;
import com.example.mytrack.entities.AppUser;
import com.example.mytrack.entities.Task;
import com.example.mytrack.enums.TaskStatus;
import com.example.mytrack.exceptions.InvalidDataException;
import com.example.mytrack.repositories.TaskRepository;
import com.example.mytrack.repositories.UserRepository;
import com.example.mytrack.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public TaskDTO createTask(TaskCreateDTO taskCreateDTO, Long appUser_id) {
        Optional<AppUser> optionalAppUser = userRepository.findById(appUser_id);
        if(optionalAppUser.isEmpty()){
            throw  new InvalidDataException("This user cannot create a task");
        }
        //get the user from the database if the user is available
        AppUser appUser = optionalAppUser.get();

        Task task = new Task();

        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setAppUser(appUser);


        Task savedTask = taskRepository.save(task);

        return TaskDTO.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .taskStatus(savedTask.getTaskStatus())
                .createdAt(savedTask.getCreatedAt()).build();
    }

    @Override
    public List<TaskDTO> getAllTask(Long appUser_id) {
        Optional<AppUser> optionalAppUser = userRepository.findById(appUser_id);
        if (optionalAppUser.isEmpty()) {
            throw new InvalidDataException("User not found");
        } else {
            AppUser appUser = optionalAppUser.get();
            List<Task> tasks = taskRepository.findByAppUser(appUser);

            return tasks.stream()
                    .map(task -> TaskDTO.builder()
                            .id(task.getId())
                            .title(task.getTitle())
                            .description(task.getDescription())
                            .taskStatus(task.getTaskStatus())
                            .createdAt(task.getCreatedAt())
                            .build())
                    .toList();
        }

    }

    @Override
    public TaskUpdateResponseDTO updateTask(TaskCreateDTO taskCreateDTO, Long appUser_id, Long id) {
        //check if the Appuser trying to update a task is a registered user
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if (optionalAppUser.isEmpty()) {
            throw new InvalidDataException("You are not an authorized User");
        } else {
            AppUser appUser = optionalAppUser.get();
            //check if the task trying to be updated actually exists in my database
            Optional<Task> optionalTask = taskRepository.findById(id);
            if (optionalTask.isEmpty()) {
                throw new InvalidDataException("sorry! this task does not exist");
            } else {
                Task task = optionalTask.get();
                //compare the id of the Appuser with id of the Appuser that created the task,only when they are a match should the update be allowed
                if (!task.getAppUser().getId().equals(appUser.getId())) {
                    throw new InvalidDataException("You are not authorized to update this task");
                }
                //update the task if the user is authorized
                task.setTitle(taskCreateDTO.getTitle());
                task.setDescription(taskCreateDTO.getDescription());
                task.setTaskStatus(TaskStatus.PENDING);
                task.setUpdatedAt(LocalDateTime.now());
                Task updatedTask = taskRepository.save(task);

                //provide the user with a response
                TaskUpdateResponseDTO taskUpdateResponseDTO = new TaskUpdateResponseDTO();
                taskUpdateResponseDTO.setId (updatedTask.getId());
                taskUpdateResponseDTO.setTitle(updatedTask.getTitle());
                taskUpdateResponseDTO.setTaskStatus(updatedTask.getTaskStatus());
                taskUpdateResponseDTO.setDescription(updatedTask.getDescription());
                taskUpdateResponseDTO.setUpdatedAt(LocalDateTime.now());
                return taskUpdateResponseDTO;
            }
        }
    }

    @Override
    public TaskDeleteResponseDto deleteTask(Long appUserId, Long taskId) {
        //check if the Appuser trying to update a task is a registered user
        Optional<AppUser> optionalAppUser = userRepository.findById(appUserId);
        if (optionalAppUser.isEmpty()) {
            throw new InvalidDataException("you are not an authorized user");
        } else {
            AppUser appUser = optionalAppUser.get();
//            check if the task trying to be updated actually exists in my database
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isEmpty()) {
                throw new InvalidDataException("sorry! this task does not exist");
            } else {
                Task task = optionalTask.get();

//compare the id of the Appuser with id of the Appuser that created the task,only when they are a match should the update be allowed
                if (!task.getAppUser().getId().equals(appUser.getId())) {
                    throw new InvalidDataException("Task does not belong to the user");
                }
                taskRepository.delete(task);
                TaskDeleteResponseDto deleteResponseDto = new TaskDeleteResponseDto();
                deleteResponseDto.setId(taskId);
                deleteResponseDto.setMessage("Task successfully deleted");

                return deleteResponseDto;
            }
        }

    }

// Helper method
    private Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new InvalidDataException("Task not found"));
    }
    @Override
    public DoneTaskDTO moveTaskToDone(Long taskId) {
        Task task = findTaskById(taskId);
        task.setTaskStatus(TaskStatus.DONE);
        task.setCompletedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return DoneTaskDTO.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .taskStatus(savedTask.getTaskStatus())
                .completedAt(savedTask.getCompletedAt()).build();
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = findTaskById(taskId);

        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskStatus(task.getTaskStatus())
                .createdAt(task.getCreatedAt()).build();

    }
}

