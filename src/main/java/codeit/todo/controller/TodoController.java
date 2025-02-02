package codeit.todo.controller;

import codeit.todo.entity.Todo;
import codeit.todo.entity.TodoSaveForm;
import codeit.todo.entity.TodoUpdateForm;
import codeit.todo.repository.MemoryTodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/todos")
@RequiredArgsConstructor
public class TodoController {
    private final MemoryTodoRepository memoryTodoRepository;

    @GetMapping
    public String todos(Model model) {
        List<Todo> todos=memoryTodoRepository.findAll();
        model.addAttribute("todos", todos);
        return "basic/todos";
    }

    @GetMapping("/{todoId}")
    public String todo(@PathVariable long todoId, Model model) {
        Todo todo = memoryTodoRepository.findById(todoId);
        model.addAttribute("todo", todo);
        return "/basic/todo";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addTodo(@Validated @ModelAttribute("todo") TodoSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("errors={}",bindingResult);
            return "basic/addForm";
        }

        Todo todo = new Todo();
        todo.setTitle(form.getTitle());
        todo.setDescription(form.getDescription());
        Todo savedTodo = memoryTodoRepository.save(todo);
        redirectAttributes.addAttribute("todoId", savedTodo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/todos/{todoId}";
    }

    @GetMapping("/{todoId}/edit")
    public String editForm(@PathVariable Long todoId, Model model) {
        Todo todo = memoryTodoRepository.findById(todoId);
        model.addAttribute("todo", todo);
        return "basic/editForm";
    }

    @PostMapping("/{todoId}/edit")
    public String edit(@PathVariable Long todoId, @Validated @ModelAttribute("todo") TodoUpdateForm form,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors={}",bindingResult);
            return "basic/editForm";
        }
        Todo todoParam = new Todo();
        todoParam.setDescription(form.getDescription());
        todoParam.setTitle(form.getTitle());
        memoryTodoRepository.update(todoId, todoParam);
        return "redirect:/basic/todos/{todoId}";
    }
}
