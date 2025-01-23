package todo.todominsung.module.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.entity.Todo;

@Component
public class TodoMapper {
    // TodoDto를 Todo 엔티티로 변환
    public Todo dtoToEntity(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setText(todoDto.getText());
        todo.setCompleted(todoDto.getCompleted());
        return todo;
    }

    // Todo 엔티티를 TodoDto로 변환
    public TodoDto entityToDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setText(todo.getText());
        todoDto.setCompleted(todo.getCompleted());
        return todoDto;
    }

    // TodoDto 리스트 -> Todo 리스트 변환
    public List<Todo> dtosToEntities(List<TodoDto> todoDtos) {
        return todoDtos.stream()
                .map(this::dtoToEntity) // 각 TodoDto를 Todo로 변환
                .collect(Collectors.toList()); // 변환된 Todo 객체들을 리스트로 반환
    }

    // Todo 리스트 -> TodoDto 리스트 변환
    public List<TodoDto> entitiesToDtos(List<Todo> todos) {
        return todos.stream()
                .map(this::entityToDto) // 각 Todo를 TodoDto로 변환
                .collect(Collectors.toList()); // 변환된 TodoDto 객체들을 리스트로 반환
    }
}