package codeit.todo.repository;

import codeit.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
    Todo save(Todo todo);
    Todo findById(Long id);
    List<Todo> findAll();
    void update(Long todoId, Todo updateParam);
    void delete(Long todoId);
}
