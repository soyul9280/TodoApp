package codeit.todo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class TodoUpdateForm {
    @NotNull
    private Long id;
    @NotBlank
    private String title;

    private String description;
}
