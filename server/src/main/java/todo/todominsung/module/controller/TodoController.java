package todo.todominsung.module.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.dto.TodoTextUpdateDto;
import todo.todominsung.module.service.TodoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired /* 필드 주입 방식 */
    private TodoService todoService;

    @GetMapping("/search")
    public ResponseEntity<List<TodoDto>> searchTodos(@RequestParam(required = false) String keyword) {
        log.info("검색 키워드: {} ", keyword);
        List<TodoDto> searchTodos = todoService.searchTodos(keyword);
        return ResponseEntity.ok(searchTodos);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtos = todoService.getAllTodos();
        return ResponseEntity.ok(todoDtos);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TodoDto>> filterTodos(@RequestParam(required = false) String category) {
        List<TodoDto> filteredTodos = todoService.filteringTodos(category);
        return ResponseEntity.ok(filteredTodos);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto createdTodo = todoService.createTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PostMapping("/{id}/text")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoTextUpdateDto dto) {
        String newText = dto.getText();
        todoService.updateTodo(id, newText);
        return ResponseEntity.ok("Todo is patched");
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeTodo(@PathVariable Long id, @RequestBody Map<String, Boolean> request) {
        Boolean completed = request.get("completed");
        todoService.completeTodo(id, completed);
        System.out.println("완료 상태 값: " + completed);
        return ResponseEntity.ok("complete Changed");
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