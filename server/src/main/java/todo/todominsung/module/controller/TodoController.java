package todo.todominsung.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.service.TodoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    // TODO..검색 조건 추가해서 구현하기
    @GetMapping("/search")
    public ResponseEntity<List<TodoDto>> searchTodos(@RequestParam(required = false) String keyword) {
        List<TodoDto> results = todoService.searchTodos(keyword);
        return ResponseEntity.ok(results);
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
        TodoDto updatedTodo = todoService.updateTodoText(id, newText); // Service 호출
        return ResponseEntity.ok(updatedTodo);
    }
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id, @RequestBody Map<String, Boolean> request) {
        Boolean completed = request.get("completed");
        TodoDto updatedTodo = todoService.completeTodo(id, completed);
        return ResponseEntity.ok(updatedTodo);
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