package codeit.todo.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class TodoSaveForm {
    @NotBlank
    private String title;

    private String description;
}
