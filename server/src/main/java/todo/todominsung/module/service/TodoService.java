package todo.todominsung.module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.repository.TodoRepository;


@Transactional
@Service
public class TodoService {
    /* 의존성 주입 1.(생성자 방법) @RequiredArgConstruc.. => 순환참조 에러를 컴파일 시 발견 가능하기때문
     * 2. @Autowired(필드 주입)
     * 3. Setter 방식
     * 애너테이션 방식 사용해보기*/
    @Autowired
    private TodoRepository todoRepository;

    // todoDto를 db에 저장하고 반환
    public TodoDto createTodo(TodoDto todoDto) {
        todoRepository.insertTodo(todoDto);
        todoRepository.updateTodoTime(todoDto.getId());
        return todoDto;
    }

    // Todo 검색 조회
    public List<TodoDto> searchTodos(String keyword) {
        return todoRepository.searchedTodos(keyword);
    }

    // Todo 전체 조회
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll();
    }

    // Todo 필터링 조회
    public List<TodoDto> filteringTodos(String category) {
        if("전체".equals(category)) {
            category = "ALL";
        }
        return todoRepository.filteredTodos(category);
    }

    // update to text
    public void updateTodo(Long id, String newText) {
        todoRepository.updateTodo(id, newText);
    }

    // completed
    public void completeTodo(Long id, Boolean completed) {
       todoRepository.completeTodo(id, completed);
    }

    // delete to todo
    public void deleteTodo(Long id) {
        todoRepository.deleteTodo(id);
    }

    // clear Alltodo
    public void deleteAll() {
        todoRepository.deleteAllTodos();
    }
}