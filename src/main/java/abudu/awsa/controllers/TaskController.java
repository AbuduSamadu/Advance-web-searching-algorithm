package abudu.awsa.controllers;

import abudu.awsa.dto.TaskDTO;
import abudu.awsa.models.Priority;
import abudu.awsa.models.Task;
import abudu.awsa.services.TaskService;
import abudu.awsa.utils.HateoasUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController{
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        EntityModel<TaskDTO> resource = HateoasUtil.addLinksToTask(createdTask);
       return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource.getContent());
    }

    @GetMapping("/{id}")
    public EntityModel<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = taskService.getTaskById(id);
        return HateoasUtil.addLinksToTask(task);
    }

    @GetMapping
    public List<EntityModel<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return tasks.stream()
                .map(HateoasUtil::addLinksToTask)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        EntityModel<TaskDTO> resource = HateoasUtil.addLinksToTask(updatedTask);
        return ResponseEntity.ok(resource.getContent());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filter/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        List<Task> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Task status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter/upcoming")
    public ResponseEntity<List<Task>> getUpcomingTasks() {
        List<Task> tasks = taskService.getTasksWithUpcomingDeadlines(LocalDateTime.now());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> tasks = taskService.getTasksWithOverdueDeadlines(LocalDateTime.now());
        return ResponseEntity.ok(tasks);
    }


}
