package codeit.todo.repository;

import codeit.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryTodoRepository implements TodoRepository{
    private static final Map<Long,Todo> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public Todo findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long todoId, Todo updateParam) {
        Todo findTodo = findById(todoId);
        findTodo.setTitle(updateParam.getTitle());
        findTodo.setDescription(updateParam.getDescription());
        findTodo.setOpen(updateParam.getOpen());
    }

    @Override
    public void delete(Long todoId) {
        store.remove(todoId);
    }
}
