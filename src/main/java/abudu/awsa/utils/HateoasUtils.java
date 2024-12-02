package abudu.awsa.utils;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class HateoasUtils {

    // Method to create a self-link for a resource
    public static Link createSelfLink(Class<?> controllerClass, Long resourceId, String relation) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(controllerClass).getClass())
                .slash(resourceId)
                .withRel(relation);
    }

    // Example: Generate a collection link
    public static Link createCollectionLink(Class<?> controllerClass, String relation) {
        return WebMvcLinkBuilder.linkTo(controllerClass).withRel(relation);
    }
}
