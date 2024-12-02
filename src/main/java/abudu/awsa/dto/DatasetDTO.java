package abudu.awsa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DatasetDTO {

    private Long id;

    @NotNull (message = "Name cannot be null")
    @Size(min = 3 , max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull (message = "Value cannot be null")
    private Integer value;

    public DatasetDTO() {
    }

    public DatasetDTO(Long id, String name, Integer value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DatasetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
