package abudu.awsa.utils;

import abudu.awsa.controllers.TaskController;
import abudu.awsa.dto.TaskDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class HateoasUtil {


    public static EntityModel<TaskDTO> addLinksToTask(TaskDTO taskDTO) {
        EntityModel<TaskDTO> resource = EntityModel.of(taskDTO);

        // Add self-link
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class)
                .getTaskById(taskDTO.getId())).withSelfRel();
        resource.add(selfLink);

        // Add link to all tasks
        Link allTasksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class)
                .getAllTasks()).withRel("all-tasks");
        resource.add(allTasksLink);

        // Add link to update task
        Link updateTaskLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class)
                .updateTask(taskDTO.getId(), null)).withRel("update-task");
        resource.add(updateTaskLink);

        // Add link to delete task
        Link deleteTaskLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class)
                .deleteTask(taskDTO.getId())).withRel("delete-task");
        resource.add(deleteTaskLink);

        return resource;
    }
}
