package abudu.awsa.utils;

import abudu.awsa.dto.TaskDTO;
import abudu.awsa.models.Priority;
import abudu.awsa.models.Status;
import abudu.awsa.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toTaskDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority().name(),
                task.getDeadline(),
                task.getStatus().name()
        );
    }

    public static Task toTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(Priority.valueOf(taskDTO.getPriority().toUpperCase()));
        task.setDeadline(taskDTO.getDeadline());
        task.setStatus(Status.valueOf(taskDTO.getStatus().toUpperCase()));
        return task;
    }
}