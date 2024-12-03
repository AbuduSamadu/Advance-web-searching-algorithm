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
       return new Task(
               taskDTO.getTitle(),
               taskDTO.getDescription(),
               Priority.valueOf(taskDTO.getPriority()),
               taskDTO.getDeadline(),
               Status.valueOf(taskDTO.getStatus())
       );
    }
}