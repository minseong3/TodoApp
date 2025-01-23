package todo.todominsung.module.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.repository.TodoRepository;
import todo.todominsung.module.service.TodoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    // DI(의존성 주입 생성자 방법)
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtos = todoService.getAllTodos();
        return ResponseEntity.ok(todoDtos);
    }
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto createdTodo = todoService.createTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }
    @PatchMapping("/{id}/text")
    public ResponseEntity<TodoDto> updateText(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        String newText = updates.get("text");
        TodoDto updatedTodo = todoService.patchTodo(id, newText); // Service 호출
        return ResponseEntity.ok(updatedTodo);
    }
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        TodoDto completedTodo = todoService.changeCompleted(id);
        return ResponseEntity.ok(completedTodo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo with id " + id + " has been deleted.");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.ok("All deleted.");
    }
}