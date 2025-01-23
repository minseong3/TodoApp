package todo.todominsung.module.service;

import java.util.List;

import org.springframework.stereotype.Service;


import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.entity.Todo;
import todo.todominsung.module.mapper.TodoMapper;
import todo.todominsung.module.repository.TodoRepository;



@Service
public class TodoService {

    //private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    // 의존성 주입 (생성자 방법)
    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }
    // todoDto를 인자로 받아서 todo로 매핑 후 db에 저장하고 Dto 반환
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = todoMapper.dtoToEntity(todoDto);
        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.entityToDto(savedTodo);
    }
    // 중복된 todo가 있으면 예외를 던지기
    // todo를 todoDto로 매핑 후 반환
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDto> todoDtos = todoMapper.entitiesToDtos(todos);
        return todoDtos;
    }
    // db에서 id를 찾아 text를 변경하고 todoDto로 매핑 후 반환
    public TodoDto patchTodo(Long id, String newText) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다: " + id));
        
        todo.setText(newText);
        Todo updatedTodo = todoRepository.save(todo);
        
        return todoMapper.entityToDto(updatedTodo);
    }
    // id를 인자로 받아 db에서 todo 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    // db에 저장되어있는 todo 전부 삭제
    public void deleteAll() {
        todoRepository.deleteAll();
    }
    // completed를 true로 바꾸고 todoDto로 매핑 후 반환
    public TodoDto changeCompleted(Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다: " + id));
        // completed 값 반전
        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.entityToDto(updatedTodo);

    }
}