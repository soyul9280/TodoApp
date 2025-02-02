package codeit.todo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {

    private Long id;
    private String title;
    private String description;
    private Boolean open;

    public Todo() {
    }

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
