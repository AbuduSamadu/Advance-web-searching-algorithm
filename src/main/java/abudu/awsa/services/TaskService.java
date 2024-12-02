package abudu.awsa.services;

import abudu.awsa.dto.TaskDTO;
import abudu.awsa.models.Task;
import abudu.awsa.repositories.TaskRepository;
import abudu.awsa.utils.TaskMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper ;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getTasksByPriority(Task priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getTasksByStatus(Task status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksWithUpcomingDeadlines(LocalDateTime now) {
        return taskRepository.findByDeadlineAfter(now);
    }

    public List<Task> getTasksWithOverdueDeadlines(LocalDateTime now) {
        return taskRepository.findByDeadlineBefore(now);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = TaskMapper.toTask(taskDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toTaskDTO(savedTask);
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toTaskDTO(task);
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = TaskMapper.toTask(taskDTO);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toTaskDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
